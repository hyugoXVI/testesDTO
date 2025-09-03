package usuarios.testeConhecimentos.exceptions;

public class IdNaoEncontradoException extends RuntimeException{
    private String mensagem;

    public IdNaoEncontradoException(String message, String mensagem) {
        super(message);
        this.mensagem = mensagem;
    }
}
