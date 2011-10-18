package cn.lettoo.thread;

public class SimpleThread extends Thread {

    private int countDown = 5;

    private int threadNumber = ++threadCount;

    public SimpleThread() {
        System.out.println("Making " + threadNumber);
    }

    @Override
    public void run() {
        while (true) {
            System.out
                    .println("Thread " + threadNumber + "(" + countDown + ")");
            if (--countDown == 0)
                return;
        }
    }

    private static int threadCount = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread().start();
        }
        System.out.println("All Threads Started.");
    }

}
