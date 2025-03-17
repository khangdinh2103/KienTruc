package observerpattern.CoPhieu;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Stock appleStock = new Stock("Apple", 150.0);

        Investor investor1 = new Investor("Nguyễn Văn A");
        Investor investor2 = new Investor("Trần Thị B");

        appleStock.attach(investor1);
        appleStock.attach(investor2);

        appleStock.setPrice(155.5);
        appleStock.setPrice(160.0);

        appleStock.detach(investor1);

        appleStock.setPrice(165.0);
    }
}

