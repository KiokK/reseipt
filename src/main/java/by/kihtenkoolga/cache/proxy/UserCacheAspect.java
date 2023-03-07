package by.kihtenkoolga.cache.proxy;
import by.kihtenkoolga.cache.CacheHandler;
import by.kihtenkoolga.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class UserCacheAspect {

    @Autowired
    private CacheHandler<Long, User> createUserCashHandler;

    @Around("@annotation(PostCache)")
    public User postCache(ProceedingJoinPoint joinPoint) throws Throwable {

        User serviceData = (User) joinPoint.proceed();
        Long id = serviceData.getId();

        createUserCashHandler.put(id, serviceData);
        System.out.println("POST ");
        return serviceData;
    }

    @Around("@annotation(PutCache)")
    public Object putCache(ProceedingJoinPoint joinPoint) throws Throwable {

        Object serviceData = joinPoint.getArgs()[0];

        User data = (User) serviceData;
        Long id = data.getId();

        createUserCashHandler.put(id, data);

        return data;
    }

    @Around("@annotation(GetCache)")
    public Object getCache(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean beanOptional = false;

        Object serviceData = joinPoint.proceed();

        if (Optional.class.equals(serviceData.getClass())) {
            serviceData = ((Optional) serviceData).get();
            beanOptional = true;
        }

        User data = (User) serviceData;
        Long id = data.getId();

        data = createUserCashHandler.get(id);

        if (data != null) {
            if (beanOptional)
                return Optional.of(data);
            return data;
        }

        serviceData = joinPoint.proceed();
        if (Optional.class.equals(serviceData.getClass()))
            data = (User)((Optional)serviceData).get();
        else
            data = (User)serviceData;

        createUserCashHandler.put(id, data);

        if (beanOptional)
            return Optional.of(data);
        return data;
    }

    @Around("@annotation(DeleteCache)")
    public void deleteCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Long id = (Long) joinPoint.getArgs()[0];

        createUserCashHandler.remove(id);
    }

}