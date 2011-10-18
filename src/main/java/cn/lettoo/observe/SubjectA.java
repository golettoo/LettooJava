package cn.lettoo.observe;

import java.util.HashSet;
import java.util.Set;

public class SubjectA implements Subject {

    Set<Observer> observers = new HashSet<Observer>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.watch("MessageA");
        }
    }

}
