import barrier_server.server.BarrierSQL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class testBarrierSQL {

    public static void testPermittedBarriers(){
        String id = "id_1";
        BarrierSQL bsql = new BarrierSQL();
        ArrayList<Integer> res = bsql.getPermittedBarriers(id);
        res
                .forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        testPermittedBarriers();
    }

}
