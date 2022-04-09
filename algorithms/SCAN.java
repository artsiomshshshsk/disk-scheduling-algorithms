package algorithms;

import Comparators.QueryAppearanceTimeComparator;
import utils.Disk;
import utils.Query;

import java.util.ArrayList;
import java.util.Random;

public class SCAN extends SchedulingAlgorithm {
    @Override
    public void handleQueries(ArrayList<Query> queries) {
        algorithmName = "SCAN";
        System.out.println("SCAN");
        setQueries(queries);
        Random r = new Random();

        Disk.QueryHeadLocationDistanceComparator queryHeadLocationDistanceComparator = getDisk().new QueryHeadLocationDistanceComparator();

        boolean moveToRight = r.nextBoolean();

        while(queries.size() != 0 || queryQueue.size() != 0){
            if(moveToRight){
                int startHeadPosition = disk.getHeadLocation();

                newQueries(disk.getTime() + disk.getDiskSize() - disk.getHeadLocation());
                if(queryQueue.size() != 0){
                    queryQueue.sort(queryHeadLocationDistanceComparator);
                    int i = queryQueue.size();
                    while(i-- != 0){
                        Query query = queryQueue.first();
                        if(query.getLocation() >= startHeadPosition){
                            handleQuery(queryQueue.pop());
                        }else{
                            queryQueue.add(queryQueue.pop());
                        }
                    }
                }
                if(queries.size() != 0|| queryQueue.size() != 0){
                    disk.setTime(disk.getTime() + (disk.getDiskSize() - disk.getHeadLocation()));
                    disk.setHeadMovements(disk.getHeadMovements() + (disk.getDiskSize() - disk.getHeadLocation()));
                    disk.setHeadLocation(disk.getDiskSize());
                    moveToRight = false;
                }

            }else{  // move to left
                int startHeadPosition = disk.getHeadLocation();
                newQueries( disk.getTime() + disk.getHeadLocation());
                if(queryQueue.size() != 0){
                    queryQueue.sort(queryHeadLocationDistanceComparator);
                    int i = queryQueue.size();
                    while(i-- != 0){
                        Query query = queryQueue.first();
                        if(query.getLocation() <= startHeadPosition){
                            handleQuery(queryQueue.pop());
                        }else{
                            queryQueue.add(queryQueue.pop());
                        }
                    }
                }

                if(queries.size() != 0 || queryQueue.size() != 0){
                    disk.setTime(disk.getTime() + disk.getHeadLocation());
                    disk.setHeadMovements(disk.getHeadMovements() + disk.getHeadLocation());
                    disk.setHeadLocation(0);
                    moveToRight = true;
                }
            }
        }

    }
}
