package repository.menu;

import repository.Credenciales;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MesaRepository {
    private static final Credenciales c=new Credenciales();

    private static final String URL = c.getURL();
    private static final String USER = c.getUser();
    private static final String PASSWORD = c.getPassword();
    public Map<Integer, Double[]> obtenerPosicionesMesas() {
        Map<Integer, Double[]> posiciones = new HashMap<>();
        String query = "SELECT ID_MESA, UBICACION_X, UBICACION_Y FROM MESA";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idMesa = resultSet.getInt("ID_MESA");
                double ubicacionX = resultSet.getDouble("UBICACION_X");
                double ubicacionY = resultSet.getDouble("UBICACION_Y");
                posiciones.put(idMesa, new Double[]{ubicacionX, ubicacionY});
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Puedes añadir un manejo de error específico aquí
        }

        return posiciones;
    }
}