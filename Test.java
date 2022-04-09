import algorithms.*;
import utils.Disk;
import utils.Query;
import utils.QueryGenerator;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        int diskSize = 300;
        int queriesNumber = 30;

        QueryGenerator generator = new QueryGenerator(0,diskSize,diskSize/3, true);
        ArrayList<Query> queries = generator.generate(queriesNumber);
        System.out.println("Number of queries:" +queriesNumber );
        System.out.println("_____________________________________");

        Disk disk = new Disk(new FCFS(), diskSize);
        disk.handleQueries(new ArrayList<>(queries));

        System.out.println("_____________________________________");

        disk.setSchedulingAlgorithm(new SSTF());
        disk.handleQueries(new ArrayList<>(queries));

        System.out.println("_____________________________________");

        disk.setSchedulingAlgorithm(new SCAN());
        disk.handleQueries(new ArrayList<>(queries));

        System.out.println("_____________________________________");

        disk.setSchedulingAlgorithm(new C_SCAN());
        disk.handleQueries(new ArrayList<>(queries));

        System.out.println("_____________________________________");

        disk.setSchedulingAlgorithm(new EDF());
        disk.handleQueries(new ArrayList<>(queries));

        System.out.println("_____________________________________");

        disk.setSchedulingAlgorithm(new FD_SCAN());
        disk.handleQueries(new ArrayList<>(queries));


    }
}
