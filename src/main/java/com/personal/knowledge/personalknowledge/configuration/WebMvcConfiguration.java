package com.personal.knowledge.personalknowledge.configuration;

import com.personal.knowledge.personalknowledge.controllers.interceptors.ApplicationBasicTokenInterceptor;
import com.personal.knowledge.personalknowledge.controllers.interceptors.ControllerLoggerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${security.app.authorization.user}")
    private String user;

    @Value("${security.app.authorization.password}")
    private String password;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerLoggerInterceptor());
        registry.addInterceptor(new ApplicationBasicTokenInterceptor(user, password));
    }
}
