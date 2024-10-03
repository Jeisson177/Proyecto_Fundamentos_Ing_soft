package services;

import repository.ReservaRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GestionarReserva {

    private ReservaRepository reservaRepo = new ReservaRepository();

    // Método para obtener los horarios disponibles en un día específico y para una mesa específica
    public List<String> obtenerHorariosDisponibles(LocalDate fechaSeleccionada, int mesa) throws SQLException {
        List<String> horariosDisponibles = new ArrayList<>();

        // Conectar a la base de datos
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto ingesoft", "root", "cl")) {

            // Obtener el día de la semana de la fecha seleccionada
            String nombreDia = obtenerNombreDia(fechaSeleccionada);

            // Consulta para obtener el intervalo y horarios de apertura y cierre para el día seleccionado
            String query = "SELECT intervalo, hora_apertura, hora_cierre FROM Dias d JOIN Horarios h ON d.id_dia = h.id_dia WHERE nombre_dia = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombreDia);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int intervalo = rs.getInt("intervalo");  // Intervalo en minutos
                        LocalTime horaApertura = rs.getTime("hora_apertura").toLocalTime();
                        LocalTime horaCierre = rs.getTime("hora_cierre").toLocalTime();

                        // Lógica para calcular los horarios disponibles
                        LocalTime horaActual = horaApertura;
                        while (horaActual.isBefore(horaCierre)) {
                            // Verificar si el horario está ocupado para la mesa
                            if (!reservaRepo.FechaOcupada(fechaSeleccionada, horaActual, mesa,connection)) {
                                horariosDisponibles.add(horaActual.format(DateTimeFormatter.ofPattern("HH:mm")));
                            }
                            horaActual = horaActual.plusMinutes(intervalo);  // Avanzar en el intervalo
                        }
                    }else{

                        System.out.println("No se encontraron resultados para: " + nombreDia);
                    }
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
        int idCliente = 1; // Supongamos que siempre es el cliente 1. Esto puede ser dinámico.
        int idMesa = 1;    // Supongamos que siempre es la mesa 1. Esto puede ser dinámico.

        // Combina la fecha (calendario) y la hora (hora) en una cadena de fecha y hora
        String fechaHora = calendario.toString() + " " + hora;  // Formato "YYYY-MM-DD HH:MM:SS"

        // Consulta SQL para insertar una nueva reserva
        String query = "INSERT INTO RESERVA (ID_CLIENTE, ID_MESA, FECHA_HORA) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto ingesoft", "root", "Basedatos")) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                // Asignamos los valores a los parámetros de la consulta
                stmt.setInt(1, idCliente);                    // ID del cliente
                stmt.setInt(2, idMesa);                       // ID de la mesa
                stmt.setString(3, fechaHora);                 // Fecha y hora de la reserva (YYYY-MM-DD HH:MM:SS)

                // Ejecutar la inserción
                int rowsAffected = stmt.executeUpdate();

                // Verificamos si la inserción fue exitosa
                if (rowsAffected > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
