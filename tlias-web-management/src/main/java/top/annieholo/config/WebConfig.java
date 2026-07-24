package top.annieholo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.annieholo.interceptor.DemoInterceptor;
import top.annieholo.interceptor.TokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private DemoInterceptor demoInterceptor;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")// 拦截所有请求
                .excludePathPatterns("/login"); // 排除/login接口
        /**
         *  /* 一级路径 /depts /emps /login
         *  /** 任意级路径  /depts/1/2/3
         *  /depts/* /depts下的一级路径
         *  /depts/** /depts下的任意路径
         */
    }
}
