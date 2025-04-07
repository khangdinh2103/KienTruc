package compositepattern.cafe;

import java.util.ArrayList;
import java.util.List;

public class CafeShop implements OrderItem{
    String name;
    List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public void setName(String name) {
        this.name = name;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public CafeShop(String name) {
        this.name = name;
    }

    public void addTable (OrderItem table) {
        orderItems.add(table);
    }
    @Override
    public double getPrice() {
        double price = 0;
        for (OrderItem orderItem : orderItems) {
            price += orderItem.getPrice();
        }
        return price;
    }

    @Override
    public String getDetail() {
        String detail = "";
        for (OrderItem orderItem : orderItems) {
            detail += orderItem.getDetail() + "\n";
        }
        return "Name shop: " + name + ", Order Items: " + detail +  ", Price shop: " + getPrice();
    }
}
