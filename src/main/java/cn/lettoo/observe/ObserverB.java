package cn.lettoo.observe;

public class ObserverB implements Observer {

    public void watch(String message) {
        System.out.println(String.format("This is observer B watching the message: %s", message));
    }
}
