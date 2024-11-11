package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager implements IDBConnectionManager {

    private static final String JDBC_URL = "jdbc:h2:file:C:/Users/lenovo1/Documents/GitHub/Proyecto_Fundamentos_Ing_soft/db/Cliente";

    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public DBConnectionManager() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        // return dataSource.getConnection();
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

}
