package com.estoqueinteligente.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueinteligente.estoque.modelo.Usuario;
import com.estoqueinteligente.estoque.repositorio.UsuarioRepository;

import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

// Classe responsável por gerenciar o fluxo de autenticação, controle de sessão e identidade.
@RestController 
public class LoginController {

    // Injeção da interface que permite a comunicação com a tabela de usuários no banco de dados.
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Recebe as credenciais (login e senha) via JSON e valida o acesso no sistema.
    @PostMapping("/api/fazer-login")
    public ResponseEntity<String> realizarLogin(@RequestBody Usuario usuario, HttpSession session) {
        
        // Consulta no banco de dados se existe uma correspondência exata para as credenciais fornecidas.
        Usuario usuarioLogado = usuarioRepository.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());

        if (usuarioLogado != null) {
            // Autenticação bem-sucedida: vincula o objeto do usuário à sessão segura do servidor (memória RAM).
            session.setAttribute("usuarioLogado", usuarioLogado);
            return ResponseEntity.ok("Sucesso"); 
        } else {
            // Autenticação falhou: retorna o código HTTP 401 (Unauthorized) para o Front-end bloquear o acesso.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha incorretos."); 
        }
    }

    // Rota consumida pelo Front-end para recuperar a identidade do usuário atualmente autenticado.
    @GetMapping("/api/usuario-logado")
    public ResponseEntity<String> getUsuarioLogado(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        // Valida se a sessão ainda existe ou se já expirou.
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        // Retorna apenas o identificador (login) por segurança, sem expor a senha no tráfego da rede.
        return ResponseEntity.ok(usuario.getLogin());
    }

    // Rota responsável por encerrar a sessão ativa do usuário no servidor.
    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        // Invalida e destrói todos os dados (crachá virtual) atrelados à sessão atual de forma segura.
        session.invalidate(); 
        return ResponseEntity.ok().build();
    }
}