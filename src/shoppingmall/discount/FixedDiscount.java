package shoppingmall.discount;

///  Decorator Pattern: Applies a fixed amount discount
public class FixedDiscount extends DiscountDecorator {
    private double amount;

    public FixedDiscount(ItemComponent item, double amount) {
        super(item);
        this.amount = amount;
    }

    @Override
    public double getPrice() {
        return Math.max(0, item.getPrice() - amount);
    }
}
