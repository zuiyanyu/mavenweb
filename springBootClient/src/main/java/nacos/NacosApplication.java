package nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @EnableNacosConfig 注解启用 Nacos Spring 的配置管理服务。
 * @NacosPropertySource加载了dataId为nacos-spring`的配置源，并开启自动更新。
 * 对 Nacos Spring 的用户来说，在自身应用中就只是设置 “autoRefreshed” 的一个布尔值。然后在需要修改配置的时候，调用 Nacos 修改配置的接口，或使用 Nacos 的控制台去修改，配置发生变更后， Nacos 就会把最新的配置推送到该应用的所有机器上。
 *
 */
@SpringBootApplication
//注解启用 Nacos Spring 的配置管理服务。
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "hadoop102:8848"))
//@NacosPropertySource(dataId = "nacos-spring", autoRefreshed = true)
//@Controller
//@NacosPropertySource(dataId = "bamboo.test", autoRefreshed = true)
public class NacosApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NacosApplication.class, args);
//        run.getBean(Runnable.class).run();
    }
//    @NacosValue(value = "${service.name:1}", autoRefreshed = true)
//    private String serverName;
////
//    @RequestMapping(value = "/test", method = GET)
//    @ResponseBody
//    public String get() {
//        return serverName;
//    }

//    @Bean
//    public Runnable createRunnable() {
//        return () -> System.out.println("spring boot is running");
//    }

}
