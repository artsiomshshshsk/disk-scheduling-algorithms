package utils;

import DataVizualization.HeadMovement;
import algorithms.SchedulingAlgorithm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Disk {
    private SchedulingAlgorithm schedulingAlgorithm;
    private int headMovements;
    private int headLocation;
    private final int startHeadLocation;
    private final int diskSize;
    private int time;
    private ArrayList<HeadMovement> headMovementsLog;

    public Disk(SchedulingAlgorithm schedulingAlgorithm, int diskSize) {
        this.schedulingAlgorithm = schedulingAlgorithm;
        this.diskSize = diskSize;
        Random r = new Random();
        startHeadLocation = r.nextInt(diskSize);
        System.out.println("Start head location:" + startHeadLocation);
    }

    public void handleQueries(ArrayList<Query> queries){
        headMovementsLog = new ArrayList<>();
        headMovementsLog.add(new HeadMovement(0,startHeadLocation));

        headMovements = 0;
        headLocation = startHeadLocation;
        time = 0;

        schedulingAlgorithm.setDisk(this);
        schedulingAlgorithm.handleQueries(queries);
        log();
        System.out.println("Head movements:"+getHeadMovements());
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
//        headMovementsLog.add(new HeadMovement(time, getHeadLocation()));
        this.time = time;
    }

    public void setHeadMovements(int headMovements) {
        this.headMovements = headMovements;
    }

    public void setHeadLocation(int headLocation) {
        headMovementsLog.add(new HeadMovement(time,headLocation));
//        System.out.println("_____________________________headLocation________________");
//        System.out.println("time:"+ time);
//        System.out.println("current:"+this.headLocation);
//        System.out.println("changed to:" + headLocation);
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

    public void log(){
        String fileName = "DataVizualization/" + schedulingAlgorithm.getAlgorithmName() + ".txt";
        File file = new File(fileName);
        try(FileWriter fileWriter = new FileWriter(file)){
            for(HeadMovement headMovement:headMovementsLog){
                fileWriter.write(headMovement.getTime() + " " + headMovement.getLocation() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
