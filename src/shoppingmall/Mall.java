package shoppingmall;

import java.util.*;

public class Mall {
    private String name;
    private List<Store> stores = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public Mall(String name) {
        this.name = name;
    }

    public void enter(Customer c) {
        customers.add(c);
        System.out.println(c.getName() + " entered the mall");
    }

    public void exit(Customer c) {
        customers.remove(c);
        System.out.println(c.getName() + " exited the mall");
    }

    public Enumeration<Customer> customers() {
        return new Vector<>(customers).elements();
    }

    public Enumeration<Store> stores() {
        return new Vector<>(stores).elements();
    }

    public void addStore(Store s) {
        stores.add(s);
        System.out.println("Store added: " + s.getName());
    }
}
