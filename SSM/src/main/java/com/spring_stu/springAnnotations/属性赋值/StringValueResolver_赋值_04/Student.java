package com.spring_stu.springAnnotations.属性赋值.StringValueResolver_赋值_04;


import lombok.Data;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * EmbeddedValueResolverAware 用来获取字符串解析器
 */
@Data
public class Student implements EmbeddedValueResolverAware {

    //TODO 4. 可以使用StringValueResolver，取出配置文件中的值(在运行环境变量里面的值)
    //TODO    需要使用 @PropertySource("classpath:属性配置文件路径") 先进行配置文件的加载。
    private String friends ;

    /**
     * StringValueResolver 字符串解析器
     * @param resolver
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        //从配置文件中获取stu.friends的属性值
        String friends = resolver.resolveStringValue("${stu.friends}");
        this.friends = friends+"_resolver" ;
    }
}
