package backend.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Starter {

    public static void main(String[] args){
        SpringApplication springApplication = new SpringApplication(Starter.class);
        springApplication.run(args);
    }
}
