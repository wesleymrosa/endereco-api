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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private static final Logger log = LoggerFactory.getLogger(EnderecoService.class);
    private final EnderecoRepository enderecoRepository;
    private final ViaCepClient viaCepClient;

    public EnderecoService(EnderecoRepository enderecoRepository, ViaCepClient viaCepClient) {
        this.enderecoRepository = enderecoRepository;
        this.viaCepClient = viaCepClient;
    }

//    @Transactional
//    public EnderecoDTO buscarPorCep(String cep) {
//        return enderecoRepository.findByCep(cep)
//                .map(EnderecoMapper::toDTO)
//                .orElseGet(() -> {
//                    EnderecoDTO dto = viaCepClient.buscarCep(cep);
//                    Endereco endereco = EnderecoMapper.toEntity(dto);
//                    enderecoRepository.save(endereco);
//                    return dto;
//                });
//    }

    @Transactional
    public EnderecoDTO buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep)
                .map(endereco -> {
                    EnderecoDTO dto = EnderecoMapper.toDTO(endereco);
                    dto.setPersistido(true); // Indica que já existia no banco
                    log.info("Endereço encontrado no banco de dados para o CEP informado, portanto não será persistido novamente: {}" + cep);
                    return dto;
                })
                .orElseGet(() -> {
                    log.info("O CEP não foi encontrado na nossa base de dados. Sistema buscando via API externa o CEP para ser persistido no banco de dados: {}" + cep);
                    EnderecoDTO dto = viaCepClient.buscarCep(cep);
                    dto.setPersistido(false); // Indica que foi buscado externamente
                    Endereco endereco = EnderecoMapper.toEntity(dto);
                    enderecoRepository.save(endereco);
                    return dto;
                });
    }


    public List<EnderecoDTO> buscarPorLogradouro(String logradouro) {
        return enderecoRepository.findByLogradouroContainingIgnoreCase(logradouro)
                .stream().map(EnderecoMapper::toDTO).collect(Collectors.toList());
    }

    public List<EnderecoDTO> buscarPorBairro(String bairro) {
        return enderecoRepository.findByBairroContainingIgnoreCase(bairro)
                .stream().map(EnderecoMapper::toDTO).collect(Collectors.toList());
    }

    public List<EnderecoDTO> buscarPorLocalidade(String localidade) {
        return enderecoRepository.findByLocalidadeContainingIgnoreCase(localidade)
                .stream().map(EnderecoMapper::toDTO).collect(Collectors.toList());
    }

    public List<EnderecoDTO> buscarPorUf(String uf) {
        return enderecoRepository.findByUf(uf)
                .stream().map(EnderecoMapper::toDTO).collect(Collectors.toList());
    }

    public List<EnderecoDTO> listarTudo() {
        return enderecoRepository.findAll()
                .stream().map(EnderecoMapper::toDTO).collect(Collectors.toList());
    }
}
