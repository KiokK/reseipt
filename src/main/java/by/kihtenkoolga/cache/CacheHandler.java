package by.kihtenkoolga.cache;

/**
 * Интерфейс, который реализуют обработчики кэша
 * @param <K> ключ для поиска данных в кэше, id объекта
 * @param <V> объект, который кэшируется
 */
public interface CacheHandler<K, V> {

    /**
     * Вставка объекта в кэш
     * @param key id объекта
     * @param value объект
     */
    void put(K key, V value);

    /**
     * Получение объекта из кэша
     * @param key id объекта
     * @return объект из кэша
     */
    V get(K key);

    /**
     * Удаление объекта из кэша по ключу
     * @param key id кэшируемого объекта
     */
    void remove(K key);

}
