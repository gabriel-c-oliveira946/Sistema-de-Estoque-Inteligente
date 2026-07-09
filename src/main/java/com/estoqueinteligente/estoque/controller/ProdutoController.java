package com.estoqueinteligente.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueinteligente.estoque.modelo.Movimentacao;
import com.estoqueinteligente.estoque.modelo.Produto;
import com.estoqueinteligente.estoque.modelo.Usuario;
import com.estoqueinteligente.estoque.repositorio.MovimentacaoRepository;
import com.estoqueinteligente.estoque.repositorio.ProdutoRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

// Define que esta classe é um controlador REST, respondendo requisições com dados em JSON
@RestController
// Define o caminho base na URL para acessar as operações de produtos
@RequestMapping("/api/produtos")
public class ProdutoController {

    // Injeção de dependência para acesso ao banco de dados da tabela de produtos
    @Autowired
    private ProdutoRepository produtoRepository;

    // Injeção de dependência para registrar automaticamente as movimentações (auditoria)
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    // Endpoint para buscar a lista de produtos. Pode receber um parâmetro opcional de busca por nome.
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos(@RequestParam(required = false) String nome, HttpSession session) {
        // Trava de segurança: impede o acesso se o usuário não estiver autenticado na sessão
        if (session.getAttribute("usuarioLogado") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        // Retorna a lista filtrada pelo nome (se fornecido) garantindo que apenas produtos com ativo=true sejam exibidos
        if (nome != null && !nome.isEmpty()) {
            return ResponseEntity.ok(produtoRepository.findByAtivoTrueAndNomeContainingIgnoreCase(nome));
        }
        return ResponseEntity.ok(produtoRepository.findByAtivoTrue());
    }

    // Endpoint para criar um novo produto no estoque
    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        produto.setUsuarioResponsavel(usuarioLogado);
        
        // 1. Salva o produto primeiro para o banco gerar o ID dele
        Produto salvo = produtoRepository.save(produto);

        // 2. NOVO: Registra o nascimento do produto na auditoria, conforme exigência da banca
        Movimentacao historico = new Movimentacao();
        historico.setProduto(salvo);
        historico.setUsuarioResponsavel(usuarioLogado);
        historico.setTipo("CADASTRO"); // Um tipo exclusivo para a criação inicial
        historico.setQuantidade(salvo.getQuantidade()); // Registra com qual saldo ele nasceu
        movimentacaoRepository.save(historico);

        return ResponseEntity.ok(salvo);
    }

    // Endpoint para atualizar os dados cadastrais de um produto existente
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Busca o produto no banco e, caso exista, aplica as alterações
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setCategoria(produtoAtualizado.getCategoria());
            
            Produto salvo = produtoRepository.save(produto);

            // Gera um registro automático na trilha de auditoria especificando que houve uma edição de cadastro
            Movimentacao historico = new Movimentacao();
            historico.setProduto(salvo);
            historico.setUsuarioResponsavel(usuarioLogado);
            historico.setTipo("ALTERAÇÃO");
            historico.setQuantidade(salvo.getQuantidade());
            movimentacaoRepository.save(historico);
            
            return ResponseEntity.ok(salvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para realizar a deleção lógica de um produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return produtoRepository.findById(id).map(produto -> {
            // Aplica o Soft Delete mudando o estado do produto para ocultá-lo da interface principal
            produto.setAtivo(false);
            produtoRepository.save(produto);

            // Registra a exclusão na auditoria para manter o rastreio da operação no banco de dados
            Movimentacao historico = new Movimentacao();
            historico.setProduto(produto);
            historico.setUsuarioResponsavel(usuarioLogado);
            historico.setTipo("EXCLUSÃO");
            historico.setQuantidade(produto.getQuantidade()); 
            movimentacaoRepository.save(historico);

            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}