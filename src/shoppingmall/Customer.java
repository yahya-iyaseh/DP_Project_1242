package shoppingmall;

public class Customer {
    private String name;
    private ShoppingCart shoppingCart;
    private Store store;

    public Customer(String name) {
        this.name = name;
        this.shoppingCart = new ShoppingCart();
    }

    public String getName() {
        return name;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void enterStore(Store store) {
        this.store = store;
        store.enter(this);
    }

    public void exitStore() {
        if (store != null) {
            store.exit(this);
            store = null;
        }
    }
}
