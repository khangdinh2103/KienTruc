package StrategyPattern;
//Các sản phẩm có thể áp dụng các loại thuế khác nhau,
//ví dụ như thuế tiêu thụ, thuế giá trị gia tăng (VAT),
//hoặc thuế đặc biệt cho các sản phẩm xa xỉ.

import java.util.List;

interface Strategy {
    void applyStrategy();

}
class VAT implements Strategy {


    public VAT() {
    }


    @Override
    public void applyStrategy() {
        System.out.println(".1");
    }
}
class TieuThu implements Strategy {
    public TieuThu() {
    }

    @Override
    public void applyStrategy() {
        System.out.println(".2");
    }
}
class BillNe{
    private double price;
    private Strategy strategy;
    public BillNe(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;

    }
    public Strategy getStrategy() {
        return strategy;
    }
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public void applyStrategy(Strategy strategy) {
        strategy.applyStrategy();
    }
}

public class OnGk1 {
    public static void main(String[] args) {
        VAT vat = new VAT();
        TieuThu tieuThu = new TieuThu();
        BillNe bill = new BillNe(100);
        bill.applyStrategy(vat);

    }
}
