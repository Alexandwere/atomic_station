package org.javaacademy.atomic_station;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Runner {

    public static final int YEARS = 1;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);
        NuclearStation bean = context.getBean(NuclearStation.class);
        bean.start(YEARS);
    }
}
