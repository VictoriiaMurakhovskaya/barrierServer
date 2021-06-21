package barrier_server.server;

import barrier_server.Barrier;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BarrierSQL {

    final static String DB_DIR = "/src/main/db";
    final static String DB_FILE = "barriers.db";

    static String DB_URL = "";

    public BarrierSQL(){
        String cwd = System.getProperty("user.dir");
        Path cwdPath = Paths.get(cwd);
        DB_URL = "jdbc:sqlite:" + cwdPath + DB_DIR + "/" + DB_FILE;
    }

    public Connection connect() {
        try {
            // create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL);

            System.out.println("Connection to SQLite has been established.");

            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean addUser(String login, String id){
        String sql = "INSERT INTO users (login, id) values (?, ?)";

        try (Connection conn = this.connect()){

            // вставка данных
            PreparedStatement ptstmt = conn.prepareStatement(sql);
            ptstmt.setString(1, login);
            ptstmt.setString(2, id);
            ptstmt.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Integer> getPermittedBarriers(String id){
        String sql = "SELECT barrier_id FROM permissions WHERE user_id=?";

        try (Connection conn = this.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            ArrayList<Integer> barriers = new ArrayList<>();
            while ( rs.next() ){
                barriers.add(rs.getInt("barrier_id"));
            }
            return barriers;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Barrier> getBarriersList(ArrayList<Integer> lst){

        String sql = "SELECT id, longitude, latitude FROM barriers WHERE id=?";
        ResultSet rs;

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            ArrayList<Barrier> res = new ArrayList<Barrier>();

            for (Integer n:lst
                 ) {
                stmt.setInt(1, n);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    res.add(new Barrier((short) rs.getInt("id"),
                            rs.getDouble("longitude"),
                            rs.getDouble("latitude")));
                }
            }

            return res;


        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
