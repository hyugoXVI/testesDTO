package usuarios.testeConhecimentos.exceptions;

public class UsuarioSenhaException extends RuntimeException{
    private String mensagem;

    public UsuarioSenhaException(String mensagem) {
        this.mensagem = mensagem;
    }
}
