package repository;

import javafx.beans.property.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class modificarUsuarioRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/proyecto ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "cl";

    public static class Usuario {
        private final IntegerProperty id;
        private final StringProperty nombre;
        private final StringProperty email;
        private final StringProperty telefono;
        private final StringProperty contrasena;

        public Usuario(int id, String nombre, String email, String telefono, String contrasena) {
            this.id = new SimpleIntegerProperty(id);
            this.nombre = new SimpleStringProperty(nombre);
            this.email = new SimpleStringProperty(email);
            this.telefono = new SimpleStringProperty(telefono);
            this.contrasena = new SimpleStringProperty(contrasena);
        }

        public int getId() {
            return id.get();
        }

        public String getNombre() {
            return nombre.get();
        }

        public void setNombre(String nombre) {
            this.nombre.set(nombre);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String email) {
            this.email.set(email);
        }

        public String getTelefono() {
            return telefono.get();
        }

        public void setTelefono(String telefono) {
            this.telefono.set(telefono);
        }

        public String getContrasena() {
            return contrasena.get();
        }

        public void setContrasena(String contrasena) {
            this.contrasena.set(contrasena);
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public StringProperty nombreProperty() {
            return nombre;
        }

        public StringProperty emailProperty() {
            return email;
        }

        public StringProperty telefonoProperty() {
            return telefono;
        }

        public StringProperty contrasenaProperty() {
            return contrasena;
        }
    }

    public List<Usuario> obtenerTodosLosMeseros() {
        List<Usuario> meseros = new ArrayList<>();
        String query = "SELECT ID_USUARIO, NOMBRE, EMAIL, TELEFONO, CONTRASENA FROM Usuario WHERE ROL = 'Mesero'";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_USUARIO");
                String nombre = rs.getString("NOMBRE");
                String email = rs.getString("EMAIL");
                String telefono = rs.getString("TELEFONO");
                String contrasena = rs.getString("CONTRASENA");

                meseros.add(new Usuario(id, nombre, email, telefono, contrasena));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meseros;
    }

    public void actualizarMesero(Usuario mesero) {
        String query = "UPDATE Usuario SET NOMBRE = ?, EMAIL = ?, TELEFONO = ?, CONTRASENA = ? WHERE ID_USUARIO = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, mesero.getNombre());
            stmt.setString(2, mesero.getEmail());
            stmt.setString(3, mesero.getTelefono());
            stmt.setString(4, mesero.getContrasena());
            stmt.setInt(5, mesero.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarMesero(int meseroId) {
        String query = "DELETE FROM Usuario WHERE ID_USUARIO = ? AND ROL = 'Mesero'";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, meseroId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarMesero(String nombre, String email, String telefono, String contrasena) {
        String query = "INSERT INTO Usuario (NOMBRE, EMAIL, TELEFONO, ROL, CONTRASENA) VALUES (?, ?, ?, 'Mesero', ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, telefono);
            stmt.setString(4, contrasena);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}