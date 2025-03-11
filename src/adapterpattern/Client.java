package adapterpattern;

public class Client {
    private Target translator;

    public Client(Target translator) {
        this.translator = translator;
    }

    public void send(String message) {
        System.out.println("Người Việt nói: " + message);
        translator.sendMessage(message);
    }
}
