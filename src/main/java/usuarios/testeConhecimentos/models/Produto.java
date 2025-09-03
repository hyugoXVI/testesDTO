package usuarios.testeConhecimentos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_PRODUTOS")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private double preco;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Produto(){}

    public Produto(long id, String nome, double preco, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
