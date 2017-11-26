package org.openutilities.core.appservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.openutilities.core.domain")
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}
