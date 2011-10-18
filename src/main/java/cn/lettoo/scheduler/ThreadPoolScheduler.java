package cn.lettoo.scheduler;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class ThreadPoolScheduler extends Scheduler {

    private ExecutorService pool;

    public ThreadPoolScheduler(ExecutorService pool) {
        super();
        this.pool = pool;
    }

    private Set<Job> runningJobList = new HashSet<Job>();
    
    @Override
    void executeInABox(final Job job) {
        if (!runningJobList.contains(job)) {
            runningJobList.add(job);
            pool.execute(new JobThread(job));
        }
    }    

    class JobThread implements Runnable {

        private Job job;

        public JobThread(Job job) {
            this.job = job;
        }

        public void run() {
            try {
                this.job.execute();
                synchronized (this) {
                    runningJobList.remove(job);
                }
            } catch (Exception e) {
                System.err
                        .println("The execution of the job threw an exception");
                e.printStackTrace(System.err);
            }
        }

    }
}
