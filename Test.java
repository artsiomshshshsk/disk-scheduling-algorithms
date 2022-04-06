import algorithms.FCFS;
import algorithms.SSTF;
import utils.Disk;
import utils.Query;
import utils.QueryGenerator;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        QueryGenerator generator = new QueryGenerator(0,100,100);
        ArrayList<Query> queries = generator.generate(10);
        Disk disk = new Disk(new FCFS(), 100);
        disk.handleQueries(new ArrayList<>(queries));

        disk.setSchedulingAlgorithm(new SSTF());
        disk.handleQueries(new ArrayList<>(queries));

    }
}
