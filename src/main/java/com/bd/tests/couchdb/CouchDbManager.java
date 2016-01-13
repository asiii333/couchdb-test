package com.bd.tests.couchdb;

import com.bd.tests.data.DbManager;
import com.bd.tests.data.Record;
import com.bd.tests.data.RecordRepository;
import com.bd.tests.data.TaskResult;
import org.ektorp.DocumentNotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Asia on 2016-01-12.
 */
public class CouchDbManager implements DbManager {

    private CouchConnection connection = new CouchConnection();
    private RecordRepository data = new RecordRepository(connection.getConnection());

    @Override
    public void cleanUp() {
        //data.clear();
    }

    @Override
    public Callable<TaskResult> returnInsertTask(final Record record) {
        return () -> {
            long start = System.nanoTime();
            data.add(record);
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
            try{
                data.findByTimeStamp(timestamp);
            }catch(DocumentNotFoundException e){
                //donothing
            }

            long stop = System.nanoTime();
            return new TaskResult(stop - start);
        };
    }

    @Override
    public Callable<TaskResult> returnAll() {
        return () -> {
            long start = System.nanoTime();
            data.getAll();
            long stop = System.nanoTime();
            return new TaskResult(stop - start);
        };
    }

    public List<Record> getAllDocument(){
        return data.getAll();
    }
}
