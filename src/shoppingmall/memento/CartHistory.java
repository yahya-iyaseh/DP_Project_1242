package shoppingmall.memento;

import java.util.Stack;

// Memento Pattern: Caretaker - manages saved states
public class CartHistory {
    private Stack<CartMemento> history = new Stack<>();

    public void save(CartMemento memento) {
        history.push(memento);
    }

    public CartMemento restore() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }

    public boolean hasHistory() {
        return !history.isEmpty();
    }
}
