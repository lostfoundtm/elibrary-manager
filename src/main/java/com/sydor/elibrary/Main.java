package com.sydor.elibrary;

import com.sydor.elibrary.app.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting e-library...\n");
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        Application application = context.getBean(Application.class, "application");
        application.start();
        context.close();
    }
}
