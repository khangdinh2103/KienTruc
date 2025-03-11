package observerpattern;

import java.util.ArrayList;
import java.util.List;

public class LopTruong {
    private String tenLop;
    private List<IObserver> observers = new ArrayList<>();

    public LopTruong(String tenLop) {
        this.tenLop = tenLop;
    }

    public void attach(IObserver observer) {
        observers.add(observer);
    }

    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    public void sendMessage(String message) {
        System.out.println("Lớp trưởng gửi thông báo đến lớp " + tenLop + ": " + message);
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }
}