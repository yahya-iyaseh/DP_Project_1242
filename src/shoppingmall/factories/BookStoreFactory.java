package shoppingmall.factories;

import shoppingmall.BookStore;
import shoppingmall.Store;

public class BookStoreFactory implements StoreFactory {
    private static BookStoreFactory instance;

    private BookStoreFactory() {
    }

    ;

    public static synchronized BookStoreFactory getInstance() {
        if (instance == null)
            instance = new BookStoreFactory();

        return instance;
    }

    @Override
    public Store createStore(String name, String id) {
        return new BookStore(name, id);
    }
}
