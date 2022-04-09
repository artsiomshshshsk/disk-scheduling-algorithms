package Comparators;

import utils.Query;

import java.util.Comparator;

public class QueryRealTimeComparator implements Comparator<Query> {
    @Override
    public int compare(Query o1, Query o2) {
        if(!o1.isRealTime() && o2.isRealTime()){
            return 1;
        }else if(o1.isRealTime() && !o2.isRealTime()){
            return -1;
        }
        return 0;
    }
}
