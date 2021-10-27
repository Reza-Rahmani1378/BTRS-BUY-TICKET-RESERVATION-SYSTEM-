package ir.maktab.btrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})


public class BtrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtrsApplication.class, args);
    }

}
