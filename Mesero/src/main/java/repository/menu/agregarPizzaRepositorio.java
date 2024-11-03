package repository.menu;

import java.sql.*;

public class agregarPizzaRepositorio {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "cl";

    public int getPrecios(String nombrePizza) {
        String query = "SELECT PRECIO FROM PLATO WHERE nombre = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombrePizza);  // Asigna el parámetro
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("PRECIO");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;  // Valor por defecto si no se encuentra el precio
    }
}
