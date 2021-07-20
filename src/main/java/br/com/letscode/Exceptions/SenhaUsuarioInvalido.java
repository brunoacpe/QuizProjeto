package br.com.letscode.Exceptions;

public class SenhaUsuarioInvalido extends Throwable{
    private String message;

    public SenhaUsuarioInvalido(){
        this.message = "Senha ou nome de usuário inválido.";
    }
}
