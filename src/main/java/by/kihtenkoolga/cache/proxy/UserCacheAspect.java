package by.kihtenkoolga.cache.proxy;
import by.kihtenkoolga.cache.CacheHandler;
import by.kihtenkoolga.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserCacheAspect {

    @Autowired
    private CacheHandler<Long, User> createUserCashHandler;


    @Around("@annotation(PostCache)")
    public void postCache(ProceedingJoinPoint joinPoint) {

        User serviceData = (User) joinPoint.getArgs()[0];
        Long id = serviceData.getId();

        createUserCashHandler.put(id, serviceData);
    }

}