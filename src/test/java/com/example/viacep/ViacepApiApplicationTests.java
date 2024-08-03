package com.example.viacep;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class ViacepApiApplicationTests {

    private final WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

    @Test
    void testGetAddress() {
        webTestClient.get().uri("/api/cep/17560246")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.cep").isEqualTo("17560246");
    }
}
