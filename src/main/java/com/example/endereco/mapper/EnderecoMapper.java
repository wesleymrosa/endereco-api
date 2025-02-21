package com.example.endereco.mapper;

import com.example.endereco.dto.EnderecoDTO;
import com.example.endereco.model.Endereco;

public class EnderecoMapper {
    public static Endereco toEntity(EnderecoDTO dto) {
        return Endereco.builder()
                .cep(dto.getCep())
                .logradouro(dto.getLogradouro())
                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .localidade(dto.getLocalidade())
                .uf(dto.getUf())
                .build();
    }

    public static EnderecoDTO toDTO(Endereco entity) {
        return EnderecoDTO.builder()
                .cep(entity.getCep())
                .logradouro(entity.getLogradouro())
                .complemento(entity.getComplemento())
                .bairro(entity.getBairro())
                .localidade(entity.getLocalidade())
                .uf(entity.getUf())
                .build();
    }
}
