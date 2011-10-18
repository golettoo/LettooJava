package cn.lettoo.thread;

import java.io.IOException;

class Daemon extends Thread {
    private static final int SIZE = 10;
    private Thread[] t = new Thread[SIZE];
    
    public Daemon() {
        setDaemon(true);
        start();
    }
    
    public void run() {
        for (int i = 0; i < SIZE; i++) {
            t[i] = new DaemonSpawn(i);            
        }
        
        for (int i = 0; i < SIZE; i++) {
            System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon() );
        }
        
        while (true) {
            yield();
        }
    }
}

class DaemonSpawn extends Thread {
    public DaemonSpawn(int i) {
        System.out.println("DaemonSpawn " + i + " started");
        start();
    }
    
    public void run() {
        while (true) {
            yield();
        }
    }
}

public class Daemons {

    public static void main(String[] args) throws IOException {
        Thread daemon = new Daemon();
        System.out.println("daemon.isDaemon() = " + daemon.isDaemon());
        
        System.out.println("Press any key");
        System.in.read();
    }
} ///:~
