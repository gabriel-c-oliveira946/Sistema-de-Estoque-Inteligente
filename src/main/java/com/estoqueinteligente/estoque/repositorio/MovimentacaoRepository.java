package com.estoqueinteligente.estoque.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoqueinteligente.estoque.modelo.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}