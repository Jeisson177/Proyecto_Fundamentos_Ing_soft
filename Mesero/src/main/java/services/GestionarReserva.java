package services;

import controller.menuMesero.MesaControl;
import repository.ConsultarReservasRepository;
import model.Reserva;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GestionarReserva {
    private MesaControl mesa;
    private ConsultarReservasRepository reservaRepo;

    public GestionarReserva(MesaControl mesa) {
        this.mesa = mesa;
        this.reservaRepo = new ConsultarReservasRepository();
    }

    public List<String> obtenerHorariosDisponibles(LocalDate fechaSeleccionada, int mesaId) throws SQLException {
        List<String> horariosDisponibles = new ArrayList<>();
        String nombreDia = obtenerNombreDia(fechaSeleccionada);
        ConsultarReservasRepository.HorarioDia horarioDia = reservaRepo.obtenerHorarioDia(nombreDia);
        if (horarioDia != null) {
            LocalTime horaActual = horarioDia.getHoraApertura();
            while (horaActual.isBefore(horarioDia.getHoraCierre())) {
                if (!reservaRepo.FechaOcupada(fechaSeleccionada, horaActual, mesaId)) {
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

    public boolean GReserva(LocalDate calendario, String hora) {
        int idCliente = mesa.idUuario;
        int idMesa = mesa.getMesa();
        String fechaHora = calendario.toString() + " " + hora;
        return reservaRepo.guardarReserva(idCliente, idMesa, fechaHora);
    }

    public void mostrarReservas(List<Reserva> reservas) {
        for (Reserva reserva : reservas) {
            System.out.println("ID Reserva: " + reserva.getIdReserva() +
                    ", ID Cliente: " + reserva.getIdCliente() +
                    ", ID Mesa: " + reserva.getIdMesa() +
                    ", Fecha y Hora: " + reserva.getFechaHora());
        }
    }

    public int getMesa() {
        return mesa.getMesa();
    }

    public Reserva obtenerReservaPorId(int idReserva) {
        return reservaRepo.obtenerReservaPorId(idReserva);
    }
}
