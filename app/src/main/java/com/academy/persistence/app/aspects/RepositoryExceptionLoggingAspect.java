package com.academy.persistence.app.aspects;

import com.academy.persistence.app.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RepositoryExceptionLoggingAspect {

    @AfterThrowing(
            pointcut = "execution(* com.academy.persistence.app.repositories.AbstractRepositoryInMemory.remove(..))",
            throwing = "ex")
    public void loggingException(Exception ex) {
        log.error("Exception {}", ex.getMessage());
        throw new ApplicationException(ex);
    }
}
