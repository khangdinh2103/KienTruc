package adapterpattern.translator;

public class Adaptee {
    public void receiveMessage(String message) {
        System.out.println("Người Nhật nhận: " + message);
    }
}
