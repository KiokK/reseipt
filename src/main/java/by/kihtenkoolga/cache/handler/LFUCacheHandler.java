package by.kihtenkoolga.cache.handler;

import by.kihtenkoolga.cache.CacheHandler;
import by.kihtenkoolga.cache.CacheValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс <code>LFUCacheHandler</code> кэширует объекты подсчитывает частоту использования каждого элемента
 * и удаляет те, к которым обращаются реже всего
 */
@Getter
public class LFUCacheHandler<K, V> implements CacheHandler<K, V> {

    private Map<K, CacheValue<Long, V>> cacheBase = new HashMap<>();

    private final int CACHE_CAPACITY;

    public LFUCacheHandler(int capacity) {
        this.CACHE_CAPACITY = capacity;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null)
            return null;
        CacheValue<Long, V> findCashValue = cacheBase.get(key);
        if (findCashValue == null || value != findCashValue.getValue())
            add(key, value);
        else
            upCountOfUseObject(key);
        return value;
    }

    @Override
    public V get(K key) {
        if (!cacheBase.containsKey(key))
            return null;

        CacheValue<Long, V> cacheValue = cacheBase.get(key);
        upCountOfUseObject(key);

        return cacheValue.getValue();
    }

    @Override
    public void remove(K key) {
        cacheBase.remove(key);
    }

    /**
     * Добавляет в кэш или обноввляет в нем новое значение
     * @param key id, кэшируемого, объекта, не null
     * @param value кэшируемый объект
     */
    private void add(K key, V value) {
        if (cacheBase.size() == CACHE_CAPACITY)
            cacheBase.remove(minUseCacheObjectKey());

        cacheBase.put(key, new CacheValue<>(1L, value));
    }

    /**
     * @return id минимально используемого объекта
     */
    private K minUseCacheObjectKey() {
        if (cacheBase.isEmpty())
            return null;

        K key = cacheBase.entrySet().stream()
                .sorted((o1, o2) -> (int) (o1.getValue().getKey() - o2.getValue().getKey()))
                .collect(Collectors.toList())
                .get(0)
                .getKey();
        return key;
    }

    /**
     * Увеличивает частоту использований кэшируемого объекта
     * @param key id кэшируемого объекта
     */
    private void upCountOfUseObject(K key) {
        CacheValue<Long, V> cacheValue = cacheBase.get(key);
        cacheValue.setKey(cacheValue.getKey() + 1);
    }

}
