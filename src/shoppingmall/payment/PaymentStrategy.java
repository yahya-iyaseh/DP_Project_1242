package shoppingmall.payment;

// Strategy Pattern: Common interface fo all payment methods
public interface PaymentStrategy {
    void pay(double amount);
}
