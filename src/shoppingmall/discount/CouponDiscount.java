package shoppingmall.discount;

// Discount handler example
public class CouponDiscount extends DiscountHandler {
    @Override
    protected ItemComponent applyDiscount(ItemComponent item) {
        return new FixedDiscount(item, 10);
    }
}
