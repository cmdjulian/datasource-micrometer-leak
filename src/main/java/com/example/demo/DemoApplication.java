package com.example.demo;

import net.ttddyy.observation.boot.autoconfigure.DataSourceNameResolver;
import net.ttddyy.observation.boot.autoconfigure.DefaultDataSourceNameResolver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceNameResolver defaultDataSourceNameResolver() {
        return new DefaultDataSourceNameResolver();
    }

    @Bean
    public CommandLineRunner onStart(TestRepository repository) {
        return args -> {
            int sequence = 0;
            while (!Thread.currentThread().isInterrupted()) {
                if (sequence++ % 2 == 0) {
                    repository.save(new TestEntity(sequence));
                } else {
                    repository.deleteById(sequence - 1);
                }
            }
        };
    }
}
