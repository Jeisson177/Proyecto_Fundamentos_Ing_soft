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
}
