package nacos.nacosClient;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SpringBootApplication
@RestController
@NacosPropertySource(dataId = "bamboo.test", autoRefreshed = true)
public class NacosApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NacosApplication.class, args);
        run.getBean(Runnable.class).run();
    }
    @NacosValue(value = "${service.name:1}", autoRefreshed = true)
    private String serverName;

    @RequestMapping(value = "/test", method = GET)
    @ResponseBody
    public String get() {
        return serverName;
    }

    @Bean
    public Runnable createRunnable() {
        return () -> System.out.println("spring boot is running");
    }

}
