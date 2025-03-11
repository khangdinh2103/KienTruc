package StrategyPattern;

import java.util.ArrayList;
import java.util.List;

class LopTruong implements Subject {
    private String tenLop;
    private List<IObserver> observers = new ArrayList<>();

    public LopTruong(String tenLop) {
        this.tenLop = tenLop;
    }

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        System.out.println("Lớp trưởng gửi thông báo đến lớp " + tenLop + ": " + message);
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }

    public void sendMessage(String message) {
        notifyObservers(message);
    }
}