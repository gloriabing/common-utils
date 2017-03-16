package org.gloria.thread;

/**
 * Create on 2017/3/16 15:04.
 *
 * @author : gloria.
 */
public class ProducerConsumerTest {

    public static void main(String[] args) {
        Bucket bucket = new Bucket();
        Producer producer = new Producer(bucket);
        Consumer consumer = new Consumer(bucket);

        Thread producerThread = new Thread(producer);
        producerThread.start();

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        
        
    }

}