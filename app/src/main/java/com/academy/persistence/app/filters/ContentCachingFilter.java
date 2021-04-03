package com.academy.persistence.app.filters;

import com.academy.persistence.app.controllers.wrappers.RealCachingRequestWrapper;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class ContentCachingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        RealCachingRequestWrapper requestWrapper = new RealCachingRequestWrapper((HttpServletRequest) req);
        chain.doFilter(requestWrapper, resp);
    }
}
