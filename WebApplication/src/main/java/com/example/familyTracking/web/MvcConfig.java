package com.example.familyTracking.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry reg) {
        reg.addViewController("/login")
                .setViewName("login");

        reg.addViewController("/")
                .setViewName("index");

        reg.addViewController("/main")
                .setViewName("main");

        reg.addViewController("/friends")
                .setViewName("friends");

        reg.addViewController("/profile")
                .setViewName("profile");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/public","classpath:/static/").setCachePeriod(0);

    }
}
