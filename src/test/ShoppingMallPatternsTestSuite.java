package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import shoppingmall.*;
import shoppingmall.command.*;
import shoppingmall.discount.*;
import shoppingmall.memento.*;
import shoppingmall.observer.*;
import shoppingmall.payment.*;
import shoppingmall.proxy.*;
import shoppingmall.state.*;

import java.util.*;
/*
  A full senarios and design patterns in one file
 */
public class ShoppingMallPatternsTestSuite {

    @Test
    public void strategyPatternTest() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Laptop", "I001", "S001", 1500));
        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout();

        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout();
    }

    @Test
    public void observerPatternTest() {
        ShoppingCart cart = new ShoppingCart();
        TestObserver observer = new TestObserver();
        cart.addObserver(observer);
        cart.addItem(new Item("Phone", "I002", "S002", 999));
        assertTrue(observer.notified);
    }


    static class TestObserver implements CartObserver {
        boolean notified = false;
        @Override
        public void update(String message) {
            notified = true;
        }
    }

    @Test
    public void mementoPatternTest() {
        ShoppingCart cart = new ShoppingCart();
        CartHistory history = new CartHistory();

        cart.addItem(new Item("TV", "I003", "S003", 700));
        history.save(cart.saveStateToMemento());

        cart.addItem(new Item("Speakers", "I004", "S003", 200));
        cart.restoreFromMemento(history.restore());

        assertEquals(1, Collections.list(cart.items()).size());
    }

    @Test
    public void commandPatternTest() {
        Item item = new Item("Tablet", "I005", "S004", 300);
        Order order = new Order("Yahya", List.of(item));
        OrderManager manager = new OrderManager();
        manager.addCommand(new PlaceOrderCommand(order));
        manager.addCommand(new CancelOrderCommand(order));
        manager.processOrders();
    }

    @Test
    public void statePatternTest() {
        OrderContext order = new OrderContext();
        order.pay();
        order.ship();
        order.deliver();
        order.cancel(); // Should not cancel since already delivered
        assertEquals("DeliveredState", order.getStateName());
    }

    @Test
    public void proxyPatternTest() {
        ReviewService guest = new ReviewServiceProxy("guest", false);
        guest.viewReviews("B001");

        ReviewService yahya = new ReviewServiceProxy("Yahya", true);
        yahya.postReview("B001", "Awesome book!", "Yahya");
        yahya.viewReviews("B001");
    }

    @Test
    public void decoratorAndCoRDiscountTest() {
        Item base = new Item("Camera", "I006", "S005", 1000);
        ItemComponent discounted = new FixedDiscount(
                new PercentageDiscount(base, 10), 50);

        assertEquals(850, discounted.getPrice(), 0.1);

        DiscountHandler chain = new BlackFridayDiscount();
        chain.setNext(new CouponDiscount());
        ItemComponent chained = chain.apply(base);
        assertEquals(840, chained.getPrice(), 0.1);
    }

    @Test
    public void fullScenarioTest() {
        // Customer enters, adds item, gets notified, saves cart, pays, transitions through order states
        Mall mall = new Mall("JavaMall");
        Store bookStore = new BookStore("BookWorld", "BS001");
        Item book = new Item("Design Patterns", "B001", "BS001", 100);
        bookStore.addItem(book);
        mall.addStore(bookStore);

        Customer c = new Customer("Yahya");
        mall.enter(c);
        c.enterStore(bookStore);

        ShoppingCart cart = c.getShoppingCart();
        cart.addObserver(new EmailNotifier("yahya@example.com"));
        cart.addItem(book);

        CartHistory h = new CartHistory();
        h.save(cart.saveStateToMemento());

        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout();

        OrderContext order = new OrderContext();
        order.pay();
        order.ship();
        order.deliver();

        assertEquals("DeliveredState", order.getStateName());
    }
}
