package com.estoqueinteligente.estoque.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoqueinteligente.estoque.modelo.Produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // Busca apenas produtos ativos
    List<Produto> findByAtivoTrue();
    
    // Busca produtos ativos pelo nome (para a barra de pesquisa)
    List<Produto> findByAtivoTrueAndNomeContainingIgnoreCase(String nome);
}

