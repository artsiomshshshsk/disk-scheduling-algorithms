package utils;

import java.util.ArrayList;
import java.util.Random;

public class QueryGenerator {
    private int origin;
    private int bound;
    private int maxDeadline;
    private boolean withAppereanceTime;

    public QueryGenerator(int origin, int bound, int maxDeadline, boolean withAppereanceTime) {
        this.origin = origin;
        this.bound = bound;
        this.maxDeadline = maxDeadline;
        this.withAppereanceTime = withAppereanceTime;
    }

    public ArrayList<Query> generate(final int amount){
        int realTimeProcesses = amount / 10;
        System.out.println("Number of real-time queries:" + realTimeProcesses);

        Random random = new Random();
        ArrayList<Query> all = new ArrayList<>();
        for(int i = 0; i < amount;i++){
//            Query query = new Query(random.nextInt(origin,bound),random.nextInt(0,maxDeadline), random.nextInt(bound * 3), random.nextBoolean());

            boolean realTime = false;
            if(realTimeProcesses-- > 0){
                realTime = true;
            }

            Query query;
            if(withAppereanceTime){
                query = new Query(random.nextInt(origin,bound),random.nextInt(0,maxDeadline), random.nextInt(bound * 10), realTime);
            }else{
                query = new Query(random.nextInt(origin,bound),random.nextInt(0,maxDeadline), 0, realTime);
            }
            System.out.println(query);
            all.add(query);
        }
        return all;
    }
}
