package br.com.letscode.DAO;

import br.com.letscode.Model.Movie;
import br.com.letscode.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.security.DenyAll;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
@Component
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDAO {

    private String caminho = "C:\\Users\\Eu\\Documents\\GitHub\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\filmes.csv";
    private Path path;

    @PostConstruct
    public void init(){
        this.path = Paths.get(caminho);
    }


    public List<Movie> listarTodos(){
        List<Movie> moviesList = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(path)){
            moviesList = br.lines().map(this::converterLinhaEmMovie).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moviesList;

    }


    public List<Movie> listMovies(){
        List<Movie> listMovies = listarTodos();
        //TODO - AINDA FALTA SABER COMO PEGAR APENAS 2 ITENS DESSA LISTA DE FORMA RANDOM;
        return null;
    }
    public Movie converterLinhaEmMovie(String linha){
        StringTokenizer st = new StringTokenizer(linha,";");
        Movie movie = new Movie();
        movie.setTitle(st.nextToken());
        movie.setYear(Integer.valueOf(st.nextToken()));
        movie.setRating(Double.valueOf(st.nextToken()));
        movie.setScore(Double.valueOf(st.nextToken()));
        return movie;
    }

    public String formatar(Movie movie) {
        //Nome - Ano - Rating - Score
        return String.format("%s;%s;%s;%s;\n",movie.getTitle(),movie.getYear(),movie.getRating(),movie.getScore());
    }

}