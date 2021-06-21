package barrier_server.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BarrierDB {
    public static final String DB_DIR = "src/main/db";
    public static final String DB_FILE = "barriers.db";
    public static final String DB_URL = "jdbc:sqlite:/" + DB_DIR + "/" + DB_FILE;

    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/barriers.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
