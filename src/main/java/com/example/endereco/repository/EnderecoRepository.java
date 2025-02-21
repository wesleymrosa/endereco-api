package com.example.endereco.repository;

import com.example.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByCep(String cep);

    List<Endereco> findByLogradouroContainingIgnoreCase(String logradouro);

    List<Endereco> findByBairroContainingIgnoreCase(String bairro);

    List<Endereco> findByLocalidadeContainingIgnoreCase(String localidade);

    List<Endereco> findByUf(String uf);
}
