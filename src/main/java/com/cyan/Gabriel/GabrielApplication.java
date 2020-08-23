package com.cyan.Gabriel;

import com.cyan.Gabriel.model.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GabrielApplication {

	@Bean
	public FilterRegistrationBean filterJWTUser() {
		FilterRegistrationBean filterRbUser = new FilterRegistrationBean();
		filterRbUser.setFilter(new TokenFilter());

		filterRbUser.addUrlPatterns("/api/mill/*");
		filterRbUser.addUrlPatterns("/api/mills");
		filterRbUser.addUrlPatterns("/api/harv/*");
		filterRbUser.addUrlPatterns("/api/harvs");
		filterRbUser.addUrlPatterns("/api/farm/*");
		filterRbUser.addUrlPatterns("/api/farms");
		filterRbUser.addUrlPatterns("/api/field/*");
		filterRbUser.addUrlPatterns("/api/fields");

		return filterRbUser;
	}

	public static void main(String[] args) {
		SpringApplication.run(GabrielApplication.class, args);
	}

}
