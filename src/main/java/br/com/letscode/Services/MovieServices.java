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

    @Autowired
    public MovieServices(MovieDAO movieDAO){
        this.movieDAO = movieDAO;
    }

    public List<Movie> listarTodos() {
        return movieDAO.listarTodos();
    }
    //2 Filmes aleatorios
    public List<Movie> filmesAleatorios(){

        return movieDAO.listarTodos();
    }

}