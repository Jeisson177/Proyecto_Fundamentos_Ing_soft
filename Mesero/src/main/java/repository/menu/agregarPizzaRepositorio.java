package repository.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.Plato;
import repository.Credenciales;

public class agregarPizzaRepositorio {
    private static final Credenciales c = new Credenciales();
    private static final String URL = c.getURL();
    private static final String USER = c.getUser();
    private static final String PASSWORD = c.getPassword();

    // Método para obtener el precio de la pizza según el nombre y tamaño
    public double getPrecioPizza(String nombre, String tamaño) {
        String query = "SELECT PRECIO FROM PIZZA WHERE NOMBRE = ? AND TAMANO = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, tamaño);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("PRECIO");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 si no se encuentra el precio
    }

    // Método para verificar la disponibilidad de la pizza según el nombre y tamaño
    public boolean estaDisponible(String nombre, String tamaño) {
        String query = "SELECT DISPONIBILIDAD FROM PIZZA WHERE NOMBRE = ? AND TAMANO = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, tamaño);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return "Disponible".equalsIgnoreCase(rs.getString("DISPONIBILIDAD"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false si no está disponible
    }
}
