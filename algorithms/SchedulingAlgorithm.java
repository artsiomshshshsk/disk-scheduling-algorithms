package algorithms;

import utils.Disk;
import utils.Query;
import utils.Queue;

import java.util.ArrayList;

public abstract class SchedulingAlgorithm {
    protected Disk disk;
    protected ArrayList<Query> queries;
    protected Queue<Query> queryQueue;
    protected String algorithmName;

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setQueries(ArrayList<Query> queries) {
        this.queries = queries;
    }

    public SchedulingAlgorithm() {
        queryQueue = new Queue<>();
    }

    public abstract void handleQueries(ArrayList<Query> queries);

    public void handleQuery(Query query){
//        System.out.println("handle:"+query);
        int distanceHeadQuery = getDifference(query);
        disk.setTime(disk.getTime() + distanceHeadQuery);
        disk.setHeadLocation(query.getLocation());
        disk.setHeadMovements(disk.getHeadMovements() + distanceHeadQuery);
    }

    protected int getDifference(Query query){
        return Math.abs(disk.getHeadLocation() - query.getLocation());
    }

    public void newQueries(){
        newQueries(disk.getTime());
    }
    public void newQueries(int time ){
        int num = 0;
        for(int i = 0; i < queries.size(); i++){
            Query query = queries.get(i);
            if(query.getAppearanceTime() <= time) {
                queryQueue.push(query);
                num++;
            }
        }
        while(num-- > 0) queries.remove(0);
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }
}
