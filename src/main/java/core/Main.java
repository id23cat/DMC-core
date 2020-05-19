package core;

import static org.springframework.boot.SpringApplication.run;

import core.configuration.RabbitConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RabbitConfiguration.class)
@EnableDiscoveryClient
public class Main {
    public static void main(String[] args) throws Exception {
        run(Main.class, args);
    }
}