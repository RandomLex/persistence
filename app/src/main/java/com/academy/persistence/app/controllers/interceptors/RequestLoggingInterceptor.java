package com.academy.persistence.app.controllers.interceptors;

import com.academy.persistence.app.controllers.wrappers.RealCachingRequestWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Enumeration;

@Slf4j
@Component
public class RequestLoggingInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        logRequestUrl(req);
        logHeaders(req);
        logBody(req);
        return true;
    }

    @SneakyThrows
    private void logBody(HttpServletRequest req) {
        RealCachingRequestWrapper reqWrapper = (RealCachingRequestWrapper) req;
        String body = new String(reqWrapper.getContentAsByteArray(), req.getCharacterEncoding());
        log.info("Request Body: \n {}", body);
    }

    private void logRequestUrl(HttpServletRequest req) {
        log.info("{} {}", req.getMethod(), ServletUriComponentsBuilder.fromRequest(req).toUriString());
    }

    private void logHeaders(HttpServletRequest req) {
        Enumeration<String> headerNames = req.getHeaderNames();
        if (!headerNames.hasMoreElements()) {
            return;
        }
        log.debug("Headers");
        Collections.list(headerNames)
                .forEach(name -> log.debug("[{}]: {}", name, req.getHeader(name)));
    }
}
