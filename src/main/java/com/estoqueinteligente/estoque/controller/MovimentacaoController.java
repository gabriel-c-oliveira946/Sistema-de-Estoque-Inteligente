package com.estoqueinteligente.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueinteligente.estoque.modelo.Movimentacao;
import com.estoqueinteligente.estoque.modelo.Produto;
import com.estoqueinteligente.estoque.modelo.Usuario;
import com.estoqueinteligente.estoque.repositorio.MovimentacaoRepository;
import com.estoqueinteligente.estoque.repositorio.ProdutoRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository; // Precisamos dele para atualizar o estoque do produto

    @GetMapping
    public ResponseEntity<List<Movimentacao>> listarTodas(HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(movimentacaoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> registrarMovimentacao(@RequestBody Movimentacao movimentacao, HttpSession session) {
        // 1. Trava de Segurança: Quem está fazendo a movimentação?
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        // 2. Busca o Produto original no banco para mexer na quantidade dele
        Produto produto = produtoRepository.findById(movimentacao.getProduto().getId()).orElse(null);
        if (produto == null) {
            return ResponseEntity.badRequest().body("Produto não encontrado.");
        }

        // 3. Lógica Matemática de Estoque
        if (movimentacao.getTipo().equals("ENTRADA")) {
            produto.setQuantidade(produto.getQuantidade() + movimentacao.getQuantidade());
            
        } else if (movimentacao.getTipo().equals("SAIDA")) {
            // Trava contra estoque negativo
            if (produto.getQuantidade() < movimentacao.getQuantidade()) {
                return ResponseEntity.badRequest().body("Erro: Estoque insuficiente para esta saída!");
            }
            produto.setQuantidade(produto.getQuantidade() - movimentacao.getQuantidade());
        }

        // 4. Amarrações finais
        movimentacao.setUsuarioResponsavel(usuarioLogado);
        movimentacao.setProduto(produto);

        // 5. Salva a nova quantidade no produto e registra a movimentação
        produtoRepository.save(produto);
        Movimentacao movimentacaoSalva = movimentacaoRepository.save(movimentacao);

        return ResponseEntity.ok(movimentacaoSalva);
    }
}