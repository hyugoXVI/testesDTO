package usuarios.testeConhecimentos.validator;

import usuarios.testeConhecimentos.exceptions.UsuarioCamposException;
import usuarios.testeConhecimentos.exceptions.UsuarioSenhaException;

public class UsuarioValidator {
    public static void validarNome(String nome){

        if (nome == null || nome.trim().isEmpty()){
            throw new UsuarioCamposException("Nome não pode ser nulo ou vazio.");
        }
    }

    public static void validarEmail(String email){

        if (email == null || email.trim().isEmpty()){
            throw new UsuarioCamposException("E-mail não pode ser nulo ou vazio.");
        }
    }

    public static void validarSenha(String senha){

        if (senha == null || senha.trim().isEmpty()){
            throw new UsuarioCamposException("Senha não pode ser nula ou vazia.");
        }
        if (senha.length() < 8){
            throw new UsuarioSenhaException("Senha deve ter, no mínimo, 8 caracteres!");
        }
    }
}
