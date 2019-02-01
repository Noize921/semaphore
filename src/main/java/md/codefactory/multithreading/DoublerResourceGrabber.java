package md.codefactory.multithreading;

import java.util.concurrent.Semaphore;

public class DoublerResourceGrabber implements Runnable {

    private Semaphore first;
    private Semaphore second;

    public DoublerResourceGrabber(Semaphore s1, Semaphore s2) {
        this.first = s1;
        this.second = s2;
    }

    @Override
    public void run() {
        try {
            Thread thread = Thread.currentThread();

            first.acquire();
            System.out.println(thread + " acquired s1 " + first);

            Thread.sleep(200);

            second.acquire();
            System.out.println(thread + " acquired s2 "  + second);

            second.release();
            System.out.println(thread + " released s2 " + second);

            first.release();
            System.out.println(thread + " released s1 " + first);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
