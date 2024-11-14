package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;

public class modifificarRepository {
    private static final Credenciales credenciales=new Credenciales();
    private static final String url = credenciales.getURL();
    //private static final String URL  = "jdbc:mysql://localhost:3307/proyecto ingesoft";
    private static final String user = credenciales.getUser();
    private static final String password = credenciales.getPassword();

    public Map<Integer, Point2D> obtenerPosicionesMesas() throws SQLException {
        Map<Integer, Point2D> posicionesMesas = new HashMap<>();
        String query = "SELECT ID_MESA, UBICACION_X, UBICACION_Y FROM MESA";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idMesa = resultSet.getInt("ID_MESA");
                double ubicacionX = resultSet.getDouble("UBICACION_X");
                double ubicacionY = resultSet.getDouble("UBICACION_Y");
                posicionesMesas.put(idMesa, new Point2D(ubicacionX, ubicacionY));
            }
        }
        return posicionesMesas;
    }

    public Point2D obtenerPosicionMesa(int idMesa) throws SQLException {
        String query = "SELECT UBICACION_X, UBICACION_Y FROM MESA WHERE ID_MESA = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idMesa);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double ubicacionX = resultSet.getDouble("UBICACION_X");
                double ubicacionY = resultSet.getDouble("UBICACION_Y");
                return new Point2D(ubicacionX, ubicacionY);
            }
        }
        return null;
    }

    // Actualizar posición de una mesa en la base de datos
    public void actualizarPosicionMesa(int idMesa, double nuevaX, double nuevaY) throws SQLException {
        String query = "UPDATE MESA SET UBICACION_X = ?, UBICACION_Y = ? WHERE ID_MESA = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, nuevaX);
            statement.setDouble(2, nuevaY);
            statement.setInt(3, idMesa);

            statement.executeUpdate();
        }
    }


}
