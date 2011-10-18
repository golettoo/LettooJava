package cn.lettoo.observe;

public class Client {

    public static void main(String[] args) {
        Observer a = new ObserverA();
        Observer b = new ObserverB();

        Subject subject = new SubjectA();
        subject.addObserver(a);
        subject.addObserver(b);

        subject.notifyObservers();
    }
}
