package br.com.letscode.Exceptions;

public class VidaInsuficiente extends Throwable{
    private String message;

    public VidaInsuficiente(){
        this.message = "Usuário não possui vida suficiente.";
    }
}
