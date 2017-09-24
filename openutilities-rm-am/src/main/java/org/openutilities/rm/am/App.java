package org.openutilities.rm.am;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main Spring Boot entry class.
 *
 * TODO Configure HTTP/2 or use gRPC
 *
 */
@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}
