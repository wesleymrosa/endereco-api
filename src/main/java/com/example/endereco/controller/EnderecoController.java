package com.example.endereco.controller;

import com.example.endereco.dto.EnderecoDTO;
import com.example.endereco.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private static final Logger log = LoggerFactory.getLogger(EnderecoController.class);
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Operation(summary = "Busca um endereço pelo CEP", description = "Consulta um endereço no banco de dados ou via API externa caso não esteja salvo.")
    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> buscarPorCep(
            @Parameter(description = "CEP do endereço desejado", example = "01001-000")
            @PathVariable String cep) {
        EnderecoDTO enderecoDTO = enderecoService.buscarPorCep(cep);

        if (enderecoDTO.isPersistido()) {
            String msg = "O CEP solicitado: " + cep + " já está salvo no nosso banco de dados.";
            log.info(msg);
            enderecoDTO.getLogs().add(msg);
        } else {
            String msg = "O CEP solicitado: " + cep + " foi salvo no banco de dados após busca em API externa.";
            log.info(msg);
            enderecoDTO.getLogs().add(msg);
        }

        return ResponseEntity.ok(enderecoDTO);
    }

    @Operation(summary = "Busca endereços por logradouro", description = "Retorna uma lista de endereços contendo o logradouro informado.")
    @GetMapping("/logradouro")
    public ResponseEntity<List<EnderecoDTO>> buscarPorLogradouro(
            @Parameter(description = "Nome do logradouro", example = "Avenida Paulista")
            @RequestParam String logradouro) {
        return ResponseEntity.ok(enderecoService.buscarPorLogradouro(logradouro));
    }

    @Operation(summary = "Busca endereços por bairro")
    @GetMapping("/bairro")
    public ResponseEntity<List<EnderecoDTO>> buscarPorBairro(
            @Parameter(description = "Nome do bairro", example = "Centro")
            @RequestParam String bairro) {
        return ResponseEntity.ok(enderecoService.buscarPorBairro(bairro));
    }

    @Operation(summary = "Busca endereços por localidade")
    @GetMapping("/localidade")
    public ResponseEntity<List<EnderecoDTO>> buscarPorLocalidade(
            @Parameter(description = "Nome da localidade", example = "São Paulo")
            @RequestParam String localidade) {
        return ResponseEntity.ok(enderecoService.buscarPorLocalidade(localidade));
    }

    @Operation(summary = "Busca endereços por estado (UF)")
    @GetMapping("/uf")
    public ResponseEntity<List<EnderecoDTO>> buscarPorUf(
            @Parameter(description = "Código da UF", example = "SP")
            @RequestParam String uf) {
        return ResponseEntity.ok(enderecoService.buscarPorUf(uf));
    }

    @Operation(summary = "Lista todos os endereços salvos")
    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarTudo() {
        return ResponseEntity.ok(enderecoService.listarTudo());
    }
}
