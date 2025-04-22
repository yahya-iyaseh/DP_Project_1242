package shoppingmall.command;

import shoppingmall.Item;

import java.util.ArrayList;
import java.util.List;

// Command Pattern: Receiver - performs the actual operations
public class Order {
    private List<Item> items = new ArrayList<>();
    private String customerName;

    public Order(String customerName, List<Item> items) {
        this.customerName = customerName;
        this.items.addAll(items);
    }

    public void place() {
        System.out.println("Order placed for " + customerName + ":");
        items.forEach(i -> System.out.println("- " + i.getName()));
    }

    public void cancel() {
        System.out.println("Order canceled for " + customerName);
    }

    public void modify(Item newItem) {
        items.add(newItem);
        System.out.println("Order modified: added " + newItem.getName());
    }
}
