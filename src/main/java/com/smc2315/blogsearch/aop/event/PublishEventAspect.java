package com.smc2315.blogsearch.aop.event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

@Component
@Aspect
public class PublishEventAspect implements ApplicationEventPublisherAware {

    private final String spelRegex = "^#\\{(.*)\\}$";
    private final Pattern spelPattern = Pattern.compile(spelRegex);

    private ApplicationEventPublisher eventPublisher;
    private ExpressionParser expressionParser = new SpelExpressionParser();

    @Pointcut("@annotation(publishEvent)")
    public void pointcut(PublishEvent publishEvent) {
    }

    @Before("pointcut(publishEvent) && args(..)")
    public void beforeMethod(JoinPoint joinPoint, PublishEvent publishEvent) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object event = createEvent(joinPoint, publishEvent);
        eventPublisher.publishEvent(event);
    }

    private Object createEvent(JoinPoint joinPoint, PublishEvent publishEvent) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] args = joinPoint.getArgs();

        if (args == null || args.length == 0) {
            return createEventWithoutArgs(publishEvent);
        }
        if (!StringUtils.hasLength(publishEvent.params())) {
            return createEventWithoutParam(publishEvent, args[0]);
        }
        if (isSpel(publishEvent.params())) {
            return createEventWithSpel(publishEvent, args[0]);
        }
        return createEventWithStringParam(publishEvent);
    }

    private Object createEventWithoutArgs(PublishEvent publishEvent) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return publishEvent.eventType()
                .getDeclaredConstructor()
                .newInstance();
    }

    private Object createEventWithoutParam(PublishEvent publishEvent, Object arg) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        return publishEvent.eventType()
                .getConstructor(arg.getClass())
                .newInstance(arg);
    }

    private Object createEventWithSpel(PublishEvent publishEvent, Object arg) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        String spel = publishEvent.params().replaceAll(spelRegex, "$1");
        Object constructArg = expressionParser.parseExpression(spel).getValue(arg);
        return publishEvent.eventType()
                .getDeclaredConstructor(constructArg.getClass())
                .newInstance(constructArg);
    }

    private Object createEventWithStringParam(PublishEvent publishEvent) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        return publishEvent.eventType().getConstructor(String.class).newInstance(publishEvent.params());
    }


    private boolean isSpel(String params) {
        return spelPattern.matcher(params).matches();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}