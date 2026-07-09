package com.estoqueinteligente.estoque.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.estoqueinteligente.estoque.modelo.Categoria;
import com.estoqueinteligente.estoque.repositorio.CategoriaRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se a tabela está vazia. Se estiver, popula automaticamente!
        if (categoriaRepository.count() == 0) {
            List<String> categoriasIniciais = Arrays.asList(
                "Eletrônicos", "Vestuário Masculino", "Suplementos Esportivos",
                "Componentes de Hardware", "Jogos Digitais", "Artigos Esportivos",
                "Informática e Redes", "Livros e Apostilas", "Alimentos e Bebidas",
                "Ferramentas", "Limpeza", "Papelaria"
            );

            for (String nome : categoriasIniciais) {
                Categoria categoria = new Categoria();
                categoria.setNome(nome);
                categoriaRepository.save(categoria);
            }
            System.out.println(">> [DATABASE] As 12 categorias iniciais foram carregadas automaticamente!");
        }
    }
}