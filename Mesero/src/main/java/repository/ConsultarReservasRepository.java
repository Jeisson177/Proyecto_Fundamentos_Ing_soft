package repository;

import controller.Reserva;

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

    // Método para establecer conexión con la base de datos
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para obtener los detalles del horario de un día específico
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

    // Método para obtener reservas según una fecha específica
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

    // Método para guardar una nueva reserva
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

    // Método para verificar si un horario está ocupado para una mesa específica
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

    // Clase interna para representar el horario de un día específico
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
}
