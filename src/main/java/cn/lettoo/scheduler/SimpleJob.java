package cn.lettoo.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleJob implements Job {

    private String name;

    public SimpleJob(String name) {
        this.name = name;
    }

    public void execute() {
        Date now = new Date(System.currentTimeMillis());

        System.out.println(String.format("%s: %s executed by thread %s",
                SimpleDateFormat.getDateTimeInstance().format(now), this.name,
                Thread.currentThread().getName()));
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
