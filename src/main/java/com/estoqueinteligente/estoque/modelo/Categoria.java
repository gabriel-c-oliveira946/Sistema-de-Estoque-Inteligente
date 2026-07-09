package com.estoqueinteligente.estoque.modelo;

// Importações oficiais da biblioteca Jakarta Persistence (JPA).
// São as ferramentas que permitem mapear esta classe Java para o banco de dados.
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Entidade de domínio que representa as categorias dos produtos no estoque.
// Funciona como uma tabela auxiliar para organizar o inventário e evitar dados duplicados.
@Entity // Sinaliza que esta classe será lida e gerenciada automaticamente pelo Hibernate
@Table(name = "categorias") // Amarra esta classe especificamente à tabela "categorias" no MySQL
public class Categoria {

    // Definição da Chave Primária da tabela
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Delega a criação do ID para o Auto-Incremento do banco
    private Long id;

    // Atributo básico que armazenará o texto do nome da categoria
    private String nome;

    // Construtor vazio obrigatório.
    // O Hibernate necessita deste construtor sem parâmetros para recriar o objeto 
    // na memória (via Reflection) antes de preencher com os dados vindos do banco.
    public Categoria() {
    }

    // Métodos encapsulados (Getters e Setters)
    // Permitem a leitura e alteração segura dos dados privados da classe
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNome() { 
        return nome; 
    }
    
    public void setNome(String nome) { 
        this.nome = nome; 
    }
}