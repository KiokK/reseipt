package by.kihtenkoolga.config;

import by.kihtenkoolga.cache.CacheHandler;
import by.kihtenkoolga.cache.CacheHandlerCreator;
import by.kihtenkoolga.cache.proxy.UserCacheAspect;
import by.kihtenkoolga.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Value("${cache.capacity}")
    private int size;

    @Value("${cache.algorithm-type}")
    private CacheHandlerCreator.TypeOfHandler type;

    /**
     * Создание бина обработчика кэша данных класса <code>User</code> по соответствующему алгоритму
     * @return объект <code>CacheHandler</code> обработчик кэша пользователя
     */
    @Bean
    public CacheHandler<Long, User> createUserCashHandler() {
        return new CacheHandlerCreator(type, size).getCacheHandler();
    }

    /**
     * Создание бина CacheAspect для обработки кэша данных класса <code>User</code>
     * @param createUserCashHandler - бин обработчика кэша пользователя
     * @return объект <code>UserCacheAspect</code> для обработки кэша
     */
    @Bean
    public UserCacheAspect userCacheAspect(CacheHandler<Long, User> createUserCashHandler){
        return new UserCacheAspect(createUserCashHandler);
    }

}
