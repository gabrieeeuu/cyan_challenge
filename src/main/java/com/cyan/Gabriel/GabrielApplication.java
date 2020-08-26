package com.cyan.Gabriel;

import com.cyan.Gabriel.model.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class GabrielApplication {

	@Bean
	public FilterRegistrationBean filterJWTUser() {
		FilterRegistrationBean filterRbUser = new FilterRegistrationBean();
		filterRbUser.setFilter(new TokenFilter());

		filterRbUser.addUrlPatterns("/api/auth/user");
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
    
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

	public static void main(String[] args) {
		SpringApplication.run(GabrielApplication.class, args);
	}

}
