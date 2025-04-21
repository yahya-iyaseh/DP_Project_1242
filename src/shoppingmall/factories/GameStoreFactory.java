package shoppingmall.factories;

import shoppingmall.BookStore;
import shoppingmall.Store;

public class GameStoreFactory implements StoreFactory {
    private static GameStoreFactory instance;

    private GameStoreFactory() {
    }

    ;

    public static synchronized GameStoreFactory getInstance() {
        if (instance == null)
            instance = new GameStoreFactory();

        return instance;
    }

    @Override
    public Store createStore(String name, String id) {
        return new BookStore(name, id);
    }
}
