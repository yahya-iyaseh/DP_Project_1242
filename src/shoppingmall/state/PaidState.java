package shoppingmall.state;

public class PaidState implements OrderState {
    private OrderContext context;

    public PaidState(OrderContext context) {
        this.context = context;
    }

    @Override
    public void pay() {
        System.out.println("Order is already paid.");
    }

    @Override
    public void ship() {
        System.out.println("Order shipped.");
        context.setState(new ShippedState(context));
    }

    @Override
    public void deliver() {
        System.out.println("Cannot deliver. Order not shipped yet.");
    }

    @Override
    public void cancel() {
        System.out.println("Order canceled.");
        context.setState(new CanceledState(context));
    }
}
