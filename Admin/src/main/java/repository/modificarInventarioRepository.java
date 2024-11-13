package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class modificarInventarioRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/proyecto ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "cl";

    public static class InventarioAlimento {
        private String nombre;
        private int cantidadInv;
        private Date fechaVen;
        private String unidad;

        public InventarioAlimento(String nombre, int cantidadInv, Date fechaVen, String unidad) {
            this.nombre = nombre;
            this.cantidadInv = cantidadInv;
            this.fechaVen = fechaVen;
            this.unidad = unidad;
        }

        // Getters
        public String getNombre() {
            return nombre;
        }

        public int getCantidadInv() {
            return cantidadInv;
        }

        public void setCantidadInv(int cantidadInv) {
            this.cantidadInv = cantidadInv;
        }

        public Date getFechaVen() {
            return fechaVen;
        }

        public String getUnidad() {
            return unidad;
        }

        @Override
        public String toString() {
            return "InventarioAlimento{" +
                    "nombre='" + nombre + '\'' +
                    ", cantidadInv=" + cantidadInv +
                    ", fechaVen=" + fechaVen +
                    ", unidad='" + unidad + '\'' +
                    '}';
        }
    }

    // Metodo para obtener todos los registros de inventario
    public List<InventarioAlimento> obtenerTodosLosInventarios() {
        List<InventarioAlimento> lista = new ArrayList<>();
        String query = "SELECT i.nombre, ii.cantidad_inv, ii.fecha_ven, ii.unidad " +
                "FROM INGREDIENTE i " +
                "JOIN INGREDIENTE_INV ii ON i.id_ingrediente = ii.id_ingrediente";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidadInv = rs.getInt("cantidad_inv");
                Date fechaVen = rs.getDate("fecha_ven");
                String unidad = rs.getString("unidad");

                lista.add(new InventarioAlimento(nombre, cantidadInv, fechaVen, unidad));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Metodo para agregar un ingrediente al inventario
    public void agregarIngrediente(InventarioAlimento ingrediente) {
        String query = "INSERT INTO INGREDIENTE_INV (id_ingrediente, cantidad_inv, fecha_ven, unidad) " +
                "VALUES ((SELECT id_ingrediente FROM INGREDIENTE WHERE nombre = ?), ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, ingrediente.getNombre());
            stmt.setInt(2, ingrediente.getCantidadInv());
            stmt.setDate(3, new java.sql.Date(ingrediente.getFechaVen().getTime()));
            stmt.setString(4, ingrediente.getUnidad());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para eliminar un ingrediente del inventario
    public void eliminarIngrediente(String nombre) {
        String query = "DELETE FROM INGREDIENTE_INV WHERE id_ingrediente = " +
                "(SELECT id_ingrediente FROM INGREDIENTE WHERE nombre = ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
