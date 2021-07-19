package br.com.letscode.Exceptions;

public class UsuarioNaoEncontrado extends Throwable{
    private String message;

    public UsuarioNaoEncontrado(){
        this.message = "Usuário não encontrado.";
    }
}
