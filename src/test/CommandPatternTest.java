package test;

import org.junit.jupiter.api.Test;
import shoppingmall.*;
import shoppingmall.command.*;

import java.util.List;

public class CommandPatternTest {

    @Test
    public void testOrderQueueExecution() {
        Item item = new Item("TestGame", "G001", "S001", 99.0);
        Order order = new Order("Yahya", List.of(item));

        OrderCommand place = new PlaceOrderCommand(order);
        OrderCommand cancel = new CancelOrderCommand(order);

        OrderManager manager = new OrderManager();
        manager.addCommand(place);
        manager.addCommand(cancel);

        manager.processOrders(); // Should run both commands
    }
}

