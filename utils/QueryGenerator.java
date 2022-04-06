package utils;

import java.util.ArrayList;
import java.util.Random;

public class QueryGenerator {
    private int origin;
    private int bound;
    private int maxDeadline;


    public QueryGenerator(int origin, int bound, int maxDeadline) {
        this.origin = origin;
        this.bound = bound;
        this.maxDeadline = maxDeadline;
    }

    public ArrayList<Query> generate(final int amount){
        Random random = new Random();

        ArrayList<Query> all = new ArrayList<>();
        for(int i = 0; i < amount;i++){
            Query query = new Query(random.nextInt(origin,bound),random.nextInt(0,maxDeadline), random.nextInt(amount));
            all.add(query);
        }
        return all;
    }
}
