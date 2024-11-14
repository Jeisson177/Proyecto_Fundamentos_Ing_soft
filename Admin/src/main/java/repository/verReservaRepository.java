package repository;

import java.sql.*;
import java.time.LocalDate;
import Entities.Reserva;
import java.util.ArrayList;
import java.util.List;

public class verReservaRepository {
    private static final Credenciales credenciales=new Credenciales();
    private static final String URL = credenciales.getURL();
    //private static final String URL  = "jdbc:mysql://localhost:3307/proyecto ingesoft";
    private static final String USER = credenciales.getUser();
    private static final String PASSWORD = credenciales.getPassword();
    private List<Reserva> reservas;

    public Reserva getReservaCompletaPorId(String idReserva) {
        String query = "SELECT * FROM RESERVA WHERE ID_RESERVA = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, idReserva);  // Asigna el parámetro al PreparedStatement

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Construye y retorna un objeto Reserva con todos los atributos
                        return new Reserva(
                                rs.getInt("ID_RESERVA"),
                                rs.getInt("ID_CLIENTE"),
                                rs.getInt("ID_MESA"),
                                rs.getTimestamp("FECHA_HORA").toLocalDateTime()
                        );
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);  // Lanza una excepción si ocurre un error
        }

        return null;  // Retorna null si no se encuentra el resultado
    }

    public List<Reserva> getReservasPorIdCliente(String idCliente) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM RESERVA WHERE ID_CLIENTE = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, idCliente);  // Asigna el parámetro al PreparedStatement

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        // Construye un objeto Reserva y lo agrega a la lista
                        Reserva reserva = new Reserva(
                                rs.getInt("ID_RESERVA"),
                                rs.getInt("ID_CLIENTE"),
                                rs.getInt("ID_MESA"),
                                rs.getTimestamp("FECHA_HORA").toLocalDateTime()
                        );
                        reservas.add(reserva);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);  // Lanza una excepción si ocurre un error
        }

        return reservas;  // Retorna la lista de objetos Reserva
    }

    public List<Reserva> getReservasPorFecha(LocalDate fecha) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM RESERVA WHERE DATE(FECHA_HORA) = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setDate(1, java.sql.Date.valueOf(fecha));  // Asigna la fecha al PreparedStatement

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        // Construye y agrega cada objeto Reserva a la lista
                        reservas.add(new Reserva(
                                rs.getInt("ID_RESERVA"),
                                rs.getInt("ID_CLIENTE"),
                                rs.getInt("ID_MESA"),
                                rs.getTimestamp("FECHA_HORA").toLocalDateTime()
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);  // Lanza una excepción si ocurre un error
        }

        return reservas;  // Retorna la lista de reservas
    }

    public boolean eliminarReserva(int idReserva) {
        String query = "DELETE FROM RESERVA WHERE ID_RESERVA = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idReserva);  // Asigna el ID de la reserva al PreparedStatement
                int rowsAffected = stmt.executeUpdate();  // Ejecuta la eliminación
                return rowsAffected > 0;  // Retorna true si se eliminó al menos una fila
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la reserva: " + e.getMessage(), e);  // Lanza una excepción si ocurre un error
        }
    }

}
