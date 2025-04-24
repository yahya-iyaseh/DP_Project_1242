package test;

import org.junit.jupiter.api.Test;
import shoppingmall.*;
import shoppingmall.payment.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentStrategyTest {

    @Test
    public void testPayWithCreditCard() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Item A", "I001", "S001", 100.0));
        cart.setPaymentStrategy(new CreditCardPayment());

        // Expected behavior: prints "Paid $100.0 using Credit Card."
        cart.checkout(); // You’d verify by observing the printed output or mocking in real case
    }

    @Test
    public void testPayWithPayPal() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Item A", "I001", "S001", 50.0));
        cart.setPaymentStrategy(new PayPalPayment());

        cart.checkout(); // Should use PayPal
    }
}
