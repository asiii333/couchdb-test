package com.bd.tests.data;

import java.util.Comparator;

/**
 * @author majaschaefer
 */
public class InsertResultComparator implements Comparator<TaskResult> {


    public InsertResultComparator() {
    }

    @Override
    public int compare(TaskResult o1, TaskResult o2) {
        if(o1.getDuration() == o2.getDuration()) {
            return 0;
        } else if (o1.getDuration() > o2.getDuration()) {
            return 1;
        } else {
            return -1;

        }
    }
}
