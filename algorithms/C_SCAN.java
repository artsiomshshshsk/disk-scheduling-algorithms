package algorithms;

import utils.Disk;
import utils.Query;

import java.util.ArrayList;

public class C_SCAN extends SchedulingAlgorithm{


    @Override
    public void handleQueries(ArrayList<Query> queries) {
        algorithmName = "C-SCAN";
        System.out.println("C-SCAN");
        setQueries(queries);
        Disk.QueryHeadLocationDistanceComparator queryHeadLocationDistanceComparator = getDisk().new QueryHeadLocationDistanceComparator();

        while(queries.size() != 0 || queryQueue.size() != 0) {
            int startHeadPosition = disk.getHeadLocation();
            newQueries(disk.getTime() + disk.getDiskSize() - disk.getHeadLocation());
            if (queryQueue.size() != 0) {
                queryQueue.sort(queryHeadLocationDistanceComparator);
                int i = queryQueue.size();
                while (i-- != 0) {
                    Query query = queryQueue.first();
                    if (query.getLocation() >= startHeadPosition) {
                        handleQuery(queryQueue.pop());
                    } else {
                        queryQueue.add(queryQueue.pop());
                    }
                }
            }
//            }else{
//                disk.setTime(disk.getTime() + disk.getDiskSize() - disk.getHeadLocation());
//                disk.setHeadMovements(disk.getHeadMovements() + (disk.getDiskSize() - disk.getHeadLocation()));
//                disk.setHeadLocation(disk.getDiskSize());
//            }
            if (queries.size() != 0 || queryQueue.size() != 0) {
                disk.setTime(disk.getTime() + disk.getDiskSize() - disk.getHeadLocation());
                disk.setHeadMovements(disk.getHeadMovements() + (disk.getDiskSize() - disk.getHeadLocation()));
                disk.setHeadLocation(disk.getDiskSize());
//                disk.setTime(disk.getTime() + (disk.getDiskSize() - disk.getHeadLocation()));
//                disk.setHeadMovements(disk.getHeadMovements() + (disk.getDiskSize() - disk.getHeadLocation()));
                disk.setHeadLocation(0);
            }
        }
    }
}