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
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
@Component
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDAO {

    private String caminho = "C:\\Users\\Eu\\Documents\\GitHub\\Projeto\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\filmes.csv";
    private Path path;

    @PostConstruct
    public void init(){
        this.path = Paths.get(caminho);
    }


    public List<Movie> listarTodos(){
        Random random = new Random();
        List<Movie> moviesList = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(path)){
            moviesList = br.lines().map(this::converterLinhaEmMovie).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Movie> listaDoisFilmes = new ArrayList<>();
        listaDoisFilmes.add(moviesList.get(random.nextInt(moviesList.size())));
        listaDoisFilmes.add(moviesList.get(random.nextInt(moviesList.size())));

        return listaDoisFilmes;

    }


    public Movie converterLinhaEmMovie(String linha){
        StringTokenizer st = new StringTokenizer(linha,";");
        Movie movie = new Movie();
        movie.setTitle(st.nextToken());
        movie.setYear(st.nextToken());
        movie.setImdbId(st.nextToken());
        movie.setRating(Double.valueOf(st.nextToken()));
        movie.setVotes(Long.valueOf(st.nextToken()));
        movie.setScore(Double.valueOf(st.nextToken()));
        return movie;
    }

    public String formatar(Movie movie) {
        //Nome - Ano - Rating - Score
        return String.format("%s;%s;%s;%s;%s;%s\n",movie.getTitle(),movie.getYear(),movie.getImdbId(),movie.getRating(),movie.getVotes(),movie.getScore());
    }

}
