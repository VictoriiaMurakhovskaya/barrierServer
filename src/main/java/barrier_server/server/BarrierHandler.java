package barrier_server.server;

import java.util.ArrayList;
import java.util.List;

import barrier_server.Barrier;
import barrier_server.BarrierService;

public class BarrierHandler implements BarrierService.Iface {

    @Override
    public boolean addUser(String login, String id){
        System.out.printf("Adding user... login: %s, id:%s", login, id);
        BarrierSQL bsql = new BarrierSQL();
        return bsql.addUser(login, id);
    }

    @Override
    public boolean openBarrier(String id, double longitude, double latitude){

        ArrayList<Barrier> allowedBarriers;
        double barrierDistance;

        System.out.printf("Open barrier id:%s", id);
        BarrierSQL bsql = new BarrierSQL();

        ArrayList<Integer> permittedBarriers = bsql.getPermittedBarriers(id);
        allowedBarriers = bsql.getBarriersList(permittedBarriers);

        for (Barrier barrier:allowedBarriers
             ) {
            barrierDistance = defineDistance(longitude, latitude, barrier.longitude, barrier.latitude);
            if (barrierDistance < 50){
                System.out.printf("%n Barrier with ID %d is in %f meters", barrier.id, barrierDistance);
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Barrier> getBarrierList(String id){
        return null;
    }

    private static double defineDistance(double long1, double lat1, double long2, double lat2){
        // дуга меридиана в один градус равна 111 км = 111000 м
        // для расчета длины параллели необходимо учитывать широту
        return Math.sqrt(Math.pow((long1 - long2) * 111000, 2) +
               Math.pow((lat1 - lat2) * 111000 * Math.cos((lat1 + lat2) / 2) * 3.1415 / 180, 2));
    }


}
