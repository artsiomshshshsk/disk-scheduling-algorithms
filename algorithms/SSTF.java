package algorithms;

import Comparators.QueryAppearanceTimeComparator;
import utils.Disk;
import utils.Query;
import utils.Queue;

import java.util.ArrayList;
import java.util.Comparator;

public class SSTF extends SchedulingAlgorithm{ // Shortest Seek Time First
    @Override
    public void handleQueries(ArrayList<Query> queries) {
        algorithmName = "SSTF";
        setQueries(queries);
        Disk.QueryHeadLocationDistanceComparator queryHeadLocationDistanceComparator = getDisk().new QueryHeadLocationDistanceComparator();
        System.out.println("SSTF:");
        queries.sort(new QueryAppearanceTimeComparator());

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
    }

}
