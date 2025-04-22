package shoppingmall.discount;

// Discount handler example
public class BlackFridayDiscount extends DiscountHandler {
    @Override
    protected ItemComponent applyDiscount(ItemComponent item) {
        return new PercentageDiscount(item, 15);
    }
}
