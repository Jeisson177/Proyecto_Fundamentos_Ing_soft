package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.Plato;

public class consultarPlatosRepository {
    private static final Credenciales c = new Credenciales();
    private static final String URL = c.getURL();
    private static final String USER = c.getUser();
    private static final String PASSWORD = c.getPassword();

    // Método para obtener el precio de un plato por su nombre
    public double getPrecio(String nombre) {
        String query = "SELECT PRECIO FROM PLATO WHERE NOMBRE = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("PRECIO");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el precio del plato: " + e.getMessage(), e);
        }
        return -1;  // Retorna -1 si no se encuentra el plato
    }

    // Método para verificar la disponibilidad de un plato por su nombre
    public String getDisponibilidad(String nombre) {
        String query = """
            SELECT p.NOMBRE AS Plato, 
                   CASE 
                       WHEN MIN(ii.CANTIDAD_INV) >= MAX(ip.Cantidad) THEN 'Disponible' 
                       ELSE 'No disponible' 
                   END AS Disponibilidad 
            FROM PLATO p 
            JOIN INGREDIENTE_PLATO ip ON p.ID_PLATO = ip.ID_PLATO 
            JOIN INGREDIENTE_INV ii ON ip.ID_INGREDIENTE = ii.ID_INGREDIENTE 
            WHERE p.NOMBRE = ? 
            GROUP BY p.NOMBRE;
        """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Disponibilidad");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al verificar la disponibilidad del plato: " + e.getMessage(), e);
        }
        return "No encontrado";  // Retorna "No encontrado" si no se encuentra el plato
    }
}
