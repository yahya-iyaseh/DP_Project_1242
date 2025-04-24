package shoppingmall.state;

public class NewState implements OrderState {
    private OrderContext context;

    public NewState(OrderContext context) {
        this.context = context;
    }

    @Override
    public void pay() {
        System.out.println("Order paid.");
        context.setState(new PaidState(context));
    }

    @Override
    public void ship() {
        System.out.println("Cannot ship. Order is not paid.");
    }

    @Override
    public void deliver() {
        System.out.println("Cannot deliver. Order is not shipped.");
    }

    @Override
    public void cancel() {
        System.out.println("Order canceled.");
        context.setState(new CanceledState(context));
    }
}
