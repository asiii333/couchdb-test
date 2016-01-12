package com.bd.tests.couchdb

import org.ektorp.CouchDbConnector

/**
 * Created by Asia on 2016-01-12.
 */
class CouchConnectionTest extends spock.lang.Specification {
    CouchConnection connection = new CouchConnection();
    CouchDbConnector connector;
    def "getConnection"() {
        when:
        connector = connection.getConnection();
        then:
        connector.dbInfo != null;
        connector.databaseName == "ztb"


    }
}
