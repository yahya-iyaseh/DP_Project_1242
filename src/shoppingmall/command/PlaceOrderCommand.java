package shoppingmall.command;

// Command Pattern: Concrete Command for placing an order
public class PlaceOrderCommand implements OrderCommand {
    private Order order;

    public PlaceOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        order.place();
    }
}
