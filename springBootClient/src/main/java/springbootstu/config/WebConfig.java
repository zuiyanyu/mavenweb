package springbootstu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springbootstu.converters.GenderFormatterFactory;
import springbootstu.converters.String2EnumConverterFactory;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 为webMVC注册转换器
//        registry.addConverter(new String2StatusEnumConverter());
        registry.addConverterFactory(new String2EnumConverterFactory());
        registry.addFormatterForFieldAnnotation(new GenderFormatterFactory());
    }


}
