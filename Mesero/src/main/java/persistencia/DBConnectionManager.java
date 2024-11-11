package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager implements IDBConnectionManager {

    private static final String JDBC_URL = "jdbc:h2:./banco";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        // return dataSource.getConnection();
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

}
