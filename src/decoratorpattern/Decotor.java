package decoratorpattern;

interface Coffee {
    String moTa();
    double price();

}
class CoffeeSimple implements Coffee {

    @Override
    public String moTa() {
        return "Den da khong duong, ";
    }

    @Override
    public double price() {
        return 15000.0;
    }
}
abstract class CoffeeDecorator implements Coffee {
    private Coffee cfDecor;
    public CoffeeDecorator(Coffee cf) {
        cfDecor = cf;
    }

    @Override
    public String moTa() {
        return cfDecor.moTa();
    }

    @Override
    public double price() {
        return cfDecor.price();
    }
}

class CoffeeMilk extends CoffeeDecorator {
    public CoffeeMilk(Coffee cf) {
        super(cf);
    }

    @Override
    public String moTa() {
        return super.moTa() + "Milk";
    }

    @Override
    public double price() {
        return super.price()+3000.0;
    }

}

class BacXiu extends CoffeeDecorator {
    public BacXiu(Coffee cf) {
        super(cf);
    }

    @Override
    public String moTa() {
        return super.moTa() + "Nhieu sua";
    }

    @Override
    public double price() {
        return super.price()+5000.0;
    }

}
public class Decotor {
    public static void main(String[] args) {
        Coffee cf = new CoffeeSimple();
        CoffeeMilk milk = new CoffeeMilk(cf);
        System.out.println(milk.moTa());
        System.out.println(milk.price());
        BacXiu bacxiu = new BacXiu(cf);
        System.out.println(bacxiu.moTa());
        System.out.println(bacxiu.price());

    }
}
