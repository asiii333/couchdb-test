package com.bd.tests.data;

import java.util.concurrent.Callable;

/**
 * @author majaschaefer
 */
public interface DbManager {

    void cleanUp();

    Callable<TaskResult> returnInsertTask(Record record);

    Callable<TaskResult> returnQueryTask(String id);

    Callable<TaskResult> returnQueryTask(long timestamp);
}
