package observerpattern.CoPhieu;

public class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(Stock stock) {
        System.out.println(" Nhà đầu tư " + name + " nhận thông báo: Cổ phiếu "
                + stock.getName() + " có giá mới là " + stock.getPrice());
    }
}