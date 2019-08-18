package com.dyp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    /**
     * 自己定义的拦截器类
     * @return
     */
    @Bean
    PlatformLoginInterceptor PlatformLoginInterceptor() {
        return new PlatformLoginInterceptor();

    }
  //配置静态资源放行
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加登录处理拦截器，拦截所有请求，登录请求除外
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(PlatformLoginInterceptor());
        //排除配置
        interceptorRegistration.excludePathPatterns("/images/**");
        interceptorRegistration.excludePathPatterns("/swagger-resources/**");
        interceptorRegistration.excludePathPatterns("/static/**");
        interceptorRegistration.excludePathPatterns("/webjars/**");
        interceptorRegistration.excludePathPatterns("/static/**");
        interceptorRegistration.excludePathPatterns("/js/**");
        interceptorRegistration.excludePathPatterns("/v2/**");
        interceptorRegistration.excludePathPatterns("/index.html");
        interceptorRegistration.excludePathPatterns("/error/index");
        interceptorRegistration.excludePathPatterns("/swagger-ui.html");
        //配置拦截策略
        interceptorRegistration.addPathPatterns("/**");
    }
}
