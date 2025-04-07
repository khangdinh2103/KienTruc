package compositepattern.cafe;

import java.util.ArrayList;
import java.util.List;

public class Table implements OrderItem {
    Integer NumberTable;
    List<OrderItem> OrderItemList = new ArrayList<OrderItem>();

    public Table(Integer numberTable) {
        NumberTable = numberTable;

    }

    public Integer getNumberTable() {
        return NumberTable;
    }

    public void setNumberTable(Integer numberTable) {
        NumberTable = numberTable;
    }

    public List<OrderItem> getOrderItemList() {
        return OrderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        OrderItemList = orderItemList;
    }

    public void addProDuct(OrderItem product) {
        OrderItemList.add(product);
    }

    @Override
    public double getPrice() {
        double price = 0;
        for (OrderItem orderItem : OrderItemList) {
            price = price + orderItem.getPrice();
        }
        return price;
    }

    @Override
    public String getDetail() {
        String detail = "";
        for (OrderItem orderItem : OrderItemList) {
            detail = detail + orderItem.getDetail() ;
        }
        return "Table number: "+NumberTable+"\n " + detail + "Price : "+getPrice();
    }
}
