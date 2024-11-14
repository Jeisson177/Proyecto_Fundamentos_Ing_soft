package repository;


import java.sql.*;

public class AdminRepository {
    private static final Credenciales credenciales=new Credenciales();
    private static final String URL = credenciales.getURL();
    //private static final String URL  = "jdbc:mysql://localhost:3307/proyecto ingesoft";
    private static final String USER = credenciales.getUser();
    private static final String PASSWORD = credenciales.getPassword();

    public boolean AutentificatAdmin(String email, String contrasena){
        String query = "SELECT ID_USUARIO FROM usuario WHERE EMAIL = ? AND CONTRASENA = ? AND ROL = ?";
        String rol = "Administrador";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                // Asignación de parámetros
                stmt.setString(1, email);
                stmt.setString(2, contrasena);
                stmt.setString(3, rol);

                // Ejecutar la consulta y procesar el resultado
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return true;  // Usuario encontrado
                    } else {
                        return false;  // Usuario no encontrado o credenciales incorrectas
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
