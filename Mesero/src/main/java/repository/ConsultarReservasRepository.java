package repository;

import javafx.scene.control.Alert;
import model.Reserva;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultarReservasRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "cl";

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
                    LocalDateTime fechaHora = rs.getTimestamp("FECHA_HORA").toLocalDateTime();
                    reservas.add(new Reserva(idReserva, idCliente, idMesa, fechaHora));
                }
            }
        } catch (SQLException e) {
            mostrarAlertaError(e);
        }
        return reservas;
    }

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

    // Método para verificar si la fecha está ocupada
    public boolean FechaOcupada(LocalDate fecha, LocalTime hora, int mesaId) {
        String query = "SELECT COUNT(*) FROM RESERVA WHERE DATE(FECHA_HORA) = ? AND HOUR(FECHA_HORA) = ? AND ID_MESA = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setInt(2, hora.getHour());
            stmt.setInt(3, mesaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Si hay reservas, retorna true
                }
            }
        } catch (SQLException e) {
            mostrarAlertaError(e);
        }
        return false; // No hay reservas
    }

    // Nuevo método para obtener una reserva por ID
    public Reserva obtenerReservaPorId(int idReserva) {
        String query = "SELECT ID_CLIENTE, ID_MESA, FECHA_HORA FROM RESERVA WHERE ID_RESERVA = ?";
        Reserva reserva = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idReserva);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idCliente = rs.getInt("ID_CLIENTE");
                    int idMesa = rs.getInt("ID_MESA");
                    LocalDateTime fechaHora = rs.getTimestamp("FECHA_HORA").toLocalDateTime();
                    reserva = new Reserva(idReserva, idCliente, idMesa, fechaHora);
                }
            }
        } catch (SQLException e) {
            mostrarAlertaError(e);
        }
        return reserva; // Retorna la reserva encontrada o null si no existe
    }

    public boolean guardarReserva(int idCliente, int idMesa, String fechaHora) {
        String query = "INSERT INTO RESERVA (ID_CLIENTE, ID_MESA, FECHA_HORA) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idCliente);
            stmt.setInt(2, idMesa);
            stmt.setTimestamp(3, Timestamp.valueOf(fechaHora));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Devuelve true si se insertó correctamente
        } catch (SQLException e) {
            mostrarAlertaError(e);
        }
        return false; // Devuelve false si hubo un error
    }

    private void mostrarAlertaError(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Conexión");
        alert.setHeaderText("No se pudo conectar con la base de datos");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
