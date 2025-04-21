package shoppingmall.factories;

import shoppingmall.Store;

public class BookStore implements StoreFactory {
    private static BookStore instance;

    private BookStore() {
    }

    ;

    public static synchronized BookStore getInstance() {
        if (instance == null)
            instance = new BookStore();

        return instance;
    }

    @Override
    public Store createStore(String name, String id) {
        return new shoppingmall.BookStore(name, id);
    }
}
