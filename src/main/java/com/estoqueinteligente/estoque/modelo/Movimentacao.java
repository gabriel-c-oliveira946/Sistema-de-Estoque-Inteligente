package com.estoqueinteligente.estoque.modelo;

// Importações da biblioteca Jakarta Persistence (JPA) para mapeamento de banco de dados
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Importação nativa do Java para lidar com datas e horas precisas
import java.time.LocalDateTime;

// Entidade que representa o histórico imutável (auditoria) do sistema.
// Cada registro salvo nesta tabela nunca deve ser alterado ou apagado fisicamente.
@Entity 
@Table(name = "movimentacoes") 
public class Movimentacao {

    // Chave primária gerada automaticamente pelo banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define a natureza da operação. No nosso escopo, recebe valores como "ENTRADA", "SAÍDA" ou "EXCLUSÃO".
    private String tipo; 
    
    // O volume de itens movimentados nesta transação específica
    private int quantidade;
    
    // Captura o momento exato em que o objeto é instanciado na memória do servidor.
    // Isso garante que a data da auditoria seja gerada pelo Back-end, evitando fraudes no Front-end.
    private LocalDateTime dataHora = LocalDateTime.now();

    // Mapeamento de chave estrangeira: Muitas movimentações estão ligadas a um único Produto.
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    // Mapeamento de chave estrangeira: Muitas movimentações são realizadas por um único Usuário.
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioResponsavel;

    // Construtor padrão exigido pelo framework Hibernate para instanciação via Reflection
    public Movimentacao() {
    }

    // Métodos de acesso (Getters e Setters) para encapsulamento e proteção dos dados
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getTipo() { 
        return tipo; 
    }
    
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    }

    public int getQuantidade() { 
        return quantidade; 
    }
    
    public void setQuantidade(int quantidade) { 
        this.quantidade = quantidade; 
    }

    public LocalDateTime getDataHora() { 
        return dataHora; 
    }
    
    public void setDataHora(LocalDateTime dataHora) { 
        this.dataHora = dataHora; 
    }

    public Produto getProduto() { 
        return produto; 
    }
    
    public void setProduto(Produto produto) { 
        this.produto = produto; 
    }

    public Usuario getUsuarioResponsavel() { 
        return usuarioResponsavel; 
    }
    
    public void setUsuarioResponsavel(Usuario usuarioResponsavel) { 
        this.usuarioResponsavel = usuarioResponsavel; 
    }
}