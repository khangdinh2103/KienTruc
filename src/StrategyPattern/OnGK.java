package StrategyPattern;

interface DiscountStrategy {
    double applyDiscount(double price);
}
class PrecentStrategy implements DiscountStrategy {
    double Precent;

    public double getPrecent() {
        return Precent;
    }

    public void setPrecent(double precent) {
        Precent = precent;
    }

    public PrecentStrategy(double precent) {
        Precent = precent;
    }

    @Override
    public double applyDiscount(double price) {
        return price * Precent;
    }
}

class FixedDiscountStrategy implements DiscountStrategy {
    double FixedPrice;

    public double getFixedPrice() {
        return FixedPrice;
    }

    public void setFixedPrice(double fixedPrice) {
        FixedPrice = fixedPrice;
    }

    public FixedDiscountStrategy(double fixedPrice) {
        FixedPrice = fixedPrice;
    }

    @Override
    public double applyDiscount(double price) {
        return price - FixedPrice;
    }
}

class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(double price) {
        return price;
    }
}

class Bill {
    double price;
    DiscountStrategy discountStrategy;

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }


    public Bill(double price, DiscountStrategy discountStrategy) {
        this.price = price;
        this.discountStrategy = discountStrategy;
    }

    void applyDiscount() {
        System.out.println(discountStrategy.applyDiscount(price));

    }
}
public class OnGK {
    public static void main(String[] args) {
        Bill bill;
        bill = new Bill(15000, new PrecentStrategy(0.1));
        bill.applyDiscount();
    }
}
