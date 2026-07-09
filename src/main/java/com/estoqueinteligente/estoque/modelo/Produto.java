package com.estoqueinteligente.estoque.modelo;

import jakarta.persistence.*;

// ==============================================================================
// CLASSE MODELO: PRODUTO
// Esta classe representa a entidade "Produto" no sistema. 
// Através do padrão ORM (Object-Relational Mapping), o JPA/Hibernate 
// traduzirá esta classe automaticamente para uma tabela no banco de dados MySQL.
// ==============================================================================

@Entity // Indica ao JPA que esta classe é uma entidade mapeada no banco de dados
@Table(name = "produtos") // Define o nome exato da tabela que será criada no MySQL
public class Produto {

    // --- CHAVE PRIMÁRIA ---
    @Id // Define o atributo 'id' como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados vai gerar o ID automaticamente (Auto-Incremento)
    private Long id;

    // --- ATRIBUTOS BÁSICOS ---
    private String nome; 
    private int quantidade; 
    private double preco;

    // --- RELACIONAMENTOS (CHAVES ESTRANGEIRAS) ---
    
    // Relacionamento de Muitos para Um: Muitos produtos podem pertencer a uma mesma categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id") // Cria a coluna 'categoria_id' na tabela produtos
    private Categoria categoria;

    // Relacionamento de Muitos para Um: Muitos produtos podem ser cadastrados por um mesmo usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id") // Cria a coluna 'usuario_id' indicando quem cadastrou o item
    private Usuario usuarioResponsavel;

    // --- CONTROLE DE DELEÇÃO LÓGICA (SOFT DELETE) ---
    // Em vez de usar DELETE no SQL (o que destruiria a auditoria das movimentações),
    // mudamos este estado para 'false' para ocultar o produto do sistema.
    private boolean ativo = true;

    // ==============================================================================
    // GETTERS E SETTERS
    // Métodos de acesso para permitir que o Spring Boot e o Front-end leiam e 
    // alterem os dados privados desta classe de forma segura.
    // ==============================================================================

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
    
    public int getQuantidade() { 
        return quantidade; 
    }
    
    public void setQuantidade(int quantidade) { 
        this.quantidade = quantidade; 
    }
    
    public double getPreco() { 
        return preco; 
    }
    
    public void setPreco(double preco) { 
        this.preco = preco; 
    }
    
    public Categoria getCategoria() { 
        return categoria; 
    }
    
    public void setCategoria(Categoria categoria) { 
        this.categoria = categoria; 
    }
    
    public Usuario getUsuarioResponsavel() { 
        return usuarioResponsavel; 
    }
    
    public void setUsuarioResponsavel(Usuario usuarioResponsavel) { 
        this.usuarioResponsavel = usuarioResponsavel; 
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}