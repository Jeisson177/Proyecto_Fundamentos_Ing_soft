package repository.menu;

import repository.Credenciales;

import java.sql.*;

public class PostreRepositorio {

    private static final Credenciales c = new Credenciales();
    private static final String URL = c.getURL();
    private static final String USER = c.getUser();
    private static final String PASSWORD = c.getPassword();

    // Método para obtener el precio de un postre
    public int getPrecio(String nombrePostre) {
        String query = "SELECT PRECIO FROM PLATO WHERE NOMBRE = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombrePostre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("PRECIO");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el precio del postre: " + e.getMessage(), e);
        }
        return -1;  // Valor por defecto si no se encuentra el precio
    }

    // Método para verificar la disponibilidad de un postre
    public boolean isDisponible(String nombrePostre) {
        String query = """
            SELECT CASE 
                WHEN MIN(ii.CANTIDAD_INV) >= MAX(ip.Cantidad) THEN 'Disponible' 
                ELSE 'No disponible' 
            END AS Dispo
            FROM PLATO p
            JOIN INGREDIENTE_PLATO ip ON p.ID_PLATO = ip.ID_PLATO
            JOIN INGREDIENTE_INV ii ON ip.ID_INGREDIENTE = ii.ID_INGREDIENTE
            WHERE p.NOMBRE = ?
            GROUP BY p.NOMBRE;
        """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombrePostre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return "Disponible".equalsIgnoreCase(rs.getString("Dispo"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar la disponibilidad del postre: " + e.getMessage(), e);
        }
        return false;  // Retorna falso si no se encuentra el postre o no está disponible
    }
}

