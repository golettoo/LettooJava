package cn.lettoo.thread.waitnotify;

public class Base implements Runnable {

    protected String name;

    public String getName() {
        return name;
    }

    protected Store store;

    public Base(String name, Store store) {
        this.name = name;
        this.store = store;
    }

    public void run() {        
    }

    public void sleep() {
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
