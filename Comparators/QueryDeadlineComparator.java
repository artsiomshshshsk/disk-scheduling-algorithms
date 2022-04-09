package Comparators;

import utils.Query;

import java.util.Comparator;

public class QueryDeadlineComparator implements Comparator<Query> {
    @Override
    public int compare(Query o1, Query o2) {
        if(o1.getDeadline() > o2.getDeadline())return 1;
        else if(o1.getDeadline() < o2.getDeadline()) return -1;
        return 0;
    }
}
