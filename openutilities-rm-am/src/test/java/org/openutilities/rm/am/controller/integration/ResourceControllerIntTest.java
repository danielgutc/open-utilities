package org.openutilities.rm.am.controller.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openutilities.rm.am.controller.ResourceController;
import org.openutilities.rm.am.domain.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
        Resource upResource = Resource.builder().id(1L).code(code).resourceType("UP").build();

        // Finds and return the resource
        this.webTestClient.get()
                .uri(String.format("/resource/%s",code))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Resource.class)
                .isEqualTo(upResource);


        // Doesn't find the resource and return an empty one
        this.webTestClient.get()
                .uri(String.format("/resource/%s","up-3"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Resource.class)
                .isEqualTo(Resource.builder().build());
    }
}
