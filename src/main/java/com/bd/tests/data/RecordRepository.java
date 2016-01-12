package com.bd.tests.data;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

/**
 * Created by Asia on 2016-01-12.
 */
public class RecordRepository extends CouchDbRepositorySupport<Record> {
    public RecordRepository(CouchDbConnector db) {
        super(Record.class, db);
    }
}
