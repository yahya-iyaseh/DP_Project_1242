package shoppingmall;

import shoppingmall.discount.ItemComponent;

// Decorator Pattern: Concrete Component
public class Item implements ItemComponent {
    private String name;
    private String id;
    private String storeId;
    private double price;

    public Item(String name, String id, String storeId, double price) {
        this.name = name;
        this.id = id;
        this.storeId = storeId;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public String GetId() {
        return id;
    }

    public String getStoreId() {
        return storeId;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
