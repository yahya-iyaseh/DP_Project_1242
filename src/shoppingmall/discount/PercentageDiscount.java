package shoppingmall.discount;

// Decorator Pattern: Applies a percentage discount
public class PercentageDiscount extends DiscountDecorator {
    private double percentage;

    public PercentageDiscount(ItemComponent item, double percentage) {
        super(item);
        this.percentage = percentage;
    }

    @Override
    public double getPrice() {
        return item.getPrice() * (1 - percentage / 100.0);
    }
}
