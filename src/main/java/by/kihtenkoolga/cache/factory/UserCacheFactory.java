package by.kihtenkoolga.cache.factory;

import by.kihtenkoolga.cache.CacheHandler;
import by.kihtenkoolga.cache.impl.LFUCacheHandler;
import by.kihtenkoolga.cache.impl.LRUCacheHandler;
import by.kihtenkoolga.model.User;

public class UserCacheFactory implements CacheFactory<Long, User> {

    private CacheHandler<Long, User> userCacheHandler;

    public UserCacheFactory(TypeOfHandler typeOfHandler, int size) {
        initializeCacheHandler(typeOfHandler, size);
    }

    @Override
    public void initializeCacheHandler(TypeOfHandler typeOfHandler, int size) {
        if (typeOfHandler == TypeOfHandler.LFU)
            userCacheHandler = new LFUCacheHandler<>(size);
        else
            userCacheHandler = new LRUCacheHandler<>(size);
    }

    @Override
    public CacheHandler<Long, User> getCacheHandler() {
        return userCacheHandler;
    }

}
