package shoppingmall.command;

import shoppingmall.Item;

// Command Pattern: Concrete Command for modifying an order
public class ModifyOrderCommand implements OrderCommand {
    private Order order;
    private Item newItem;

    public ModifyOrderCommand(Order order, Item newItem) {
        this.order = order;
        this.newItem = newItem;
    }

    @Override
    public void execute() {
        order.modify(newItem);
    }
}
