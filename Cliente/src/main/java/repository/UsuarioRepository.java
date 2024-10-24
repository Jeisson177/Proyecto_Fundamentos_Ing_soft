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
    // Método para crear usuario
    public int CrearUsuario(String email, String contrasena, String nombre, String telefono) {
        String queryInsert = "INSERT INTO usuario (NOMBRE, EMAIL, TELEFONO, CONTRASENA, ROL) VALUES (?, ?, ?, ?, ?)";
        String rol = "Cliente";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS)) {
                // Asignación de parámetros
                stmt.setString(1, nombre);
                stmt.setString(2, email);
                stmt.setString(3, telefono);
                stmt.setString(4, contrasena);
                stmt.setString(5, rol);

                // Ejecutar la inserción
                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    // Obtener el ID generado
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            return generatedKeys.getInt(1);  // Devuelve el ID del nuevo usuario
                        }
                    }
                }
                return -1;  // Fallo al crear el usuario
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
