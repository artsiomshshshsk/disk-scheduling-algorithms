package algorithms;

import Comparators.QueryAppearanceTimeComparator;
import Comparators.QueryDeadlineComparator;
import Comparators.QueryRealTimeComparator;
import utils.Disk;
import utils.Query;

import java.util.ArrayList;

public class FD_SCAN extends SchedulingAlgorithm{
    
    @Override
    public void handleQueries(ArrayList<Query> queries) {
        algorithmName = "FD-SCAN";
        System.out.println("FD-SCAN");

        Disk.QueryHeadLocationDistanceComparator queryHeadLocationDistanceComparator = getDisk().new QueryHeadLocationDistanceComparator();
        setQueries(queries);
        queries.sort(new QueryAppearanceTimeComparator());
        int deadLineFailed = 0;

        while (queries.size() != 0 || queryQueue.size() != 0) {
            newQueries();
            if(queryQueue.size() != 0){
                queryQueue.sort(new QueryDeadlineComparator());
//                queryQueue.sort(queryHeadLocationDistanceComparator);
                queryQueue.sort(new QueryRealTimeComparator());
                Query query = queryQueue.pop();
                if(getDifference(query) > query.getDeadline() && query.isRealTime()){
//                    System.out.println("Deadline failed:"+query);
                    deadLineFailed++; // odrzucamy
                }
                else {
                    handleQuery(query);
                }
            }else{
                disk.setTime(disk.getTime() + 1);
            }
        }

        System.out.println("Failed deadlines:"+deadLineFailed);

    }

    @Override
    public void handleQuery(Query query){
        handleBetweenHeadAndQuery(query);
        super.handleQuery(query);
    }


    public void handleBetweenHeadAndQuery(Query query){
        ArrayList<Query> toHandle = new ArrayList<>();

        newQueries(disk.getTime() + getDifference(query));
        if(query.getLocation() >= disk.getHeadLocation()){  // handle all queries between head and query (query on the right side of the head )
            for(Query queryHandle:queryQueue){
                if(queryHandle.getLocation() >= disk.getHeadLocation() && queryHandle.getLocation() <= query.getLocation() && !query.equals(queryHandle)){
                    toHandle.add(queryHandle);
                }
            }
        }else{//query on the left side of the head
            for(Query queryHandle:queryQueue){
                if(queryHandle.getLocation() <= disk.getHeadLocation() && queryHandle.getLocation() >= query.getLocation() && !query.equals(queryHandle)){
                    toHandle.add(queryHandle);
                }
            }
        }
        for(Query queryHandle: toHandle){
            super.handleQuery(queryHandle);
            queryQueue.remove(queryHandle);
        }
    }
}
