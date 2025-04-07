package abtractpattern;

interface Button {
    void create();
}
interface Checkbox {
    void create();
}
class WindownButton implements Button {
    public void create() {
        System.out.println("Windown Button");
    }
}
class MacOSButton implements Button {
    public void create() {
        System.out.println("MacOS Button");
    }
}
class WindownCheckbox implements Checkbox {
    public void create() {
        System.out.println("Windown Checkbox");
    }
}
class MacOSCheckbox implements Checkbox {
    public void create() {
        System.out.println("MacOS Checkbox");
    }
}
abstract class GUIFactory {
    public abstract Button createButton();
    public abstract Checkbox createCheckbox();

}
class WindownFactory extends GUIFactory {
    public Button createButton() {
        return new WindownButton();
    }

    public Checkbox createCheckbox() {
        return new WindownCheckbox();
    }


}

class MacOSFactory extends GUIFactory {


    public Button createButton() {
        return new MacOSButton();
    }
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }


}

enum Sys {
    Windows, Mac

}

public class AbtracPattern {
    public static void main(String[] args) {
        GUIFactory gui = null;

        String os = Sys.Windows.toString();

        if (os.equals("Windows")) {
            gui = new WindownFactory();
        } else if (os.equals("Mac")) {
            gui = new MacOSFactory();
        }


       Button button = gui.createButton();
        button.create();
        Checkbox checkbox = gui.createCheckbox();
        checkbox.create();



    }
}
