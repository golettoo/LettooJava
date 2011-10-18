package cn.lettoo.observe;

public class ObserverA implements Observer {

    @Override
    public void watch(String message) {
        System.out.println(String.format("This is observer A watching the message: %s", message));
    }
}
