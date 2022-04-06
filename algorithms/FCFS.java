package algorithms;

import Comparators.QueryAppearanceTimeComparator;
import utils.Query;
import utils.Queue;

import java.util.ArrayList;

public class FCFS extends SchedulingAlgorithm {
    @Override

    public void handleQueries(ArrayList<Query> queries) {
        super.handleQueries(queries);
        System.out.println("FCFS:");
        queries.sort(new QueryAppearanceTimeComparator());

        while(queries.size() != 0 || queryQueue.size() != 0){
            newQueries();
            if (queryQueue.size() != 0){
                Query query = queryQueue.pop();
                handleQuery(query);
            }else disk.setTime(disk.getTime() + 1);
        }
        System.out.println(disk.getHeadMovements());
    }
}
