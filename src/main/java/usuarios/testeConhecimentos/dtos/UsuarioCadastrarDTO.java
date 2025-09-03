package usuarios.testeConhecimentos.dtos;

import java.util.ArrayList;
import java.util.List;

public class UsuarioCadastrarDTO {
    private String nome ;
    private String email;
    private String senha;
    private List<EnderecoDTO> enderecos;

    public UsuarioCadastrarDTO(){
        this.enderecos = new ArrayList<>();
    }
    public UsuarioCadastrarDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }
}
