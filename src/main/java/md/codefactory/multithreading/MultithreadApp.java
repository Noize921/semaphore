package md.codefactory.multithreading;

import com.sun.org.apache.xpath.internal.operations.Mult;

import java.util.concurrent.Semaphore;

public class MultithreadApp extends Thread {
    private Semaphore semaphore;

    public MultithreadApp() {}
    public  MultithreadApp(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            semaphore.acquire();

            System.out.println("Thread " + this.getName() + " acquired");

            sleep(1000);

            semaphore.release();

            System.out.println("Thread " + this.getName() + " release");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(1);
        Semaphore sem3 = new Semaphore(1);

        MultithreadApp thread1 = new MultithreadApp(sem1);
        ReleaseThread thread2 = new ReleaseThread(sem2);
        AcquireThread thread3 = new AcquireThread(sem3);
        MultithreadApp thread4 = new MultithreadApp(sem3);

        try {
            thread4.start();
            thread2.start();
            thread3.start();
            thread1.start();

            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
