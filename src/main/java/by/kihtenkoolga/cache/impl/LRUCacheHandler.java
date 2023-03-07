package by.kihtenkoolga.cache.impl;

import by.kihtenkoolga.cache.CacheHandler;
import by.kihtenkoolga.cache.CacheValue;
import lombok.Getter;

import java.util.*;

/**
 * Класс <code>LRUCacheHandler</code> кэширует объекты подсчитывает частоту использования каждого элемента.
 * Элемент, который не использовался в течение самого длительного времени, будет удален из кэша.
 */
@Getter
public class LRUCacheHandler<K, V> implements CacheHandler<K, V> {

    private final int CACHE_SIZE;

    private final LinkedHashMap<K, CacheValue<K, V>> cacheBase = new LinkedHashMap<>();

    public LRUCacheHandler(int capacity) {
        CACHE_SIZE = capacity;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null)
            return null;
        if (cacheBase.size() + 1 > CACHE_SIZE)
            cacheBase.remove(getKeyOfFirst());
        cacheBase.put(key, new CacheValue<>(key, value));
        return value;
    }

    @Override
    public V get(K key) {
        V findValue = (V) cacheBase.getOrDefault(key, null);
        if (findValue == null)
            return null;

        CacheValue<K, V> cacheValue = new CacheValue<>(key, findValue);
        cacheBase.remove(cacheValue);
        cacheBase.put(key, cacheValue);

        return findValue;
    }

    @Override
    public void remove(K key) {
        cacheBase.remove(key);
    }

    /**
     * Возвращает ключ элемента, который не использовался в течение самого длительного времени
     * @return ключ кэшируемого объекта
     */
    private K getKeyOfFirst(){
        return cacheBase.entrySet().stream().findFirst().get().getKey();
    }

}
