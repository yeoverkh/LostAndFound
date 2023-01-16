package lostandfound.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for application
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Takes images from this upload path.
     * You can set your path in application.properties.
     */
    @Value("${upload.path}")
    private String uploadPath;

    /**
     *  Adds resource handler to support uploading images.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadPath + "/");
    }

    /**
     * Adds redirect view controller that redirects user on main page.
     */
    @Override
    public void addViewControllers (ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/main");
    }

    /**
     * Disables spring boot security's default login page.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        return http.build();
    }
}
