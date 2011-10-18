package cn.lettoo.scheduler;

import java.util.Date;


public class SimpleJob implements Job {
    
    private String name;
    
    public SimpleJob(String name) {
        this.name = name;
    }

    public void execute() {
        System.out.println(new Date(System.currentTimeMillis()) + " Job " + this.name + " works by thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
