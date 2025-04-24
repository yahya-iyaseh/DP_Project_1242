package shoppingmall.state;

public interface OrderState {
    void pay();
    void ship();
    void deliver();
    void cancel();
}
