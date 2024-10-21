package services;

import controller.menu.MesaControl;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import repository.menu.ReservaRepository;

public class GestionarReserva {
    private MesaControl mesa;
    private ReservaRepository reservaRepo;

    public GestionarReserva(MesaControl mesa) {
        this.mesa = mesa;
        this.reservaRepo = new ReservaRepository();
    }

    // Método para obtener los horarios disponibles en un día específico y para una mesa específica
    public List<String> obtenerHorariosDisponibles(LocalDate fechaSeleccionada, int mesaId) throws SQLException {
        List<String> horariosDisponibles = new ArrayList<>();

        // Obtener el nombre del día de la semana
        String nombreDia = obtenerNombreDia(fechaSeleccionada);

        // Obtener intervalo, apertura y cierre desde el repositorio
        ReservaRepository.HorarioDia horarioDia = reservaRepo.obtenerHorarioDia(nombreDia);

        if (horarioDia != null) {
            // Calcular los horarios disponibles
            LocalTime horaActual = horarioDia.getHoraApertura();
            while (horaActual.isBefore(horarioDia.getHoraCierre())) {
                // Verificar si el horario está ocupado para la mesa
                if (!reservaRepo.FechaOcupada(fechaSeleccionada, horaActual, mesaId)) {
                    horariosDisponibles.add(horaActual.format(DateTimeFormatter.ofPattern("HH:mm")));
                }
                horaActual = horaActual.plusMinutes(horarioDia.getIntervalo());  // Avanzar en el intervalo
            }
        }

        return horariosDisponibles;
    }

    // Método auxiliar para obtener el nombre del día de la semana
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
        int idCliente = mesa.idUuario; // Obtener el ID del cliente
        int idMesa = mesa.getMesa();   // Obtener el ID de la mesa

        // Combinar la fecha y la hora en un formato correcto
        String fechaHora = calendario.toString() + " " + hora;

        // Usar el repositorio para guardar la reserva
        return reservaRepo.guardarReserva(idCliente, idMesa, fechaHora);
    }
}
