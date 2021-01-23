package cc.mm.demo4;

import cc.mm.demo4.interceptors.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/profile");   //测试demo，仅对 /profile 路径做token验证
                //.excludePathPatterns("/")

    }
}
