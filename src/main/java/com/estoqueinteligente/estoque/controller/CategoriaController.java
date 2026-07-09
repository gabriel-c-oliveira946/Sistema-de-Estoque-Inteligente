package com.estoqueinteligente.estoque.controller;

// Importações do ecossistema Spring Web para criação de rotas e APIs REST
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueinteligente.estoque.modelo.Categoria;
import com.estoqueinteligente.estoque.repositorio.CategoriaRepository;

// Importação nativa do Java para trabalhar com listas de objetos
import java.util.List;

// A anotação RestController avisa ao Spring que esta classe não vai devolver páginas HTML.
// A função dela é exclusivamente receber requisições da internet e devolver dados puros em formato JSON.
@RestController
// Define o endereço base (a URL) para acessar os recursos desta classe.
@RequestMapping("/api/categorias")
public class CategoriaController {

    // A anotação Autowired realiza a Injeção de Dependência.
    // Em vez de criarmos o repositório manualmente com "new CategoriaRepository()", 
    // o próprio Spring Boot gerencia a memória e injeta a ferramenta pronta para usarmos.
    @Autowired
    private CategoriaRepository categoriaRepository;

    // A anotação GetMapping indica que este método será acionado quando o "motoboy" (fetch)
    // fizer uma requisição do tipo GET (buscar dados) para a rota "/api/categorias".
    @GetMapping
    public List<Categoria> listarTodas() {
        // O repositório vai até o banco, faz um SELECT automático, 
        // transforma em uma lista de objetos Java e o RestController converte tudo para JSON.
        return categoriaRepository.findAll();
    }
}