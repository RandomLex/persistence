package com.academy.persistence.app.aspects;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class RequestLoggingAspect {

    @Pointcut("execution(* com.academy.persistence.app.controllers.*.*(..))")
    public void controllers() {

    }

    /**
     * Logging all the requests come to our controller methods
     * @param joinPoint {@link JoinPoint}
     */
    @Before("controllers()")
    public void before(JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof HttpServletRequest)
                .findAny()
                .map(arg -> (HttpServletRequest) arg)
                .ifPresent(req -> log.info("{} {}", req.getMethod(), ServletUriComponentsBuilder.fromRequest(req).toUriString()));
    }

//    @After("controllers()")
//    public void after(JoinPoint joinPoint) {
//        log.info("It's after: {}", joinPoint.getSignature().getName());
////        log.info(joinPoint.getKind());
////        log.info(joinPoint.toLongString());
////        log.info(joinPoint.toShortString());
////        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
////        log.info(joinPoint.getStaticPart().toLongString());
////        log.info(joinPoint.getThis().toString());
//        log.info("-------------");
//    }

//    @Around("controllers()")
//    @SneakyThrows
//    public Object around(ProceedingJoinPoint jp) {
//        log.info("It's around before: {}", jp.getSignature().getName());
//        Object result = jp.proceed();
//        log.info("It's around after: {}", jp.getSignature().getName());
//        return result;
//    }
}
