package shoppingmall.state;

public class CanceledState implements OrderState {
    public CanceledState(OrderContext context) {
    }

    @Override
    public void pay() {
        System.out.println("Cannot pay. Order was canceled.");
    }

    @Override
    public void ship() {
        System.out.println("Cannot ship. Order was canceled.");
    }

    @Override
    public void deliver() {
        System.out.println("Cannot deliver. Order was canceled.");
    }

    @Override
    public void cancel() {
        System.out.println("Order already canceled.");
    }
}
