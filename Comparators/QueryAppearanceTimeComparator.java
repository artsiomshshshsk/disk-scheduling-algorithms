package Comparators;

import utils.Query;

import java.util.Comparator;

public class QueryAppearanceTimeComparator implements Comparator<Query> {
    @Override
    public int compare(Query o1, Query o2) {
        if(o1.getAppearanceTime() > o2.getAppearanceTime())return 1;
        else if(o1.getAppearanceTime() < o2.getAppearanceTime()) return -1;
        return 0;
    }
}
