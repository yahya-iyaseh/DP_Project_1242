package shoppingmall.memento;

import shoppingmall.Item;

import java.util.ArrayList;
import java.util.List;

// Memento Pattern: Stores a snapshot of cart items
public class CartMemento {
    private final List<Item> items;

    public CartMemento(List<Item> items) {
        // Deep copy to protect encapsulation
        this.items = new ArrayList<>(items);
    }

    public List<Item> getSavedItems() {
        return new ArrayList<>(items);
    }
}
