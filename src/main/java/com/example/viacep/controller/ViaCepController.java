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

@RestController
@RequestMapping("/api")
public class ViaCepController {
    private final ViaCepService viaCepService;

    @Autowired
    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/cep/{cep}")
    public Mono<ResponseEntity<Address>> getAddressByCep(@PathVariable String cep) {
        return viaCepService.getAddressByCep(cep)
                .map(address -> ResponseEntity.ok(address))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
