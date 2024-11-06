package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private static final String JDBC_URL = "jdbc:h2:./banco";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public DBConnectionManager() {
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./banco", "sa", "");
    }
}
