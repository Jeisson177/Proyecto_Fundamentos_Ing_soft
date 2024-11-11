package Init;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;

import persistencia.DBConnectionManager;
import persistencia.IDBConnectionManager;

public class DBInitializer {

    private IDBConnectionManager connMgr;

    public DBInitializer(IDBConnectionManager connMgr) {
        this.connMgr = connMgr;
    }

    public void initDB() {
        try (Connection conn = connMgr.getConnection()) {

            String sqlFile = "./db/Consulta.sql";
            RunScript.execute(conn, new FileReader(sqlFile));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DBInitializer(new DBConnectionManager()).initDB();
    }
}
