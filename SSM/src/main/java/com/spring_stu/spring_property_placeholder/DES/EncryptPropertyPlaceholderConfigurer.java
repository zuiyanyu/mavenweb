package com.spring_stu.spring_property_placeholder.DES;

import com.github.pagehelper.StringUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 1. PropertySourcesPlaceholderConfigurer重写了PropertyResourceConfigurer的postProcessBeanFactory()方法
 * 2. 编码方式添加属性配置项：有一个方法会被调用：mergeProperties(),因此我们可以重写这个方法来添加或者处理配置项。
 * 3. 重写processProperties()方法，属性注入前进行属性值的处理和修改等。
 */
@Component
public class EncryptPropertyPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer implements BeanFactoryAware {
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     final ConfigurablePropertyResolver propertyResolver) throws BeansException {
        propertyResolver.setPlaceholderPrefix(this.placeholderPrefix);
        propertyResolver.setPlaceholderSuffix(this.placeholderSuffix);
        propertyResolver.setValueSeparator(this.valueSeparator);

        StringValueResolver valueResolver = strVal -> {
            String resolved = (this.ignoreUnresolvablePlaceholders ?
                    propertyResolver.resolvePlaceholders(strVal) :
                    propertyResolver.resolveRequiredPlaceholders(strVal));
            if (this.trimValues) {
                resolved = resolved.trim();
            }

            // 开始添加自己的处理逻辑
            try{
                String prefix = "["+this.placeholderPrefix+"]";
                String suffix = "["+this.placeholderSuffix+"]";
                String propertyName =strVal.replaceAll(prefix,"")
                        .replaceAll(suffix,"") ;
                String propertyValue  = decryptString(propertyName,resolved);
                if(null !=propertyValue){
                    resolved = propertyValue ;
                }
            }catch (Exception e){
                System.out.println("自定义处理属性注入值处理逻辑失败："+e.getMessage());
            }
            // 添加完毕

            return (resolved.equals(this.nullValue) ? null : resolved);
        };

        doProcessProperties(beanFactoryToProcess, valueResolver);
    }
    private String decryptString(String propertyName,String propertyValue){
        try{

            if(!StringUtil.isEmpty(propertyName) && !StringUtil.isEmpty(propertyValue) && propertyName.toLowerCase().startsWith("eds.")){
                propertyValue = DESUtils.getDecryptString(propertyValue);
                System.out.println("DES进行加密的属性值解密："+ propertyName + "->" + propertyValue);

            }
            return propertyValue ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }

    /**
     * 手动代码形式添加property属性,来源归属到本地属性：LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME
     * @return
     * @throws IOException
     */
    @Override
    protected Properties mergeProperties() throws IOException {
        //加载父类的配置信息项
        /*<context:property-placeholder location="classpath:jdbcInfo.properties"/>*/
        Properties result = super.mergeProperties();
        result.forEach((k,v) -> System.out.println(k+"->"+v));

        //com.mchange.v2.c3p0.ComboPooledDataSource 的简单配置项
        result.setProperty("c3p0.driverClass","com.mysql.jdbc.Driver") ;
        result.setProperty("c3p0.jdbcUrl","jdbc:mysql://localhost:3306/mavenweb") ;
        result.setProperty("c3p0.user","root") ;
        result.setProperty("c3p0.password","123456") ;
        result.setProperty("eds.jdbc.password2","eds.jdbc.password") ;

        //对每一个property 进行处理
        return result;
    }
//    //使用了加密的属性
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        super.postProcessBeanFactory(beanFactory);

        //扫描打印系统环境 和 本地环境的属性配置项
        scanPropertys(getAppliedPropertySources());
    }
    /**
     * 这种方式只能访问到 环境变量 和 mergeProperties()方法添加的本地属性
     * 即只能遍历 LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME 和 ENVIRONMENT_PROPERTIES_PROPERTY_SOURCE_NAME 这两部分的数据，不能遍历其他部分的属性数据
     */
    private void scanPropertys(PropertySources propertySources){

        for (PropertySource<?> propertySource : propertySources) {
            if(LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME.equals(propertySource.getName())){
                Properties localSource = (Properties)propertySource.getSource();
                System.out.println("================"+LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME+"====================");
                System.out.println(localSource.getClass());

                Set<Object> keyNames = localSource.keySet();
                for (Object propertyName : keyNames) {
                    String propertyValue = localSource.getProperty(propertyName.toString());
                    System.out.println(propertyName+ " --> " + propertyValue);
                }
            }else if(ENVIRONMENT_PROPERTIES_PROPERTY_SOURCE_NAME.equals(propertySource.getName())){
                StandardEnvironment environmentSource = (StandardEnvironment)propertySource.getSource();

                System.out.println("================="+ENVIRONMENT_PROPERTIES_PROPERTY_SOURCE_NAME+"============================");
                System.out.println(environmentSource.getClass());

                Map<String, Object> systemEnvironment = environmentSource.getSystemEnvironment();
                for(Map.Entry entry :systemEnvironment.entrySet()){
                    Object propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    System.out.println(propertyName+ " --> " + propertyValue);

                }
            }else{
                Object source = propertySource.getSource();
                System.out.println(source.getClass());
            }
         }
    }


    private String[] encryptPropNames = {"eds.jdbc.user","eds.jdbc.password"};
    //对指定属性进行转码
    protected Properties convertProperty() {
        Properties result = new Properties();
        PropertySources appliedPropertySources = this.getAppliedPropertySources();

        Iterator<PropertySource<?>> iterator = appliedPropertySources.iterator();
        while( iterator.hasNext()){
            PropertySource<?> propertySource = iterator.next();

            for (String propertyName : encryptPropNames) {
                Object propertyValue = propertySource.getProperty(propertyName);
                if(null !=propertyValue){
                    String decryptValue = DESUtils.getDecryptString(propertyValue.toString());
                    //System.out.println(propertyName + "->" + decryptValue);
                    result.setProperty(propertyName, decryptValue);
                }
            }
         }

        return result;
    }
}
