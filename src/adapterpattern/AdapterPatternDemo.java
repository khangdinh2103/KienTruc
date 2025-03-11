package adapterpattern;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        Adaptee japanesePerson = new Adaptee();
        Target translator = new TranslatorAdapter(japanesePerson);
        Client vietnamesePerson = new Client(translator);

        // Giao tiếp giữa người Việt và người Nhật thông qua Adapter
        vietnamesePerson.send("Xin chào");
        vietnamesePerson.send("Cảm ơn");
        vietnamesePerson.send("Tạm biệt");
        vietnamesePerson.send("Bạn khỏe không?");
    }
}
