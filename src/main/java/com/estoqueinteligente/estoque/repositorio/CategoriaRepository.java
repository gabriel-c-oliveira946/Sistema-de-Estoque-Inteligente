package com.estoqueinteligente.estoque.repositorio;

// Importação da biblioteca do Spring Data que contém a lógica de acesso a dados
import org.springframework.data.jpa.repository.JpaRepository;

import com.estoqueinteligente.estoque.modelo.Categoria;

// Interface que atua como a camada de acesso a dados (DAO) para a tabela de categorias.
// O JpaRepository exige dois parâmetros genéricos: a Entidade (Categoria) e o tipo da Chave Primária (Long).
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    // O corpo desta interface está intencionalmente vazio.
    // Ao herdar do JpaRepository, o Spring Boot injeta automaticamente todos os métodos 
    // transacionais básicos de CRUD (save, findAll, findById, delete) em tempo de execução,
    // eliminando a necessidade de escrevermos comandos SQL manualmente.
    
}