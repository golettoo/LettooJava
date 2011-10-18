package cn.lettoo.observe;

import java.util.HashSet;
import java.util.Set;

public class SubjectA implements Subject {

    Set<Observer> observers = new HashSet<Observer>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.watch("MessageA");
        }
    }

}
