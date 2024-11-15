package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Plato;

public class CarritoRepository {
    private Connection connection;

    private static final Credenciales c = new Credenciales();
    private static final String URL = c.getURL();
    private static final String USER = c.getUser();
    private static final String PASSWORD = c.getPassword();

    public CarritoRepository() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos");
        }
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plato;
    }

    public void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
