package com.bd.tests.data

import com.bd.tests.couchdb.CouchConnection
import spock.lang.Specification

/**
 * Created by Asia on 2016-01-13.
 */
class RecordRepositoryTest extends Specification {
    CouchConnection connection = new CouchConnection();
    RecordRepository repo = new RecordRepository(connection.getConnection());

    def "FindByTimeStamp"() {
        when:
          List<Record> result = repo.findByTimeStamp(1452707600790l);
        then:
            true;
    }
}
