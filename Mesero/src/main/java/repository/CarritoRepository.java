package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import controller.Plato;

public class CarritoRepository {
    private Connection connection;

    public CarritoRepository() {
        try {
            String url = "jdbc:mysql://localhost:3306/proyecto_ingesoft";
            String user = "root";
            String password = "cl";
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener un plato por su ID
    public Plato obtenerPlatoPorId(int idPlato) {
        Plato plato = null;
        String query = "SELECT * FROM PLATO WHERE ID_PLATO = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPlato);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("NOMBRE");
                double precio = resultSet.getDouble("PRECIO");
                plato = new Plato(idPlato, nombre, precio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plato;
    }
}
