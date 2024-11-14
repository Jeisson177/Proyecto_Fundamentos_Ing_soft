package services;

import controller.menuMesero.MesaControl;
import repository.ConsultarReservasRepository;
import controller.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionarReserva {

    private final MesaControl mesa;
    private final ConsultarReservasRepository reservaRepo;
    static private Integer idReservaTemporal;

    public GestionarReserva(MesaControl mesa) {
        this.mesa = mesa;
        this.reservaRepo = new ConsultarReservasRepository();
    }

    public GestionarReserva() {
        this.mesa = new MesaControl();
        this.reservaRepo = new ConsultarReservasRepository();
    }


    public boolean estaMesaDisponible(int mesaId, LocalDate fecha, LocalTime hora) {
        return !reservaRepo.fechaOcupada(fecha, hora, mesaId);
    }

    public Optional<Reserva> obtenerReservaPorId(int idReserva) {
        return Optional.ofNullable(reservaRepo.obtenerReservaPorId(idReserva));
    }

    public List<String> obtenerHorariosDisponibles(LocalDate fechaSeleccionada, int mesaId) throws SQLException {
        List<String> horariosDisponibles = new ArrayList<>();
        String nombreDia = obtenerNombreDia(fechaSeleccionada);
        ConsultarReservasRepository.HorarioDia horarioDia = reservaRepo.obtenerHorarioDia(nombreDia);

        if (horarioDia != null) {
            LocalTime horaActual = horarioDia.getHoraApertura();
            while (horaActual.isBefore(horarioDia.getHoraCierre())) {
                if (!reservaRepo.fechaOcupada(fechaSeleccionada, horaActual, mesaId)) {
                    horariosDisponibles.add(horaActual.format(DateTimeFormatter.ofPattern("HH:mm")));
                }
                horaActual = horaActual.plusMinutes(horarioDia.getIntervalo());
            }
        }
        return horariosDisponibles;
    }

    private String obtenerNombreDia(LocalDate fecha) {
        switch (fecha.getDayOfWeek()) {
            case MONDAY: return "Lunes";
            case TUESDAY: return "Martes";
            case WEDNESDAY: return "Miércoles";
            case THURSDAY: return "Jueves";
            case FRIDAY: return "Viernes";
            case SATURDAY: return "Sábado";
            case SUNDAY: return "Domingo";
            default: return "";
        }
    }

    public boolean crearReserva(LocalDate fecha, String hora, int idUsuario) {
        int idMesa = mesa.getMesa();
        String fechaHora = fecha + " " + hora;
        return reservaRepo.guardarReserva(idUsuario, idMesa, fechaHora);
    }

    public List<Reserva> obtenerReservasPorFecha(LocalDate fecha) {
        return reservaRepo.obtenerReservasPorFecha(fecha);
    }

    public boolean esReservaActual(Reserva reserva) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaHoraReserva = reserva.getFechaHora();
        if (!fechaHoraReserva.toLocalDate().equals(ahora.toLocalDate())) {
            return false;
        }
        long minutosDiferencia = ChronoUnit.MINUTES.between(ahora.toLocalTime(), fechaHoraReserva.toLocalTime());
        return Math.abs(minutosDiferencia) <= 15;
    }

    public boolean intentarAtenderReserva(int idReserva) {
        Optional<Reserva> reservaOpt = obtenerReservaPorId(idReserva);

        if (reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();
            if (esReservaActual(reserva)) {
                System.out.println("Reserva actual atendida correctamente.");
                return true;
            } else {
                System.out.println("No se puede atender una reserva anterior o futura.");
                return false;
            }
        } else {
            System.out.println("Reserva no encontrada.");
            return false;
        }
    }

    public boolean eliminarReserva(int idReserva) {
        return reservaRepo.eliminarReserva(idReserva);
    }

    public boolean crearReservaSinCita(int mesaId) {
        int idUsuarioTemporal = reservaRepo.crearUsuarioTemporalSinCita();

        if (idUsuarioTemporal != -1) {
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            boolean reservaCreada = reservaRepo.guardarReserva(idUsuarioTemporal, mesaId, fechaHora);

            if (reservaCreada) {
                this.idReservaTemporal = reservaRepo.obtenerUltimaReservaId();
                Reserva reservaTemporal = reservaRepo.obtenerReservaPorId(idReservaTemporal);
                if (reservaTemporal != null) {
                    ReservaService.getInstance().setReservaSeleccionada(reservaTemporal); // Establece la reserva temporal en ReservaService
                }

                return true;
            } else {
                reservaRepo.eliminarUsuarioTemporal(idUsuarioTemporal);
                return false;
            }
        }
        return false;
    }

    public Integer getIdReservaTemporal() {
        System.out.println("Obteniendo ID de reserva temporal: " + idReservaTemporal);
        return idReservaTemporal;
    }
    public boolean eliminarReservaTemporal() {
        if (idReservaTemporal != null) {
            boolean reservaEliminada = reservaRepo.eliminarReserva(idReservaTemporal);
            if (reservaEliminada) {
                // int idUsuarioTemporal = reservaRepo.obtenerUsuarioPorReserva(idReservaTemporal);
                // reservaRepo.eliminarUsuarioTemporal(idUsuarioTemporal);
                idReservaTemporal = null;  // Resetear el ID temporal después de la eliminación
            }
            return reservaEliminada;
        }
        return false;
    }

    }

