package br.com.letscode.Exceptions;

public class ImdbIdInvalido extends Throwable {
    private String message;

    public ImdbIdInvalido(){
        this.message = "ImdbId do filme inválido.";
    }
}