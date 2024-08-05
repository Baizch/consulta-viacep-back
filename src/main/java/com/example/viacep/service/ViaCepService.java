package com.example.viacep.service;

import com.example.viacep.model.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ViaCepService {
    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(ViaCepService.class);

    public ViaCepService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://viacep.com.br").build();
    }

    public Mono<Address> getAddressByCep(String cep) {
        logger.info("Fetching address for CEP: {}", cep);
        return this.webClient.get()
                .uri("/ws/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(Address.class)
                .map(address -> {
                    address.setLabel(address.getLogradouro() + ", " + address.getBairro() + ", " + address.getLocalidade() + ", " + address.getUf());
                    logger.info("Address found: {}", address);
                    return address;
                })
                .doOnError(error -> logger.error("Error fetching address for CEP {}: {}", cep, error.getMessage()));
    }
}
