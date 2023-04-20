package com.moxa.micro;


import com.moxa.micro.core.config.MicroProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(MicroProperties.class)
@SpringBootApplication
public class MicroCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroCodeApplication.class, args);
    }

}
