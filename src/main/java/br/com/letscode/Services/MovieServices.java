package br.com.letscode.Services;

import br.com.letscode.DAO.MovieDAO;
import br.com.letscode.Model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieServices {

    private MovieDAO movieDAO;

    @Autowired
    public MovieServices(MovieDAO movieDAO){
        this.movieDAO = movieDAO;
    }
    //TODO -- Método do Guilherme  ->>>
    public List<Movie> listarTodos() {
        return movieDAO.listarTodos();
    }



}