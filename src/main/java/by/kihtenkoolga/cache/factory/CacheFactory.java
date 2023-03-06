package by.kihtenkoolga.cache.factory;

import by.kihtenkoolga.cache.CacheHandler;

public interface CacheFactory<K, V>  {

    enum TypeOfHandler {
        LRU,
        LFU
    }

    void initializeCacheHandler(TypeOfHandler typeOfHandler, int size);

    CacheHandler<K, V> getCacheHandler();

}
