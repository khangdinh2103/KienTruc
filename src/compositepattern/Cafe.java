package compositepattern;
import java.util.ArrayList;
import java.util.List;

public class Cafe implements IComponent {
    private List<IComponent> tables = new ArrayList<>();

    public void add(IComponent table) {
        tables.add(table);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (IComponent table : tables) {
            total += table.getPrice();
        }
        return total;
    }
}
