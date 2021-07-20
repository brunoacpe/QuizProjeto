package br.com.letscode.Services;

import br.com.letscode.DAO.MovieDAO;
import br.com.letscode.Model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MovieServices {

    private MovieDAO movieDAO;

    @Autowired
    public MovieServices(MovieDAO movieDAO){
        this.movieDAO = movieDAO;
    }

    public List<Movie> listarTodos() {
        return movieDAO.listarDoisFilmes();
    }
    //2 Filmes aleatorios
    public List<Movie> filmesAleatorios(){
        return movieDAO.listarDoisFilmes();
    }


    public List<Movie> filmesAleatoriosQuiz() throws IOException {
        return movieDAO.lerArquivoQuiz();
    }
}