package repository;

import javafx.scene.control.Alert;
import model.Reserva;
import controller.ReservasControl;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class consultarReservasRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "cl";

    // Clase interna para encapsular la información del horario del día
    public static class HorarioDia {
        private int intervalo;
        private LocalTime horaApertura;
        private LocalTime horaCierre;

        public HorarioDia(int intervalo, LocalTime horaApertura, LocalTime horaCierre) {
            this.intervalo = intervalo;
            this.horaApertura = horaApertura;
            this.horaCierre = horaCierre;
        }

        public int getIntervalo() {
            return intervalo;
        }

        public LocalTime getHoraApertura() {
            return horaApertura;
        }

        public LocalTime getHoraCierre() {
            return horaCierre;
        }
    }

    // Método para obtener el horario de un día específico
    public HorarioDia obtenerHorarioDia(String nombreDia) {
        String query = "SELECT intervalo, hora_apertura, hora_cierre FROM Dias d JOIN Horarios h ON d.id_dia = h.id_dia WHERE nombre_dia = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, nombreDia);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int intervalo = rs.getInt("intervalo");
                    LocalTime horaApertura = rs.getTime("hora_apertura").toLocalTime();
                    LocalTime horaCierre = rs.getTime("hora_cierre").toLocalTime();
                    return new HorarioDia(intervalo, horaApertura, horaCierre);
                }
            }
        } catch (SQLException e) {
            mostrarAlertaError(e);
        }
        return null;
    }

    // Método para obtener las reservas en una fecha específica
    public List<Reserva> obtenerReservasPorFecha(LocalDate fechaConsulta) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT ID_RESERVA, ID_CLIENTE, ID_MESA, FECHA_HORA FROM RESERVA WHERE DATE(FECHA_HORA) = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(fechaConsulta));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idReserva = rs.getInt("ID_RESERVA");
                    int idCliente = rs.getInt("ID_CLIENTE");
                    int idMesa = rs.getInt("ID_MESA");
                    LocalDate fechaHora = rs.getTimestamp("FECHA_HORA").toLocalDateTime().toLocalDate(); // Cambié aquí
                    reservas.add(new Reserva(idReserva, idCliente, idMesa, fechaHora, null)); // Se pasa null para el estado
                }
            }
        } catch (SQLException e) {
            mostrarAlertaError(e);
        }
        return reservas;
    }

    public void atenderReserva(int idReserva) {
        String query = "UPDATE RESERVA SET ESTADO = 'Atendida' WHERE ID_RESERVA = ?"; // Asegúrate de que la tabla tenga esta columna

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idReserva);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reserva atendida con éxito: " + idReserva);
            } else {
                System.out.println("No se encontró la reserva con ID: " + idReserva);
            }
        } catch (SQLException e) {
            mostrarAlertaError(e);
        }
    }

    // Método para eliminar una reserva de la base de datos
    public boolean eliminarReserva(int idReserva) {
        String query = "DELETE FROM RESERVA WHERE ID_RESERVA = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idReserva);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            mostrarAlertaError(e);
        }
        return false;
    }

    // Método para mostrar alertas en caso de error de conexión
    private void mostrarAlertaError(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Conexión");
        alert.setHeaderText("No se pudo conectar con la base de datos");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
