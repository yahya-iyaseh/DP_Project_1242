package shoppingmall.discount;

// Decorator Pattern: Abstract Decorator
public abstract class DiscountDecorator implements ItemComponent {
    protected ItemComponent item;

    public DiscountDecorator(ItemComponent item) {
        this.item = item;
    }

    @Override
    public String getName() {
        return item.getName();
    }
}
