package shoppingmall;

import shoppingmall.payment.PaymentStrategy;
import shoppingmall.observer.CartObserver;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import java.util.*;

// ShoppingCart supports Observer and Strategy Pattern (Strategy for payments)
public class ShoppingCart {
    private ArrayList<Item> items = new ArrayList<>();
    private List<CartObserver> observers = new ArrayList();
    private PaymentStrategy paymentStrategy; // Strategy Pattern: Encapsulation of payment behavior

    // Strategy Pattern
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public Enumeration<Item> items() {
        return new Vector<>(items).elements();
    }

    public void addObserver(CartObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (CartObserver o : observers) {
            o.update(message);
        }
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println("Item added: " + item.getName());
        // notification observer
        notifyObservers("Item added to cart: " + item.getName());
    }

    public void removeItem(Item item) {
        items.remove(item);
        notifyObservers("Item removed from cart: " + item.getName());
    }

    public void checkout() {
        if (paymentStrategy == null) {
            notifyObservers("Checkout failed: No payment method selected.");
            return;
        }
        double total = items.stream().mapToDouble(Item::getPrice).sum();
        paymentStrategy.pay(total);

        notifyObservers("Checkout complete. Total paid: $" + total);

    }
}
