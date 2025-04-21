package shoppingmall.payment;

// Strategy Pattern: Concrete strategy for PayPal payment
public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
    }
}
