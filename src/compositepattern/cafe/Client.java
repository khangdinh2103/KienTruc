package compositepattern.cafe;



public class Client {
    public static void main(String[] args) {
        Product pr1 = new Product("Cafe", 15000);
        Product pr2 = new Product("Cafe sữa", 15000);

        Table tb1 = new Table(1);
        Table tb2 = new Table(2);
        tb1.addProDuct(pr1);
        tb1.addProDuct(pr2);
        tb2.addProDuct(pr1);

        CafeShop cafeShop = new CafeShop("Khang");
        cafeShop.addTable(tb1);
        cafeShop.addTable(tb2);
        System.out.println(cafeShop.getDetail());
    }
}
