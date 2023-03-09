package by.kihtenkoolga.cache.proxy;

import by.kihtenkoolga.cache.CacheHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Абстрактный класс обработки поиска данных в кэше перед взаимодействием с БД
 * @param <K> ключ для поиска данных в кэше, обязательно поле - id объекта, не null
 * @param <V> объект, который кэшируется
 */
public abstract class CacheAspect<K, V> {
    private CacheHandler<K, V> createCashHandler;

    public CacheAspect(CacheHandler<K, V> createCashHandler) {
        this.createCashHandler = createCashHandler;
    }

    @Around("@annotation(PostCache)")
    public V postCache(ProceedingJoinPoint joinPoint) throws Throwable {

        V serviceData = (V) joinPoint.proceed();
        Field idField = serviceData.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        K id = (K) idField.get(serviceData);

        createCashHandler.put(id, serviceData);

        return serviceData;
    }

    @Around("@annotation(PutCache)")
    public V putCache(ProceedingJoinPoint joinPoint) throws Throwable {

        V serviceData = (V) joinPoint.getArgs()[0];

        joinPoint.proceed();

        Field idField = serviceData.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        K id = (K) idField.get(serviceData);

        createCashHandler.put(id, serviceData);

        return serviceData;
    }

    @Around("@annotation(GetCache)")
    public V getCache(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean beanOptional = false;

        V serviceData = (V) joinPoint.proceed();

        if (Optional.class.equals(serviceData.getClass())) {
            serviceData = (V) ((Optional) serviceData).get();
            beanOptional = true;
        }

        Field idField = serviceData.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        K id = (K) idField.get(serviceData);

        serviceData = createCashHandler.get(id);

        if (serviceData != null) {
            if (beanOptional)
                return (V) Optional.of(serviceData);
            return serviceData;
        }

        serviceData = (V) joinPoint.proceed();
        if (Optional.class.equals(serviceData.getClass()))
            serviceData = (V) ((Optional)serviceData).get();

        createCashHandler.put(id, serviceData);

        if (beanOptional)
            return (V) Optional.of(serviceData);
        return serviceData;
    }

    @Around("@annotation(DeleteCache)")
    public void deleteCache(ProceedingJoinPoint joinPoint) {
        K id = (K) joinPoint.getArgs()[0];

        createCashHandler.remove(id);
    }
}
