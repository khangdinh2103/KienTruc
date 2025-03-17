package observerpattern.tack;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}


interface Observer {
    void update(Task task);
}

// Concrete Subject: Công việc (Task) có thể thay đổi trạng thái
class Task implements Subject {
    private String name;
    private String status;
    private List<Observer> observers = new ArrayList<>();

    public Task(String name) {
        this.name = name;
        this.status = "New"; // Mặc định trạng thái là mới
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("\nTrạng thái công việc '" + name + "' thay đổi: " + newStatus);
        notifyObservers();
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}

// Concrete Observer: Thành viên nhóm nhận thông báo khi task thay đổi
class TeamMember implements Observer {
    private String name;

    public TeamMember(String name) {
        this.name = name;
    }

    @Override
    public void update(Task task) {
        System.out.println("Thành viên " + name + " nhận thông báo: Công việc '"
                + task.getName() + "' có trạng thái mới: " + task.getStatus());
    }
}


public class ObserverPatternDemo {
    public static void main(String[] args) {

        Task task1 = new Task("Fix Bug #101");

        TeamMember member1 = new TeamMember("Nguyễn Văn A");
        TeamMember member2 = new TeamMember("Trần Thị B");

        task1.attach(member1);
        task1.attach(member2);

        task1.setStatus("In Progress");
        task1.setStatus("Review");

        task1.detach(member1);

        task1.setStatus("Completed");
    }
}
