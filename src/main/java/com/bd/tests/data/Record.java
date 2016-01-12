package com.bd.tests.data;

import org.ektorp.support.CouchDbDocument;

import java.util.Date;
import java.util.Random;

/**
 * @author majaschaefer
 */
public class Record extends CouchDbDocument {

    private static final Random random = new Random();
    private static final RandomString randomString = new RandomString(1024);
    private static final RandomString randomString2 = new RandomString(32767);

    //private final String id;
    private long timestamp;
    private String uri;
    private long num1;
    private long num2;
    private long num3;
    private long num4;
    private double doub1;
    private double doub2;
    private double doub3;
    private double doub4;
    private String text;

    public Record(/*String id,*/ long timestamp, String uri, long num1, long num2, long num3, long num4, double doub1, double doub2, double doub3, double doub4, String text) {
        //this.id = id;
        this.timestamp = timestamp;
        this.uri = uri;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.doub1 = doub1;
        this.doub2 = doub2;
        this.doub3 = doub3;
        this.doub4 = doub4;
        this.text = text;
    }

    public static Record newRandom(/*String id,*/ long timestamp) {

        return new Record(/*id,*/ timestamp, randomString.nextString(), random.nextLong(), random.nextLong(), random.nextLong(), random.nextLong(),
                random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), randomString2.nextString());

    }

    public static Random getRandom() {
        return random;
    }

    public static RandomString getRandomString() {
        return randomString;
    }

    public static RandomString getRandomString2() {
        return randomString2;
    }

   /* public String getId() {
        return id;
    }*/

    public long getTimestamp() {
        return timestamp;
    }

    public String getUri() {
        return uri;
    }

    public long getNum1() {
        return num1;
    }

    public long getNum2() {
        return num2;
    }

    public long getNum3() {
        return num3;
    }

    public long getNum4() {
        return num4;
    }

    public double getDoub1() {
        return doub1;
    }

    public double getDoub2() {
        return doub2;
    }

    public double getDoub3() {
        return doub3;
    }

    public double getDoub4() {
        return doub4;
    }

    public String getText() {
        return text;
    }
}
