package shoppingmall.command;

import java.util.LinkedList;
import java.util.Queue;

// Command Pattern: Invoker - manages commands
public class OrderManager {
    private Queue<OrderCommand> commandQueue = new LinkedList<>();

    public void addCommand(OrderCommand command) {
        commandQueue.add(command);
    }

    public void processOrders() {
        while (!commandQueue.isEmpty()) {
            commandQueue.poll().execute();
        }
    }
}
