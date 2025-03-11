package compositepattern;
public class CompositePatternDemo {
    public static void main(String[] args) {

        Product coffee = new Product("Cà phê", 25000);
        Product tea = new Product("Trà sữa", 30000);
        Product water = new Product("Nước suối", 15000);

        Table table1 = new Table();
        table1.add(coffee);
        table1.add(tea);

        Table table2 = new Table();
        table2.add(water);
        table2.add(coffee);
        table2.add(tea);

        Cafe cafe = new Cafe();
        cafe.add(table1);
        cafe.add(table2);
        
        System.out.println("Tổng doanh thu của quán: " + cafe.getPrice() + " VND");
    }
}
