package shoppingmall.observer;

// Observer Pattern: Concrete Observer (Email)
public class EmailNotifier implements CartObserver {
    private String customerEmail;

    public EmailNotifier(String email) {
        this.customerEmail = email;
    }

    @Override
    public void update(String message) {
        System.out.println("[Email to " + customerEmail + "] " + message);
    }
}