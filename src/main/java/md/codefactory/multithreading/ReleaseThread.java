package md.codefactory.multithreading;

import java.util.concurrent.Semaphore;

public class ReleaseThread extends Thread {
    private Semaphore semaphore;

    public ReleaseThread() {}
    public ReleaseThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.release();

            sleep(1000);

            System.out.println("Thread " + this.getName() + " released");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
