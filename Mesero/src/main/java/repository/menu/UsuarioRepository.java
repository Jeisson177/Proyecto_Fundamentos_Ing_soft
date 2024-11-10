package repository.menu;

import repository.Credenciales;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private static final Credenciales c=new Credenciales();

    private static final String URL = c.getURL();
    private static final String USER = c.getUser();
    private static final String PASSWORD = c.getPassword();
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
        // Validación de campos vacíos
        if (email == null || email.isEmpty() ||
                contrasena == null || contrasena.isEmpty() ||
                nombre == null || nombre.isEmpty() ||
                telefono == null || telefono.isEmpty()) {

            System.out.println("Error: Todos los campos deben estar completos.");
            return -1; // Indica fallo debido a campos vacíos
        }

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
    //Método ConsultarReservas
    public List<String> ConsultarReservas(int idUsuario) {
        String query = "SELECT ID_RESERVA, ID_MESA, FECHA_HORA FROM RESERVA WHERE ID_CLIENTE = ?";
        List<String> reservas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idUsuario);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        // Formatear la información de cada reserva
                        String reserva = "Reserva ID: " + rs.getInt("ID_RESERVA") +
                                ", Mesa: " + rs.getInt("ID_MESA") +
                                ", Fecha y Hora: " + rs.getTimestamp("FECHA_HORA").toString();
                        reservas.add(reserva);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

}
