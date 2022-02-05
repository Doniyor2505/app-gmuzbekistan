package uz.digitalone.appgmuzbekistan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.digitalone.appgmuzbekistan.properties.StorageProperties;

@EnableConfigurationProperties({
        StorageProperties.class
})
@SpringBootApplication
public class AppGmuzbekistanApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppGmuzbekistanApplication.class, args);
    }

}
