package services;

import controller.menuMesero.MesaControl;
import repository.ConsultarReservasRepository;
import controller.Reserva;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionarReserva {

    private final MesaControl mesa;
    private final ConsultarReservasRepository reservaRepo;

    // Constructor que acepta MesaControl
    public GestionarReserva(MesaControl mesa) {
        this.mesa = mesa;
        this.reservaRepo = new ConsultarReservasRepository();
    }

    public GestionarReserva() {
        this.mesa = new MesaControl();  // Inicialización por defecto si aplica
        this.reservaRepo = new ConsultarReservasRepository();
    }

    public Optional<Reserva> obtenerReservaPorId(int idReserva) {
        Reserva reserva = reservaRepo.obtenerReservaPorId(idReserva);
        return Optional.ofNullable(reserva); // Envuelve la reserva en un Optional
    }

    // Método para obtener la reserva activa (por ejemplo, la última reserva hecha por el usuario actual)
    public Optional<Reserva> obtenerReservaActual() {
        Reserva reservaActual = reservaRepo.obtenerUltimaReserva(); // Ajustado sin try-catch
        return Optional.ofNullable(reservaActual);
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
}
