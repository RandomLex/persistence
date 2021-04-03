package com.academy.persistence.app.controllers.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
@Component
public class RequestLoggingInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        logRequestUrl(req);
        logHeaders(req);
        return true;
    }

    private void logHeaders(HttpServletRequest req) {
        Enumeration<String> headerNames = req.getHeaderNames();
        if (headerNames.hasMoreElements()) {
            log.debug("Headers");
        }
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            log.debug("[{}]: {}", name, req.getHeader(name));
        }
    }

    private void logRequestUrl(HttpServletRequest req) {
        log.info("{} {}", req.getMethod(), ServletUriComponentsBuilder.fromRequest(req).toUriString());
    }
}
