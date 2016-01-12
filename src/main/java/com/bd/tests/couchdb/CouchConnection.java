package com.bd.tests.couchdb;

import com.bd.tests.data.Record;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Asia on 2016-01-12.
 */
public class CouchConnection {
    public CouchDbConnector getConnection(){
        // proxy serwer + main server http://10.156.207.92:5984
        // adresy do node -> .208 .84 .149
        HttpClient httpClient = null;
        try {
            httpClient = new StdHttpClient.Builder()
                    .url(" http://10.156.207.92:5984")
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector db = new StdCouchDbConnector("ztb", dbInstance);
        return db;
    }
}
