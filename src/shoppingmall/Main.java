package shoppingmall;

import shoppingmall.factories.*;
import shoppingmall.payment.*;
import shoppingmall.observer.*;

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

        // Observer Pattern: Add observers to the cart
        cart.addObserver(new EmailNotifier("yahya@example.com"));
        cart.addObserver(new SMSNotifier("+972123456789"));

        // Add item and checkout
        cart.addItem(bookStore.items().nextElement());
        cart.setPaymentStrategy(new CreditCardPayment()); // Strategy Pattern
        cart.checkout();
    }
}
