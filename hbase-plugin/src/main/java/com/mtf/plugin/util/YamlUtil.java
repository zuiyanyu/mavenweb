//package com.mtf.plugin.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.env.YamlPropertySourceLoader;
//import org.springframework.core.env.PropertySource;
//import org.springframework.core.io.ClassPathResource;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class YamlUtil {
//    private static  Logger LOGGER =LoggerFactory.getLogger(YamlUtil.class) ;
//    private static YamlUtil yamlUtil ;
//    private Map<String ,Object> yamlParams ;
//
//    public static synchronized YamlUtil _getInstance(){
//        if(null==yamlUtil){
//            yamlUtil = new YamlUtil() ;
//        }
//        return  yamlUtil ;
//    }
//
//    private YamlUtil(){
//        yamlParams = getYaml();
//    }
//
//    public Map<String ,Object> getYamlParams(){
//            return  yamlParams ;
//    }
//    /**
//     * 加载 application.yml 中的参数
//     * @return
//     */
//    private Map<String,Object> getYaml() {
//        ClassPathResource classPathResource = new ClassPathResource("application.yml", ClassLoader.getSystemClassLoader());
//        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
//        try {
//            List<PropertySource<?>> propertySources = yamlPropertySourceLoader.load("application.yml", classPathResource);
//            Map <String,Object> paramsMap = new HashMap<>();
//            LOGGER.info("propertySources.size() = "+propertySources.size());
//
//            for (PropertySource<?> propertySource : propertySources) {
//                Map<String,Object> source = ( Map<String,Object>)propertySource.getSource();
//                paramsMap.putAll(source);
//                LOGGER.info("paramsMap= "+paramsMap);
////                Object property = propertySource.getProperty("User.age[2]");
////                System.out.println("User.age[2]="+property);
//            }
//            return paramsMap ;
//        } catch (Exception e) {
//            LOGGER.error("读取application.yml配置文件失败！异常原因：{}",e.getMessage());
//        }
//        return null ;
//    }
//
//    public String getYamlValueForKey(String key){
//        if(null!=yamlParams && yamlParams.size()>0){
//            return  yamlParams.getOrDefault(key,"").toString() ;
//        }
//        return  null ;
//    }
//
//    public static void main(String[] args) {
//        YamlUtil yamlUtil = YamlUtil._getInstance();
////        String yamlValueForKey = yamlUtil.getYamlValueForKey("User.age[2]");
////        System.out.println(yamlValueForKey);
//
//    }
//}
