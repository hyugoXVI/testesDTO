package usuarios.testeConhecimentos.dtos;

public class UsuarioLoginDTO {
    private final String email;
    private final String senha;

    public UsuarioLoginDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;

    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}


