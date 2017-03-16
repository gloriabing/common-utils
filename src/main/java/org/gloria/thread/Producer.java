package org.gloria.thread;


/**
 * Create on 2017/3/16 14:22.
 *
 * @author : gloria.
 */
public class Producer implements Runnable {

    private Bucket bucket; 
    
    public Producer(Bucket bucket) {
        this.bucket = bucket;
    }
    
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                bucket.add(i);
                System.out.println("produce : " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
