package com.example.endereco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EnderecoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnderecoApiApplication.class, args);
    }

}
