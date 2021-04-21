package com.spring_stu.spring_PropertyEditor.string2enum;

import com.spring_stu.spring_PropertyEditor.string2enum.converter.GenderFormatterFactory;
import com.spring_stu.spring_PropertyEditor.string2enum.converter.String2EnumConverterFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
@ComponentScan(basePackages = "com.spring_stu.spring_PropertyEditor.string2enum")
public class WebConfig  {

    public WebConfig() {
        System.out.println("WebConfig的构造方法");
    }

    @Bean(name="conversionService")
    public FormattingConversionService conversionService() {
        System.out.println(" FormatterRegistry 被注册了");
//        FormattingConversionService registry = new DefaultFormattingConversionService();
        FormattingConversionService registry = new DefaultFormattingConversionService();

//        registry.addConverter(new String2StatusEnumConverter());
        registry.addConverterFactory(new String2EnumConverterFactory());
        registry.addFormatterForFieldAnnotation(new GenderFormatterFactory());
        return registry ;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);

        //UserEntity(username=null, password=null, gender2=StatusEnum(id=1, name=启用), gender=GenderEnum(id=0, name=男), status=null)
        Object userEntity = context.getBean("userEntity");
        System.out.println(userEntity);
    }


//    @Bean(name="mvcConversionService")
//    public FormattingConversionService mvcConversionService() {
//        System.out.println(" FormatterRegistry 被注册了");
////        FormattingConversionService registry = new DefaultFormattingConversionService();
//        FormattingConversionService registry = new DefaultFormattingConversionService();
//
////        registry.addConverter(new String2StatusEnumConverter());
//        registry.addConverterFactory(new String2EnumConverterFactory());
//        registry.addFormatterForFieldAnnotation(new GenderFormatterFactory());
//        return registry ;
//    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        System.out.println(" addFormatters 被调用了");
//        // 为webMVC注册转换器
//        registry.addConverter(new String2StatusEnumConverter());
//        registry.addConverterFactory(new String2EnumConverterFactory());
//        registry.addFormatterForFieldAnnotation(new GenderFormatterFactory());
//    }
}