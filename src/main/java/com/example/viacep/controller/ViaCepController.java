package com.example.viacep.controller;

import com.example.viacep.model.Address;
import com.example.viacep.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class ViaCepController {
    private final ViaCepService viaCepService;
    private static final Logger logger = LoggerFactory.getLogger(ViaCepController.class);

    @Autowired
    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/cep/{cep}")
    public Mono<ResponseEntity<Address>> getAddressByCep(@PathVariable String cep) {
        logger.info("Received request for CEP: {}", cep);
        return viaCepService.getAddressByCep(cep)
                .map(address -> {
                    logger.info("Returning address for CEP: {}", cep);
                    return ResponseEntity.ok(address);
                })
                .doOnError(error -> logger.error("Error processing request for CEP {}: {}", cep, error.getMessage()))
                .onErrorResume(error -> Mono.just(ResponseEntity.status(500).build()));
    }
}
