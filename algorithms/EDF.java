package algorithms;

import Comparators.QueryAppearanceTimeComparator;
import Comparators.QueryDeadlineComparator;
import Comparators.QueryRealTimeComparator;
import utils.Query;

import java.util.ArrayList;

public class EDF extends SchedulingAlgorithm{
    @Override
    public void handleQueries(ArrayList<Query> queries) {
        algorithmName = "EDF";
        System.out.println("EDF");
        setQueries(queries);
        queries.sort(new QueryAppearanceTimeComparator());

        int deadLineFailed = 0;

        while (queries.size() != 0 || queryQueue.size() != 0) {
            newQueries();
            if(queryQueue.size() != 0){
                queryQueue.sort(new QueryDeadlineComparator());
                queryQueue.sort(new QueryRealTimeComparator());
                Query query = queryQueue.pop();
                if(getDifference(query) > query.getDeadline() && query.isRealTime()){
                    deadLineFailed++;
//                    System.out.println("Deadline Failed:"+ query);
                }
                handleQuery(query);
            }else{
                disk.setTime(disk.getTime() + 1);
            }
        }

        System.out.println("Failed deadlines:"+deadLineFailed);
    }
}
