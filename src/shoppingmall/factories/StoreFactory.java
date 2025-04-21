package shoppingmall.factories;

import shoppingmall.Store;

public interface StoreFactory {
    Store createStore(String name, String id);
}
