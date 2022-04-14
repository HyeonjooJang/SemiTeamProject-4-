package com.campus.myapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.campus.myapp.interceptor.LoginInterceptor;
import com.campus.myapp.interceptor.MasterInterceptor;


@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
		.excludePathPatterns("/","/img/**","/css/**","/js/**","/member/**");
		
		registry.addInterceptor(new MasterInterceptor())
		.addPathPatterns("/master/**");
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8"); // ���� ���ڵ� ����
		multipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024); // ���ϴ� ���ε� ũ�� ���� (5MB)
		return multipartResolver;
	}
	
}

