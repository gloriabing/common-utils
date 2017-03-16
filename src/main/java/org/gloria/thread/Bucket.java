package org.gloria.thread;

import java.util.LinkedList;
import java.util.List;

/**
 * Create on 2017/3/16 14:49.
 *
 * @author : gloria.
 */
public class Bucket {

    List<Integer> list = new LinkedList<>();

    public synchronized void add(Integer integer) {
        while (list.size() == 100) {
            //full wait for consume
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        this.notify();
        list.add(integer);
    }

    public synchronized Integer remove() {
        while (list.isEmpty()) {
            //empty wait for produce
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        this.notify();
        
        return list.remove(0);
    }
}
