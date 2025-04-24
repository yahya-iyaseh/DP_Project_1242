package test;

import org.junit.jupiter.api.Test;
import shoppingmall.*;
import shoppingmall.observer.*;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {

    static class TestObserver implements CartObserver {
        boolean notified = false;
        String lastMessage = "";

        @Override
        public void update(String message) {
            notified = true;
            lastMessage = message;
        }
    }

    @Test
    public void testObserverGetsNotifiedOnAddItem() {
        ShoppingCart cart = new ShoppingCart();
        TestObserver observer = new TestObserver();
        cart.addObserver(observer);

        Item item = new Item("ObserverBook", "O001", "S002", 30.0);
        cart.addItem(item);

        assertTrue(observer.notified);
        assertTrue(observer.lastMessage.contains("ObserverBook"));
    }
}
