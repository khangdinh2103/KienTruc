package observerpattern.LopHoc;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void attach(Observer o);
    void detach();
    void notifyObservers(String message);
}
interface Observer {
    void update(String message);
}
class LopTruong implements Subject {

    private String name;
    private List<Observer> observers;

    public LopTruong(List<Observer> observers, String name) {
        this.observers = observers;
        this.name = name;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    @Override
    public void detach() {

    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }

    }
}
class SinhVien implements Observer {
    private String name;

    public SinhVien(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String mess) {
        System.out.println("Sinh Vien nhan duoc thong bao" + mess);

    }
}
public class LopHoc {
    public static void main(String[] args) {

        List<Observer> observers = new ArrayList<Observer>();
        Observer sinhVien = new SinhVien("Sinh Vien");
        observers.add(new SinhVien("cho huy"));
        LopTruong lopTruong = new LopTruong(observers, "khang");
        lopTruong.attach(sinhVien);
        lopTruong.notifyObservers("hello");

    }
}
