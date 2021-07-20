package br.com.letscode.Exceptions;

public class CadastroDeUsuarioInvalido extends Throwable{
    private String message;

    public  CadastroDeUsuarioInvalido(){
        this.message = "O usuário precisa ter de 4-8 caracteres na senha, e ter de 5-10 caracteres no nome";
    }
}
