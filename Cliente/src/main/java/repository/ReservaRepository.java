package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/proyecto ingesoft";
    private static final String USER = "root";
    private static final String PASSWORD = "Basedatos";

    // Método para verificar si una fecha está ocupada
    public boolean FechaOcupada(String fechaConsulta) {
        String query = "SELECT COUNT(*) AS total FROM reserva WHERE FECHA_HORA = '" + fechaConsulta + "'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int totalReservas = rs.getInt("total");
                // Si hay más de 0 reservas en esa fecha, entonces está ocupada
                return totalReservas > 0;//tengo que comparar con el numero de mesas
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<String> obtenerFechasDisponibles() {
        List<String> fechasDisponibles = new ArrayList<>();
        String query = "SELECT DISTINCT FECHA_HORA FROM reserva WHERE ESTADO != 'Confirmada'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String fechaDisponible = rs.getString("FECHA_HORA");
                fechasDisponibles.add(fechaDisponible);  // Agregar la fecha a la lista
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fechasDisponibles;  // Retornar la lista de fechas disponibles
    }

}

