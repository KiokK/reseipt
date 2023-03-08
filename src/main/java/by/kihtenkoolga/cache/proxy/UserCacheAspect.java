package by.kihtenkoolga.cache.proxy;
import by.kihtenkoolga.cache.CacheHandler;
import by.kihtenkoolga.model.User;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class UserCacheAspect extends CacheAspect {

    public UserCacheAspect(CacheHandler<Long, User> createCashHandler) {
        super(createCashHandler);
    }

}