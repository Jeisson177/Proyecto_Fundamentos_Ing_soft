package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LasanaMenuRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/proyecto ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "cl";

    public int getPrecios(String nombreLasana) {
        String query = "SELECT PRECIO FROM PLATO WHERE nombre = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombreLasana);  // Asigna el parámetro
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
