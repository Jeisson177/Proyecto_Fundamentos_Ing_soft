package repository;

import controller.Reserva;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultarReservasRepository {

    private static final Credenciales c = new Credenciales();
    private static final String URL = c.getURL();
    private static final String USER = c.getUser();
    private static final String PASSWORD = c.getPassword();

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public HorarioDia obtenerHorarioDia(String nombreDia) {
        String query = "SELECT intervalo, hora_apertura, hora_cierre FROM Dias d " +
                "JOIN Horarios h ON d.id_dia = h.id_dia WHERE nombre_dia = ?";

        try (Connection connection = getConnection();
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

    public List<Reserva> obtenerReservasPorFecha(LocalDate fechaConsulta) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT ID_RESERVA, ID_CLIENTE, ID_MESA, FECHA_HORA FROM RESERVA WHERE DATE(FECHA_HORA) = ?";

        try (Connection connection = getConnection();
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
            e.printStackTrace();
        }
        return reservas;
    }

    public Reserva obtenerReservaPorId(int idReserva) {
        String query = "SELECT * FROM reserva WHERE ID_RESERVA = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idReserva);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idCliente = rs.getInt("ID_CLIENTE");
                    int idMesa = rs.getInt("ID_MESA");
                    LocalDateTime fechaHora = rs.getTimestamp("FECHA_HORA").toLocalDateTime();
                    return new Reserva(idReserva, idCliente, idMesa, fechaHora);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean guardarReserva(int idCliente, int idMesa, String fechaHora) {
        String query = "INSERT INTO RESERVA (ID_CLIENTE, ID_MESA, FECHA_HORA) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idCliente);
            stmt.setInt(2, idMesa);
            stmt.setTimestamp(3, Timestamp.valueOf(fechaHora));

            return stmt.executeUpdate() > 0;  // Devuelve true si la inserción fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean fechaOcupada(LocalDate fecha, LocalTime hora, int mesaId) {
        String query = "SELECT COUNT(*) FROM RESERVA WHERE DATE(FECHA_HORA) = ? AND HOUR(FECHA_HORA) = ? AND ID_MESA = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setInt(2, hora.getHour());
            stmt.setInt(3, mesaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Retorna true si hay reservas en ese horario
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarReserva(int idReserva) {
        String query = "DELETE FROM RESERVA WHERE ID_RESERVA = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idReserva);
            return stmt.executeUpdate() > 0; // Devuelve true si la eliminación fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int crearUsuarioTemporalSinCita() {
        String query = "INSERT INTO Usuario (NOMBRE, EMAIL, TELEFONO, ROL, CONTRASENA) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1; // Valor por defecto para detectar errores

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Define los valores del usuario temporal
            stmt.setString(1, "Usuario Temporal");
            stmt.setString(2, "temporal@email.com");
            stmt.setString(3, "000-0000");
            stmt.setString(4, "Cliente");
            stmt.setString(5, "temporalPass");

            // Ejecuta la inserción
            int affectedRows = stmt.executeUpdate();

            // Verifica si la inserción fue exitosa y obtiene el ID generado
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public void eliminarUsuarioTemporal(int idUsuario) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM usuario WHERE ID_USUARIO = ? AND es_temporal = true";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idUsuario);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class HorarioDia {
        private final int intervalo;
        private final LocalTime horaApertura;
        private final LocalTime horaCierre;

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
    public Integer obtenerUltimaReservaId() {
        String query = "SELECT ID_RESERVA FROM RESERVA ORDER BY ID_RESERVA DESC LIMIT 1";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("ID_RESERVA");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Devuelve null si no hay reservas
    }
}
