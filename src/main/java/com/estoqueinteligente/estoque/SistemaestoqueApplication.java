package com.estoqueinteligente.estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SistemaestoqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaestoqueApplication.class, args);
        
        System.out.println("=========================================");
        System.out.println(" SISTEMA DE ESTOQUE INICIALIZADO COM SUCESSO");
        System.out.println("=========================================");
    }
}