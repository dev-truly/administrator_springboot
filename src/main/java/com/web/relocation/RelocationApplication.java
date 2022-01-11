package com.web.relocation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.Jsr310Converters;

@SpringBootApplication
@EntityScan(
        basePackageClasses = { Jsr310Converters.class },
        basePackages = { "com.web.relocation.entity" }
)
public class RelocationApplication {

    public static void main(String[] args) { SpringApplication.run(RelocationApplication.class, args); }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println(">>>> 스프링부트 러너  <<<< ");
            System.out.println("스프링 부트 애플리케이션 구동시 시작되는 프로그램을 살펴보자");

            String[] beanNames = ctx.getBeanDefinitionNames();
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
}
