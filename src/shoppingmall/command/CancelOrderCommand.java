package shoppingmall.command;

// Command Pattern: Concrete Command for canceling an order
public class CancelOrderCommand implements OrderCommand {
    private Order order;

    public CancelOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        order.cancel();
    }
}
