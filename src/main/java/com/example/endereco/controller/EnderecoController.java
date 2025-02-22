package com.example.endereco.controller;

import com.example.endereco.dto.EnderecoDTO;
import com.example.endereco.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> buscarPorCep(@PathVariable String cep) {
        return ResponseEntity.ok(enderecoService.buscarPorCep(cep));
    }

    @GetMapping("/logradouro")
    public ResponseEntity<List<EnderecoDTO>> buscarPorLogradouro(@RequestParam String logradouro) {
        return ResponseEntity.ok(enderecoService.buscarPorLogradouro(logradouro));
    }

    @GetMapping("/bairro")
    public ResponseEntity<List<EnderecoDTO>> buscarPorBairro(@RequestParam String bairro) {
        return ResponseEntity.ok(enderecoService.buscarPorBairro(bairro));
    }

    @GetMapping("/localidade")
    public ResponseEntity<List<EnderecoDTO>> buscarPorLocalidade(@RequestParam String localidade) {
        return ResponseEntity.ok(enderecoService.buscarPorLocalidade(localidade));
    }

    @GetMapping("/uf")
    public ResponseEntity<List<EnderecoDTO>> buscarPorUf(@RequestParam String uf) {
        return ResponseEntity.ok(enderecoService.buscarPorUf(uf));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarTudo() {
        return ResponseEntity.ok(enderecoService.listarTudo());
    }
}
