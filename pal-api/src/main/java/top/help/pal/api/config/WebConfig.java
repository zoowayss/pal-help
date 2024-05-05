package top.help.pal.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.help.pal.api.token.TokenInterceptor;
import top.help.pal.api.token.TokenMethodArgResolver;
import top.help.pal.common.token.TokenUser;

import java.util.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(token -> new TokenUser(1L, "test", "1.1.1.1")));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TokenMethodArgResolver());
    }
}
