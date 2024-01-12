package com.smc2315.blogsearch.aop.lock;

import com.smc2315.blogsearch.utils.CustomSpringELParser;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class DistributeLockAspect {

    private static final String REDISSON_KEY_PREFIX = "RLOCK_";

    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;

    @Around("@annotation(com.smc2315.blogsearch.aop.lock.DistributeLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        String key = REDISSON_KEY_PREFIX + CustomSpringELParser.getDynamicValue(signature.getParameterNames(),
                joinPoint.getArgs(), distributeLock.key());

        RLock rLock = redissonClient.getLock(key);
        boolean available = false;
        try {
            available = rLock.tryLock(distributeLock.waitTime(), distributeLock.leaseTime(), distributeLock.timeUnit());
            System.out.println("available = " + available);
            if (!available) {
                return false;
            }
            return aopForTransaction.proceed(joinPoint);
            //return joinPoint.proceed();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            throw new InterruptedException();
        } finally {
            if(available) {
                rLock.unlock();
            }
        }
    }
}
