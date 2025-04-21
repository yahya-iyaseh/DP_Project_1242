package shoppingmall;

import shoppingmall.factories.*;
import shoppingmall.payment.*;
import shoppingmall.observer.*;
import shoppingmall.memento.CartMemento;
import shoppingmall.memento.CartHistory;

public class Main {
    public static void main(String[] args) {
        Mall mall = new Mall("Java Mall");

        Store bookStore = BookStoreFactory.getInstance().createStore("Books & Co", "BS001");
        mall.addStore(bookStore);
        bookStore.addItem(new Item("Effective Java", "B001", bookStore.getId(), 45.00));
        bookStore.addItem(new Item("Clean Code", "B002", bookStore.getId(), 40.00));

        Customer customer = new Customer("Yahya");
        mall.enter(customer);
        customer.enterStore(bookStore);
        ShoppingCart cart = customer.getShoppingCart();

        cart.addObserver(new EmailNotifier("yahya@example.com"));
        cart.addItem(bookStore.items().nextElement());

        CartHistory history = new CartHistory();
        // Memento Pattern: Save current cart state
        history.save(cart.saveStateToMemento());

        // Add another item
        cart.addItem(bookStore.items().nextElement());

        // Change mind, restore previous state
        cart.restoreFromMemento(history.restore());

        // Pay
        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout();
    }
}
