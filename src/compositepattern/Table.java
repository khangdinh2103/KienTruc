package compositepattern;

import java.util.ArrayList;
import java.util.List;

public class Table implements IComponent {
    private List<IComponent> items = new ArrayList<>();

    public void add(IComponent item) {
        items.add(item);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (IComponent item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
