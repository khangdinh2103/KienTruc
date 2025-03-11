package adapterpattern;

public class TranslatorAdapter implements Target {
    private Adaptee adaptee;

    public TranslatorAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void sendMessage(String message) {
        String translatedMessage = translateToJapanese(message);
        adaptee.receiveMessage(translatedMessage);
    }

    private String translateToJapanese(String message) {
        if (message.equalsIgnoreCase("Xin chào")) {
            return "こんにちは";  // Konnichiwa
        } else if (message.equalsIgnoreCase("Cảm ơn")) {
            return "ありがとう";  // Arigatou
        } else if (message.equalsIgnoreCase("Tạm biệt")) {
            return "さようなら";  // Sayounara
        }
        return "翻訳できません";  // Không thể dịch
    }
}