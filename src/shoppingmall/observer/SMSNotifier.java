package shoppingmall.observer;

// Observer Pattern: Concrete Observer (SMS)
public class SMSNotifier implements CartObserver {
    private String customerPhone;

    public SMSNotifier(String phone) {
        this.customerPhone = phone;
    }

    @Override
    public void update(String message) {
        System.out.println("[SMS to " + customerPhone + "] " + message);
    }
}
