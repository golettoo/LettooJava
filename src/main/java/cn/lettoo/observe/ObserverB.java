package cn.lettoo.observe;

public class ObserverB implements Observer {

    @Override
    public void watch(String message) {
        System.out.println(String.format("This is observer B watching the message: %s", message));
    }
}
