package md.codefactory.multithreading;

import java.util.concurrent.Semaphore;

public class AcquireThread extends Thread {

    private Semaphore semaphore;

    public AcquireThread() {}
    public AcquireThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            semaphore.acquire();

            sleep(1000);

            System.out.println("Thread " + this.getName() + " acquired");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
