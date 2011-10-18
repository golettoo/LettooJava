package cn.lettoo.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobTest {

    public static void main(String[] args) {
        // 创建一个可缓存的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        // 构造带线程池的Scheduler
        ThreadPoolScheduler scheduler = new ThreadPoolScheduler(pool);
        
        Job job1 = new SimpleJob("job1");
        scheduler.schedule(job1, System.currentTimeMillis(), 5000);
        
        Job job2 = new SimpleJob("job2");
        scheduler.schedule(job2, System.currentTimeMillis() + 1000, 5000);
        
        Job job3 = new SimpleJob("job3");
        scheduler.schedule(job3, System.currentTimeMillis() + 2000, 5000);
        
        Job job4 = new SimpleJob("job4");
        scheduler.schedule(job4, System.currentTimeMillis() + 3000, 5000);
        
        Job job5 = new SimpleJob("job5");
        scheduler.schedule(job5, System.currentTimeMillis() + 4000, 5000);
        
        scheduler.start();
    }

}
