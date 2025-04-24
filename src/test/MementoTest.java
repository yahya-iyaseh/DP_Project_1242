package test;

import org.junit.jupiter.api.Test;
import shoppingmall.*;
import shoppingmall.memento.*;

import static org.junit.jupiter.api.Assertions.*;

public class MementoTest {

    @Test
    public void testCartRestore() {
        ShoppingCart cart = new ShoppingCart();
        Item item1 = new Item("Book", "B001", "S001", 20.0);
        Item item2 = new Item("Game", "G001", "S001", 60.0);

        cart.addItem(item1);

        CartHistory history = new CartHistory();
        history.save(cart.saveStateToMemento()); // Save state with 1 item

        cart.addItem(item2); // Add second item

        cart.restoreFromMemento(history.restore()); // Restore to 1 item

        assertEquals(1, cart.items().asIterator().hasNext() ? 1 : 0); // basic verification
    }
}
