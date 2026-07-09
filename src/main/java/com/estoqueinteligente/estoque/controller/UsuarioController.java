package com.estoqueinteligente.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueinteligente.estoque.modelo.Usuario;
import com.estoqueinteligente.estoque.repositorio.UsuarioRepository;

@RestController // Modernizado para API, igual ao Login!
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/api/cadastrar-usuario")
    public ResponseEntity<String> cadastrar(@RequestBody Usuario usuario) {
        
        // 1. Verifica se já existe alguém com esse login
        Usuario usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());
        
        if (usuarioExistente != null) {
            // TRAVA: Se existe, manda uma bomba de erro 409 (Conflito) pro JavaScript
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Esse login já está em uso.");
        }
        
        // 2. Se está livre, salva no banco
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(usuario.getLogin());
        novoUsuario.setSenha(usuario.getSenha());
        
        usuarioRepository.save(novoUsuario);
        
        return ResponseEntity.ok("Sucesso");
    }
}