package org.gloria.thread;

/**
 * Create on 2017/3/16 14:23.
 *
 * @author : gloria.
 */
public class Consumer implements Runnable {

    private Bucket bucket;

    public Consumer(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Integer number = bucket.remove();
            System.out.println("consume : " + number);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
