package shoppingmall.state;

public class DeliveredState implements OrderState {
    public DeliveredState(OrderContext context) {
    }

    @Override
    public void pay() {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void ship() {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void deliver() {
        System.out.println("Order already delivered.");
    }

    @Override
    public void cancel() {
        System.out.println("Cannot cancel. Order already delivered.");
    }
}
