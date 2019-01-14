package com.buxiu.bootexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.buxiu.bootexample.interceptor.TokenInterceptor;


@Configuration
// jwt拦截器
public class InterceptorConfig implements WebMvcConfigurer {

	@Bean
    public HandlerInterceptorAdapter getTokenInterceptor(){
        return new TokenInterceptor();
    }
	
	// 增加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(getTokenInterceptor()) // 指定拦截器类
			.addPathPatterns("/**"); // 指定该类拦截的url
		
	}

}
