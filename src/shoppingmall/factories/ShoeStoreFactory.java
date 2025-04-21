package shoppingmall.factories;

import shoppingmall.BookStore;
import shoppingmall.Store;

public class ShoeStoreFactory implements StoreFactory {
    private static ShoeStoreFactory instance;

    private ShoeStoreFactory() {
    }

    ;

    public static synchronized ShoeStoreFactory getInstance() {
        if (instance == null)
            instance = new ShoeStoreFactory();

        return instance;
    }

    @Override
    public Store createStore(String name, String id) {
        return new BookStore(name, id);
    }
}
