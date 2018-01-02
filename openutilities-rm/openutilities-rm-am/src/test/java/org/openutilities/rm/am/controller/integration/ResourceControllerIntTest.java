package org.openutilities.rm.am.controller.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openutilities.core.domain.Resource;
import org.openutilities.core.domain.builder.ResourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResourceControllerIntTest
{
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getResource() throws Exception
    {
        String code = "up-1";
        Resource upResource = ResourceBuilder.aResource().id(1L).code(code).typeId(1L).build();

        // Finds and return the resource
        this.webTestClient.get()
                .uri(String.format("/resources/%s",code))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Resource.class);


        // Doesn't find the resource and return an empty one
        this.webTestClient.get()
                .uri(String.format("/resources/%s","up-3"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Resource.class);
    }
}
