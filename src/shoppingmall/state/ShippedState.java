package shoppingmall.state;

public class ShippedState implements OrderState {
    private OrderContext context;

    public ShippedState(OrderContext context) {
        this.context = context;
    }

    @Override
    public void pay() {
        System.out.println("Order is already paid.");
    }

    @Override
    public void ship() {
        System.out.println("Order already shipped.");
    }

    @Override
    public void deliver() {
        System.out.println("Order delivered.");
        context.setState(new DeliveredState(context));
    }

    @Override
    public void cancel() {
        System.out.println("Cannot cancel. Order already shipped.");
    }
}
