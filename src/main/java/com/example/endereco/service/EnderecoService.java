package com.example.endereco.service;

import com.example.endereco.client.ViaCepClient;
import com.example.endereco.dto.EnderecoDTO;
import com.example.endereco.mapper.EnderecoMapper;
import com.example.endereco.model.Endereco;
import com.example.endereco.repository.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    private static final Logger log = LoggerFactory.getLogger(EnderecoService.class);
    private final EnderecoRepository enderecoRepository;
    private final ViaCepClient viaCepClient;

    public EnderecoService(EnderecoRepository enderecoRepository, ViaCepClient viaCepClient) {
        this.enderecoRepository = enderecoRepository;
        this.viaCepClient = viaCepClient;
    }

    @Transactional
    public EnderecoDTO buscarPorCep(String cep) {
        List<String> logsInternos = new ArrayList<>();

        return enderecoRepository.findByCep(cep)
                .map(endereco -> {
                    String msg = "O CEP " + cep + " informado já consta na nossa base de dados.";
                    log.info(msg);
                    logsInternos.add(msg);

                    EnderecoDTO dto = EnderecoMapper.toDTO(endereco);
                    dto.setPersistido(true);
                    dto.setLogs(logsInternos);
                    return dto;
                })
                .orElseGet(() -> {
                    String msg = "O CEP informado  " + cep + " não foi localizado nos nossos registros. Buscando via API externa e salvando no banco de dados.";
                    log.info(msg);
                    logsInternos.add(msg);

                    EnderecoDTO dto = viaCepClient.buscarCep(cep);
                    dto.setPersistido(false);
                    dto.setLogs(logsInternos);

                    Endereco endereco = EnderecoMapper.toEntity(dto);
                    enderecoRepository.save(endereco);

                    return dto;
                });
    }
}