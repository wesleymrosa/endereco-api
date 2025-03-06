package com.example.endereco.controller;

import com.example.endereco.dto.EnderecoDTO;
import com.example.endereco.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private static final Logger log = LoggerFactory.getLogger(EnderecoController.class);
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Operation(summary = "Busca um endereço pelo CEP")
    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> buscarPorCep(
            @Parameter(description = "CEP do endereço desejado")
            @PathVariable String cep) {
        EnderecoDTO enderecoDTO = enderecoService.buscarPorCep(cep);

        if (enderecoDTO.isPersistido()) {
            log.info("O CEP " + cep + " já está salvo na nossa base de dados.");
            return ResponseEntity.ok().body(enderecoDTO);
        } else {
            log.info("O CEP " + cep + " foi salvo no banco após busca em API externa.");
            return ResponseEntity.ok().body(enderecoDTO);
        }
    }
}
