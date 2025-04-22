package shoppingmall.discount;

///  CoR structure: Chain of Responsibility: Discount handler interface
public abstract class DiscountHandler {
    protected DiscountHandler next;

    public void setNext(DiscountHandler next) {
        this.next = next;
    }

    public ItemComponent apply(ItemComponent item) {
        ItemComponent result = applyDiscount(item);
        return (next != null) ? next.apply(result) : result;
    }

    protected abstract ItemComponent applyDiscount(ItemComponent item);
}
