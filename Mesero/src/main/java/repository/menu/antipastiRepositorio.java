package repository.menu;

import repository.Credenciales;

import java.sql.*;

public class antipastiRepositorio {
    //String url = "jdbc:mysql://localhost:3307/proyecto ingesoft";
    private static final Credenciales c=new Credenciales();

    private static final String URL = c.getURL();
    private static final String USER = c.getUser();
    private static final String PASSWORD = c.getPassword();

    public int getPrecios(String nombre) {
        String query = "SELECT PRECIO FROM PLATO WHERE nombre = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombre);  // Asigna el parámetro
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
   /* public String getDispo(String nombre){
        String query = "SELECT   p.NOMBRE AS Plato, CASE  WHEN MIN(ii.CANTIDAD_INV >= ip.Cantidad) THEN 'Disponible' ELSE 'NO disponible' END AS Dispo FROM  PLATO p JOIN INGREDIENTE_PLATO ip ON p.ID_PLATO = ip.ID_PLATO JOIN INGREDIENTE_INV ii ON ip.ID_INGREDIENTE = ii.ID_INGREDIENTE WHERE p.NOMBRE = ? GROUP BY p.NOMBRE;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombre);  // Asigna el parámetro
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("Dispo");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "No encontrado";
    }*/
}
