package cn.lettoo.scheduler;


public class TestScheduler {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        SimpleJob job1 = new SimpleJob("job1");
        SimpleJob job2 = new SimpleJob("job2");
        SimpleJob job3 = new SimpleJob("job3");
        SimpleJob job4 = new SimpleJob("job4");
        SimpleJob job5 = new SimpleJob("job5");
        
        scheduler.schedule(job1, System.currentTimeMillis(), 1000);
        scheduler.schedule(job2, System.currentTimeMillis() + 1000, 2000);
        scheduler.schedule(job3, System.currentTimeMillis() + 2000, 3000);
        scheduler.schedule(job4, System.currentTimeMillis() + 3000, 4000);
        scheduler.schedule(job5, System.currentTimeMillis() + 4000, 5000);
        
        scheduler.start();
        
    }

}
