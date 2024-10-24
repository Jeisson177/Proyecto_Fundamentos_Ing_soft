package repository;

import java.sql.*;

public class UsuarioRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto ingesoft";
    //private static final String URL  = "jdbc:mysql://localhost:3307/proyecto ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "cl";
    public int AutentificarUsuario(String email, String contrasena) {
        String query = "SELECT ID_USUARIO FROM usuario WHERE EMAIL = ? AND CONTRASENA = ? AND ROL = ?";
        String rol = "Cliente";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                // Asignación de parámetros
                stmt.setString(1, email);
                stmt.setString(2, contrasena);
                stmt.setString(3, rol);

                // Ejecutar la consulta y procesar el resultado
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("ID_USUARIO");  // Devuelve el ID del usuario autenticado
                    } else {
                        return -1;  // Usuario no encontrado o credenciales incorrectas
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
