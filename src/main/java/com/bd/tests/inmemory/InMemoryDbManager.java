package com.bd.tests.inmemory;

import com.bd.tests.couchdb.CouchConnection;
import com.bd.tests.data.DbManager;
import com.bd.tests.data.RecordRepository;
import com.bd.tests.data.TaskResult;
import com.bd.tests.data.Record;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author majaschaefer
 */
public abstract class InMemoryDbManager implements DbManager {

    private final Map<String, Record> data = new ConcurrentHashMap<String, Record>();

    @Override
    public void cleanUp() {
        //data.clear();
    }

    @Override
    public Callable<TaskResult> returnInsertTask(final Record record) {
        return () -> {
            long start = System.nanoTime();
            data.put(record.getId(),record);
            long stop = System.nanoTime();
            return new TaskResult(stop - start);
        };
    }

    @Override
    public Callable<TaskResult> returnQueryTask(String id) {
        return () -> {
            long start = System.nanoTime();
            data.get(id);
            long stop = System.nanoTime();
            return new TaskResult(stop - start);
        };
    }

    @Override
    public Callable<TaskResult> returnQueryTask(long timestamp) {
        return () -> {
            long start = System.nanoTime();
            //data.get(timestamp);
            long stop = System.nanoTime();
            return new TaskResult(stop - start);
        };
    }
}
