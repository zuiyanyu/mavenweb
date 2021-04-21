package springbootstu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootClientApplication.class, args);
        run.getBean(Runnable.class).run();
     }

    @Bean
    public Runnable createRunnable() {
        return () -> System.out.println("spring boot is running");
    }

}
