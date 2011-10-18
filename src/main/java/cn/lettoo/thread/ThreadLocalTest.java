package cn.lettoo.thread;

public class ThreadLocalTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        People people = new People();
        
        YearOld y1 = new YearOld(people);
        YearOld y2 = new YearOld(people);
        YearOld y3 = new YearOld(people);
        
        y1.start();
        y2.start();
        y3.start();
    }

}

class YearOld extends Thread {

    private People people;

    public YearOld(People people) {
        this.people = people;
    }

    public void run() {
        for (int i = 1; i < 10; i++) {
            this.people.setAge(i);
            System.out.println("Thread[" + Thread.currentThread().getName() + "] set people age to " + i);
        }
    }
}
