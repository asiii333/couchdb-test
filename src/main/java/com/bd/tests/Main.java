package com.bd.tests;

import com.bd.tests.couchdb.CouchDbManager;
import com.bd.tests.data.DbManager;
import com.bd.tests.data.ExperimentResult;
import com.bd.tests.simulation.Simulation;

/**
 * @author majaschaefer
 */
public class Main {

    public static void main(String[] args) {
        DbManager inMemoryDbManager = new CouchDbManager();
        Simulation simulation = new Simulation(inMemoryDbManager);

        //System.out.println("Experiment 1");
        //printResults(simulation.startExperimentOne(1));
//        printResults(simulation.startExperimentOne(2));
////        printResults(simulation.startExperimentOne(4));
////        printResults(simulation.startExperimentOne(8));
////        printResults(simulation.startExperimentOne(16));
////        printResults(simulation.startExperimentOne(32));
////        printResults(simulation.startExperimentOne(64));
//
//
        System.out.println("Experiment 2");
        printResults(simulation.startExperimentTwo(1));
//        printResults(simulation.startExperimentTwo(2));
////        printResults(simulation.startExperimentTwo(4));
////        printResults(simulation.startExperimentTwo(8));
////        printResults(simulation.startExperimentTwo(16));
////        printResults(simulation.startExperimentTwo(32));
////        printResults(simulation.startExperimentTwo(64));


/*        System.out.println("Experitment 3");
        ExperimentResult[] results = simulation.startExperimentThree(32, 8);
        System.out.println("Write results: ");
        printResults(results[0]);
        System.out.println("Read results: ");
        printResults(results[1]);*/
    }

    private static void printResults(ExperimentResult experimentResult) {
        System.out.println("Connections count: " + experimentResult.getConnections());
        System.out.println("Records per second: " + experimentResult.getRecordsPerSecond());
        System.out.println("Min time: " + experimentResult.getMinTime());
        System.out.println("Max time: " + experimentResult.getMaxTime());
        System.out.println("Average time: " + experimentResult.getAverageTime());
        System.out.println("Median time: " + experimentResult.getMedTime());
    }

}
