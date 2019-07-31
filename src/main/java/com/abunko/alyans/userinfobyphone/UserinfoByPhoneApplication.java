package com.abunko.alyans.userinfobyphone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class UserinfoByPhoneApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UserinfoByPhoneApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UserinfoByPhoneApplication.class);
    }

}
