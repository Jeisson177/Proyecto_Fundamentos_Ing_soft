package repository;

import java.sql.*;
import java.time.LocalTime;

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

        // Método toString() para facilitar la impresión de los datos
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


    // Método para obtener el inventario y el nombre de un alimento específico
    public InventarioAlimento obtenerInventarioAlimento(String nombreAlimento) {
        String query = "SELECT i.nombre, ii.cantidad_inv, ii.fecha_ven, ii.unidad " +
                "FROM INGREDIENTE i " +
                "JOIN INGREDIENTE_INV ii ON i.id_ingrediente = ii.id_ingrediente " +
                "WHERE i.nombre = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, nombreAlimento);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    int cantidadInv = rs.getInt("cantidad_inv");
                    Date fechaVen = rs.getDate("fecha_ven");
                    String unidad = rs.getString("unidad");
                    return new InventarioAlimento(nombre, cantidadInv, fechaVen, unidad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
