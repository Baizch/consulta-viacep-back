package com.example.viacep.service;

import com.example.viacep.model.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepService {
    private final WebClient webClient;

    public ViaCepService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://viacep.com.br").build();
    }

    public Mono<Address> getAddressByCep(String cep) {
        return this.webClient.get()
                .uri("/ws/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(Address.class)
                .map(address -> {
                    address.setLabel(address.getLogradouro() + ", " + address.getBairro() + ", " + address.getLocalidade() + ", " + address.getUf());
                    return address;
                });
    }
}
