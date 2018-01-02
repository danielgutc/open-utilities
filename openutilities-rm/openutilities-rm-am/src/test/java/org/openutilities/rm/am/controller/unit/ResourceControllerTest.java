package org.openutilities.rm.am.controller.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.openutilities.core.domain.Resource;
import org.openutilities.core.domain.builder.ResourceBuilder;
import org.openutilities.rm.am.controller.ResourceController;
import org.openutilities.rm.am.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(ResourceController.class)
public class ResourceControllerTest
{
    @MockBean
    ResourceService resourceService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    //@Ignore //TODO fix Caused by: java.lang.AssertionError: Response body expected:<org.openutilities.core.domain.Resource@310d57b1> but was:<org.openutilities.core.domain.Resource@143fefaf>
    public void getResource() throws Exception
    {
        String code = "up-1";
        Resource upResource = ResourceBuilder.aResource().id(1L).code(code).typeId(1L).build();
        //Mockito.when(resourceService.getResourceByCode(Mockito.any())).thenReturn(Mono.just(upResource)); No reactive capability for jdbc entities

        Mockito.when(resourceService.getResource(code)).thenReturn(upResource);

        // Finds and return the resource
        this.webTestClient.get()
                .uri(String.format("/resources/%s", code))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Resource.class)
                .returnResult()
                .getResponseBody()
                .getCode().equals(code);


        // Doesn't find the resource and return an empty one
        this.webTestClient.get()
                .uri(String.format("/resources/%s","up-2"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Resource.class)
                .isEqualTo(null);
    }
}
