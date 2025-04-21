package shoppingmall;

import java.util.*;

public abstract class Store {
    protected String name;
    protected String id;
    protected List<Item> items = new ArrayList<>();
    protected List<Customer> customers = new ArrayList<>();

    public Store(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract void enter(Customer customer);

    public abstract void exit(Customer customer);

    public Enumeration<Customer> customers() {
        return new Vector<>(customers).elements();
    }

    public Enumeration<Item> items() {
        return new Vector<>(items).elements();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
