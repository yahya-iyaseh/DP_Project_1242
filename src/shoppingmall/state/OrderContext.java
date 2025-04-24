package shoppingmall.state;

/*
    State Pattern: context - maintains current order state and the constraint and violotions when move from state to another.
 */
public class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new NewState(this);
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void pay() {
        state.pay();
    }

    public void ship() {
        state.ship();
    }

    public void deliver() {
        state.deliver();
    }

    public void cancel() {
        state.cancel();
    }

    public String getStateName() {
        return state.getClass().getSimpleName();
    }
}
