package org.openutilities.rm.am;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;

/**
 * Main Spring Boot entry class.
 *
 * TODO Configure HTTP/2 or use gRPC
 *
 */
@SpringBootApplication
@EntityScan("org.openutilities.core.domain")
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}
