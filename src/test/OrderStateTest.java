package test;

import org.junit.jupiter.api.Test;
import shoppingmall.state.OrderContext;

import static org.junit.jupiter.api.Assertions.*;
/*
    State Pattern - Test Transitions
 */
public class OrderStateTest {

    @Test
    public void testValidOrderFlow() {
        OrderContext order = new OrderContext();

        assertEquals("NewState", order.getStateName());

        order.pay();
        assertEquals("PaidState", order.getStateName());

        order.ship();
        assertEquals("ShippedState", order.getStateName());

        order.deliver();
        assertEquals("DeliveredState", order.getStateName());
    }

    @Test
    public void testInvalidTransitionFromDelivered() {
        OrderContext order = new OrderContext();
        order.pay();
        order.ship();
        order.deliver();

        order.cancel(); // Should not allow
        assertEquals("DeliveredState", order.getStateName());
    }
}

