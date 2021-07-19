package br.com.letscode.Services;

import br.com.letscode.DAO.MovieDAO;
import br.com.letscode.Model.Movie;
import br.com.letscode.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MovieServices {

    private MovieDAO movieDAO;
    private List<Movie> listaGame;
    private List<Movie> doisFilmes;

    @Autowired
    public MovieServices(MovieDAO movieDAO){
        this.movieDAO = movieDAO;
    }

    public List<Movie> listarTodos() {
        return movieDAO.listarTodos();
    }

    //2 Filmes aleatorios
    public List<Movie> filmesAleatorios(){
        Random gerador = new Random();
        int n1,n2;
        listaGame = movieDAO.listarTodos();
        int tamanhoList = listaGame.size();
        n1 = gerador.nextInt(tamanhoList);
        n2 = gerador.nextInt(tamanhoList);
        while(n1 == n2){
            n2 = gerador.nextInt(tamanhoList);
        }
        doisFilmes.add(listaGame.get(n1));
        doisFilmes.add(listaGame.get(n2));
        return doisFilmes;
    }

    public List<Movie> getDoisFilmes(){
        return doisFilmes;
    }
}