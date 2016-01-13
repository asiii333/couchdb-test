package com.bd.tests.data;

import org.ektorp.CouchDbConnector;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asia on 2016-01-12.
 */
public class RecordRepository extends CouchDbRepositorySupport<Record> {
    public RecordRepository(CouchDbConnector db) {
        super(Record.class, db);
    }

    @GenerateView
    public List<Record> findByTimeStamp(long timestamp) {
        int intTimpeStamp = (int)timestamp;
        List<Record> result = null;
        try {
            result = queryView("by_timestamp", intTimpeStamp);
        }catch(DocumentNotFoundException e){

        }
        return  result;
    }
}
