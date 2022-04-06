package algorithms;

import utils.Disk;
import utils.Query;
import utils.Queue;

import java.util.ArrayList;
import java.util.Comparator;

public class SSTF extends SchedulingAlgorithm{ // Shortest Seek Time First
    @Override
    public void handleQueries(ArrayList<Query> queries) {
        super.handleQueries(queries);
        Disk.QueryHeadLocationDistanceComparator queryHeadLocationDistanceComparator = getDisk().new QueryHeadLocationDistanceComparator();
        System.out.println("SSTF:");

        while(queries.size() != 0 || queryQueue.size() != 0){
            newQueries();
            if(queryQueue.size() != 0){
                queryQueue.sort(queryHeadLocationDistanceComparator);
                Query query = queryQueue.pop();
                handleQuery(query);
            }else{
                disk.setTime(disk.getTime() + 1);
            }
        }
        System.out.println(disk.getHeadMovements());
    }

}
