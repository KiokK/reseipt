package by.kihtenkoolga.config;

import by.kihtenkoolga.cache.CacheHandler;
import by.kihtenkoolga.cache.factory.CacheFactory;
import by.kihtenkoolga.cache.factory.UserCacheFactory;
import by.kihtenkoolga.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Value("${cache.capacity}")
    private int size;

    @Value("${cache.algorithm-type}")
    private CacheFactory.TypeOfHandler type;

    /**
     * Создание бина обработчика кэша пользователя
     */
    @Bean
    public CacheHandler<Long, User> createUserCashHandler() {
        return new UserCacheFactory(type, size).getCacheHandler();
    }

}
