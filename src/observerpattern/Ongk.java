package observerpattern;
//Khi có sách mới hoặc sách đã hết hạn mượn,
//hệ thống sẽ gửi thông báo cho những người quan tâm
//        (ví dụ: các nhân viên thư viện hoặc những người dùng đã đăng ký theo dõi).
//Hãy sử dụng Observer Pattern để xử lý việc này.


import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String str);
}
interface Subject {

    void attach(Observer observer);
    void detach(Observer observer);
    void notifyOb();
}
class ConcreteSubject implements Subject {
    List<Observer> observers = new ArrayList<Observer>();

    public ConcreteSubject () {
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    @Override
    public void attach(Observer observer) {
        observers.add( observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove( observer);

    }

    @Override
    public void notifyOb() {
        for (Observer observer : observers) {
            observer.update("thong bao observer");
        }
    }

}

class ConcreteObserver implements Observer {
    @Override
    public void update(String str) {
        System.out.println(str);
    }

}
public class Ongk {
    public static void main(String[] args) {
        Observer observer = new ConcreteObserver();
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(observer);
        subject.notifyOb();

    }
}
