package com.academy.persistence.app.config;

import com.academy.persistence.app.controllers.interceptors.RequestLoggingInterceptor;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

@Configuration
@AllArgsConstructor
@Import(SpringHibernateConfig.class)
public class ControllerInterceptorConfig implements WebMvcConfigurer {
    private final RequestLoggingInterceptor requestLoggingInterceptor;
    private final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addWebRequestInterceptor(openSessionInViewInterceptor());
        registry.addInterceptor(requestLoggingInterceptor);
    }

    @Bean
    public OpenSessionInViewInterceptor openSessionInViewInterceptor() {
        OpenSessionInViewInterceptor openSessionInViewInterceptor = new OpenSessionInViewInterceptor();
        SessionFactory sessionFactory = Objects.requireNonNull(entityManagerFactoryBean.getObject()).unwrap(SessionFactory.class);
        openSessionInViewInterceptor.setSessionFactory(sessionFactory);
        return openSessionInViewInterceptor;
    }
}
