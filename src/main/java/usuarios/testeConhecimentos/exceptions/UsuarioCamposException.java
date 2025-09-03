package usuarios.testeConhecimentos.exceptions;

public class UsuarioCamposException extends RuntimeException{
    private String mensagem;

    public UsuarioCamposException(String mensagem) {
        this.mensagem = mensagem;
    }
}
