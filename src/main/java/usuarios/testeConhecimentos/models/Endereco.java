package usuarios.testeConhecimentos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_ENDERECOS")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String estado;
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    public Endereco(){}

    public Endereco(String estado, String cidade, Usuario usuario) {
        this.estado = estado;
        this.cidade = cidade;
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
