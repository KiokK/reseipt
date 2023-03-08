package by.kihtenkoolga.cache;

import by.kihtenkoolga.cache.handler.LFUCacheHandler;
import by.kihtenkoolga.cache.handler.LRUCacheHandler;

/**
 * <code>CacheHandlerCreator</code> создает объект класса, с соответствующим алгоритмом кеширования,
 * который реализует {@link CacheHandler}
 * @param <K> ключ для поиска данных в кэше, id объекта, не null
 * @param <V> объект, который кэшируется
 */
public class CacheHandlerCreator<K, V> {

    private CacheHandler<K, V> objectCacheHandler;

    public CacheHandlerCreator(TypeOfHandler typeOfHandler, int size) {
        initializeCacheHandler(typeOfHandler, size);
    }

    public void initializeCacheHandler(TypeOfHandler typeOfHandler, int size) {
        if (typeOfHandler == TypeOfHandler.LFU)
            objectCacheHandler = new LFUCacheHandler<>(size);
        else
            objectCacheHandler = new LRUCacheHandler<>(size);
    }

    public CacheHandler<K, V> getCacheHandler() {
        return objectCacheHandler;
    }

    public enum TypeOfHandler {
        LRU,
        LFU
    }
}
