package repository.menu;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.control.Alert;

public class ReservaRepository {

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
            e.printStackTrace();
        }
        return null;
    }

    // Método para verificar si una mesa está ocupada en una fecha y hora específicas
    public boolean FechaOcupada(LocalDate fechaConsulta, LocalTime hora, int mesa) {
        String query = "SELECT COUNT(*) AS total FROM reserva WHERE DATE(FECHA_HORA) = ? AND TIME(FECHA_HORA) = ? AND ID_MESA = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(fechaConsulta));
            stmt.setTime(2, Time.valueOf(hora));
            stmt.setInt(3, mesa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        } catch (SQLException e) {
            mostrarAlertaError(e);
        }
        return false;
    }

    // Método para guardar una nueva reserva en la base de datos
    public boolean guardarReserva(int idCliente, int idMesa, String fechaHora) {
        String query = "INSERT INTO RESERVA (ID_CLIENTE, ID_MESA, FECHA_HORA) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idCliente);
            stmt.setInt(2, idMesa);
            stmt.setString(3, fechaHora);

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
