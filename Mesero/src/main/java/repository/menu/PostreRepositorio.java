package repository.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostreRepositorio {

    private static final String URL = "jdbc:mysql://localhost:3306/proyecto ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "cl";

    public int getPrecios(String nombrePostre) {
        String query = "SELECT PRECIO FROM PLATO WHERE nombre = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombrePostre);  // Asigna el parámetro
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
    public boolean getDispo(String nombreBebida){

        String query = "SELECT   p.NOMBRE AS Plato, CASE  WHEN MIN(ii.CANTIDAD_INV >= ip.Cantidad) THEN 'Disponible' ELSE 'NO disponible' END AS Dispo FROM  PLATO p JOIN INGREDIENTE_PLATO ip ON p.ID_PLATO = ip.ID_PLATO JOIN INGREDIENTE_INV ii ON ip.ID_INGREDIENTE = ii.ID_INGREDIENTE WHERE p.NOMBRE = ? GROUP BY p.NOMBRE;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombreBebida);  // Asigna el parámetro
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        if (rs.getString("Dispo").equals("Disponible")) {
                            return true;
                        }else {
                            return false;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
