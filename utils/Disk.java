package utils;

import algorithms.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.Comparator;

public class Disk {
    private SchedulingAlgorithm schedulingAlgorithm;
    private int headMovements;
    private int headLocation;
    private final int diskSize;
    private int time;

    public Disk(SchedulingAlgorithm schedulingAlgorithm, int diskSize) {
        this.schedulingAlgorithm = schedulingAlgorithm;
        this.diskSize = diskSize;
    }

    public void handleQueries(ArrayList<Query> queries){
        headMovements = 0;
        headLocation = diskSize / 2;
        time = 0;

        schedulingAlgorithm.setDisk(this);
        schedulingAlgorithm.handleQueries(queries);
    }

    public SchedulingAlgorithm getSchedulingAlgorithm() {
        return schedulingAlgorithm;
    }

    public int getHeadMovements() {
        return headMovements;
    }

    public int getHeadLocation() {
//        System.out.println("current head location:" + headLocation);
        return headLocation;
    }

    public int getDiskSize() {
        return diskSize;
    }

    public void setSchedulingAlgorithm(SchedulingAlgorithm schedulingAlgorithm) {
        this.schedulingAlgorithm = schedulingAlgorithm;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setHeadMovements(int headMovements) {
        this.headMovements = headMovements;
    }

    public void setHeadLocation(int headLocation) {
        this.headLocation = headLocation;
    }

    public class QueryHeadLocationDistanceComparator implements Comparator<Query> {
        @Override
        public int compare(Query o1, Query o2) {
            if(Math.abs(o1.getLocation() - headLocation) > Math.abs(o2.getLocation() - headLocation)) return 1;
            else if(Math.abs(o1.getLocation() - headLocation) < Math.abs(o2.getLocation() - headLocation)) return -1;
            return 0;
        }
    }
}
