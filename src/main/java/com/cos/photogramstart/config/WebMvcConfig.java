package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{ //web설정파일
	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			WebMvcConfigurer.super.addResourceHandlers(registry);
			
			registry.addResourceHandler("/upload/**") //jsp페이지에서 /upload/** 이런 주소패턴이 나오면 발동
			.addResourceLocations("file:///"+uploadFolder) //위에꺼처럼 적은게 있으면 이 줄에있는 정보로 바뀐다
			.setCachePeriod(60 * 10 * 6) //1시간 동안 캐시
			.resourceChain(true)
			.addResolver(new PathResourceResolver()); 
		}
}
