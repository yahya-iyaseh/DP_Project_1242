package shoppingmall.discount;

// Decorator Pattern: Component interface, will be used to apply dynamic disccount to the products
public interface ItemComponent {
    String getName();
    double getPrice();
}
