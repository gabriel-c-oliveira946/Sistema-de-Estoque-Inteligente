package com.estoqueinteligente.estoque.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoqueinteligente.estoque.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Essa única linha substitui todo aquele código de SELECT com WHERE login e senha que você fez no NetBeans!
    Usuario findByLoginAndSenha(String login, String senha);
    Usuario findByLogin(String login);
}