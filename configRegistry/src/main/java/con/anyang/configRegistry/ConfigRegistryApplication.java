package con.anyang.configRegistry;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class ConfigRegistryApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ConfigRegistryApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}

