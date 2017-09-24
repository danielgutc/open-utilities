package org.openutilities.rm.am.controller.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.openutilities.rm.am.controller.ResourceController;
import org.openutilities.rm.am.domain.Resource;
import org.openutilities.rm.am.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Optional;


@RunWith(SpringRunner.class)
@WebFluxTest(ResourceController.class)
public class ResourceControllerTest
{
    @MockBean
    ResourceService resourceService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getResource() throws Exception
    {
        String code = "up-1";
        Resource upResource = Resource.builder().id(1L).code(code).resourceType("UP").build();
        //Mockito.when(resourceService.getResourceByCode(Mockito.any())).thenReturn(Mono.just(upResource)); No reactive capability for jdbc entities

        Mockito.when(resourceService.getResourceByCode(code)).thenReturn(Optional.of(upResource));

        // Finds and return the resource
        this.webTestClient.get()
                .uri(String.format("/resource/%s",code))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Resource.class)
                .isEqualTo(upResource);


        // Doesn't find the resource and return an empty one
        this.webTestClient.get()
                .uri(String.format("/resource/%s","up-2"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Resource.class)
                .isEqualTo(Resource.builder().build());
    }
}
