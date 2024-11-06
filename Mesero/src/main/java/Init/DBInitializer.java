package Init;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import org.h2.tools.RunScript;
import repository.DBConnectionManager;

public class DBInitializer {

    private DBConnectionManager connMgr;

    public DBInitializer(DBConnectionManager connMgr) {
        this.connMgr = connMgr;
    }

    public void initDB() {
        try (Connection conn = connMgr.getConnection();) {
            String sqlFile = this.getClass().getResource("/Consulta.sql").getFile();
            RunScript.execute(conn, new FileReader(sqlFile));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DBInitializer(new DBConnectionManager()).initDB();
    }
}