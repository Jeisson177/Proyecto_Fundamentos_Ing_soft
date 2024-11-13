package repository;

import javafx.beans.property.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class modificarPlatoRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/proyecto ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "cl";

    public static class Plato {
        private final IntegerProperty id;
        private final StringProperty nombre;
        private final StringProperty descripcion;
        private final IntegerProperty precio;
        private final StringProperty categoria;

        public Plato(int id, String nombre, String descripcion, int precio, String categoria) {
            this.id = new SimpleIntegerProperty(id);
            this.nombre = new SimpleStringProperty(nombre);
            this.descripcion = new SimpleStringProperty(descripcion);
            this.precio = new SimpleIntegerProperty(precio);
            this.categoria = new SimpleStringProperty(categoria);
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

        public String getDescripcion() {
            return descripcion.get();
        }

        public void setDescripcion(String descripcion) {
            this.descripcion.set(descripcion);
        }

        public int getPrecio() {
            return precio.get();
        }

        public void setPrecio(int precio) {
            this.precio.set(precio);
        }

        public String getCategoria() {
            return categoria.get();
        }

        public void setCategoria(String categoria) {
            this.categoria.set(categoria);
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public StringProperty nombreProperty() {
            return nombre;
        }

        public StringProperty descripcionProperty() {
            return descripcion;
        }

        public IntegerProperty precioProperty() {
            return precio;
        }

        public StringProperty categoriaProperty() {
            return categoria;
        }
    }

    public List<Plato> obtenerTodosLosPlatos() {
        List<Plato> platos = new ArrayList<>();
        String query = "SELECT id_plato, nombre, descripcion, precio, categoria FROM PLATO";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_plato");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int precio = rs.getInt("precio");
                String categoria = rs.getString("categoria");

                platos.add(new Plato(id, nombre, descripcion, precio, categoria));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platos;
    }

    public void actualizarPlato(Plato plato) {
        String query = "UPDATE PLATO SET nombre = ?, descripcion = ?, precio = ?, categoria = ? WHERE id_plato = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, plato.getNombre());
            stmt.setString(2, plato.getDescripcion());
            stmt.setInt(3, plato.getPrecio());
            stmt.setString(4, plato.getCategoria());
            stmt.setInt(5, plato.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarIngredienteDelPlato(int platoId, int ingredienteId) {
        String query = "DELETE FROM INGREDIENTE_PLATO WHERE id_plato = ? AND id_ingrediente = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, platoId);
            stmt.setInt(2, ingredienteId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static class Ingrediente {
        private final IntegerProperty id;
        private final StringProperty nombre;

        public Ingrediente(int id, String nombre) {
            this.id = new SimpleIntegerProperty(id);
            this.nombre = new SimpleStringProperty(nombre);
        }

        public int getId() {
            return id.get();
        }

        public String getNombre() {
            return nombre.get();
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public StringProperty nombreProperty() {
            return nombre;
        }
    }

    public List<Ingrediente> obtenerTodosLosIngredientes() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        String query = "SELECT id_ingrediente, nombre FROM INGREDIENTE";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_ingrediente");
                String nombre = rs.getString("nombre");

                ingredientes.add(new Ingrediente(id, nombre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientes;
    }

    public List<Ingrediente> obtenerIngredientesDePlato(int platoId) {
        List<Ingrediente> ingredientes = new ArrayList<>();
        String query = "SELECT i.id_ingrediente, i.nombre FROM INGREDIENTE i JOIN INGREDIENTE_PLATO ip ON i.id_ingrediente = ip.id_ingrediente WHERE ip.id_plato = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, platoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_ingrediente");
                String nombre = rs.getString("nombre");

                ingredientes.add(new Ingrediente(id, nombre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientes;
    }

    public void agregarIngredienteAlPlato(int platoId, int ingredienteId, int cantidad) {
        String query = "INSERT INTO INGREDIENTE_PLATO (id_plato, id_ingrediente, cantidad) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, platoId);
            stmt.setInt(2, ingredienteId);
            stmt.setInt(3, cantidad);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
