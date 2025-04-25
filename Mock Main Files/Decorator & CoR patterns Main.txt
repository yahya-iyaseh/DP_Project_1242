package shoppingmall;

import shoppingmall.discount.*;

public class Main {
    public static void main(String[] args) {
        Item baseItem = new Item("Effective Java", "B001", "BS001", 100.0);
        System.out.println("Original Price: $" + baseItem.getPrice());

        // Decorator Pattern: Manually chain decorators
        ItemComponent discounted = new FixedDiscount(
                new PercentageDiscount(baseItem, 20), // 20% off
                5                                    // $5 off
        );
        System.out.println("Decorated Price (20% + $5): $" + discounted.getPrice());

        // Chain of Responsibility Pattern: build chain
        DiscountHandler chain = new BlackFridayDiscount();
        chain.setNext(new CouponDiscount());

        ItemComponent chainedDiscounted = chain.apply(baseItem);
        System.out.println("Price via CoR (15% + $10): $" + chainedDiscounted.getPrice());
    }
}

