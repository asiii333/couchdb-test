package com.bd.tests.simulation;

import com.bd.tests.data.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author majaschaefer
 */
public class Simulation {


    public static final int FIVE_MINUTES_IN_MILIS = 5 * 60 * 1000;
    private final DbManager manager;

    public Simulation(DbManager manager) {
        this.manager = manager;
    }


    /**
     * Insert experiment with clean database at the beginning.
     */
    public ExperimentResult startExperimentOne(int connectionsCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(connectionsCount);

        manager.cleanUp();
        List<Future<TaskResult>> results = new ArrayList<>();

        long start = System.nanoTime();

        for (int i = 0; i < 3; i++) {
            Record record = Record.newRandom(new Date().getTime());
            Callable<TaskResult> task = manager.returnInsertTask(record);
            Future<TaskResult> result = executorService.submit(task);
            results.add(result);
        }

        /*for (int i = 0; i < 40000; i++) {
            Record record = Record.newRandom(new Date().getTime());
            Callable<TaskResult> task = manager.returnInsertTask(record);
            Future<TaskResult> result = executorService.submit(task);
            results.add(result);
        }*/

        executorService.shutdown();
        long stop = System.nanoTime();

        return calculateResults(start, stop, results, connectionsCount);
    }

    /**
     * Insert experiment with populated databases at the beginning.
     */
    public ExperimentResult startExperimentOneExt(int connectionsCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(connectionsCount);

        manager.cleanUp();
        startExperimentOne(1);

        List<Future<TaskResult>> results = new ArrayList<>();

        long start = System.nanoTime();
        startExperimentOne(connectionsCount);
        executorService.shutdown();
        long stop = System.nanoTime();

        return calculateResults(start, stop, results, connectionsCount);
    }

    /**
     * Read experiment which reads total data volume.
     */
    public ExperimentResult startExperimentTwo(int connectionsCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(connectionsCount);

        startExperimentOne(1);

        List<Future<TaskResult>> results = new ArrayList<>();

        long start = System.nanoTime();

        //for (int i = 0; i < 40000; i++) {
            Callable<TaskResult> task = manager.returnAll();
            Future<TaskResult> result = executorService.submit(task);
            results.add(result);
        //}

        executorService.shutdown();
        long stop = System.nanoTime();

        return calculateResults(start, stop, results, connectionsCount);
    }

    /**
     * Read experiment which reads total volume with random access.
     */
    public ExperimentResult startExperimentTwoExt(int connectionsCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(connectionsCount);

        startExperimentOne(1);

        List<Future<TaskResult>> results = new ArrayList<>();

        long start = System.nanoTime();
        long startTime = System.currentTimeMillis() - FIVE_MINUTES_IN_MILIS;
        Random random = new Random();

        for (int i = 0; i < 40000; i++) {
            Callable<TaskResult> task = manager.returnQueryTask(startTime + random.nextInt(FIVE_MINUTES_IN_MILIS));
            Future<TaskResult> result = executorService.submit(task);
            results.add(result);
        }

        executorService.shutdown();
        long stop = System.nanoTime();

        return calculateResults(start, stop, results, connectionsCount);
    }

    public ExperimentResult[] startExperimentThree(int connectionsWrite, int connectionsRead) {
        Random random = new Random();

        ExecutorService executorServiceRead = Executors.newFixedThreadPool(connectionsRead);
        ExecutorService executorServiceWrite = Executors.newFixedThreadPool(connectionsWrite);



        startExperimentOne(1);

        List<Future<TaskResult>> resultsRead = new ArrayList<>();
        List<Future<TaskResult>> resultsWrite = new ArrayList<>();

        long startWrite = System.nanoTime();

        for (int i = 0; i < 40000; i++) {
            Record record = Record.newRandom( new Date().getTime());
            Callable<TaskResult> task = manager.returnInsertTask(record);
            Future<TaskResult> result = executorServiceWrite.submit(task);
            resultsWrite.add(result);
        }

        executorServiceWrite.shutdown();
        long stopWrite = System.nanoTime();

        ExperimentResult writeResults = calculateResults(startWrite, stopWrite, resultsWrite, connectionsWrite);



        long startRead = System.nanoTime();

        for (int i = 0; i < 40000; i++) {
            Callable<TaskResult> task = manager.returnQueryTask(Integer.toString(i));
            Future<TaskResult> result = executorServiceRead.submit(task);
            resultsRead.add(result);
        }

        executorServiceRead.shutdown();
        long stopRead = System.nanoTime();

        ExperimentResult readResults = calculateResults(startRead, stopRead, resultsRead, connectionsRead);

        ExperimentResult[] results = new ExperimentResult[2];
        results[0] = writeResults;
        results[1] = readResults;

        return results;




    }


    private ExperimentResult calculateResults(long start, long stop, List<Future<TaskResult>> results, int connections) {
        List<TaskResult> finalResults = results.stream().map(future -> {
            try {
                return future.get(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());


        TaskResult min = finalResults.stream().min(new InsertResultComparator()).orElse(new TaskResult(0));
        TaskResult max = finalResults.stream().max(new InsertResultComparator()).orElse(new TaskResult(0));

        double total = (stop - start);

        /*double recordsPerSecond = 40000.0 / total * 1000000.0;
        double sum = finalResults.stream().mapToDouble(TaskResult::getDuration).sum();

        double[] doubles = finalResults.stream().mapToDouble(TaskResult::getDuration).sorted().toArray();
        double median = (doubles[19999] + doubles[20000]) / 2.0;

        double averageTime = sum / 40000.0;*/

        double recordsPerSecond = 1000.0 / total * 1000000.0;
        double sum = finalResults.stream().mapToDouble(TaskResult::getDuration).sum();

        double[] doubles = finalResults.stream().mapToDouble(TaskResult::getDuration).sorted().toArray();
        double median = (doubles[499] + doubles[500]) / 2.0;

        double averageTime = sum / 1000.0;

        return new ExperimentResult(recordsPerSecond, averageTime, min.getDuration(), max.getDuration(), median, connections);
    }


}
