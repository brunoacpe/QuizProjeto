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
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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

    private String caminhoFilmes = "C:\\Users\\Eu\\Documents\\GitHub\\Projeto\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\filmes.csv";
    private String caminhoFilmesEscolhidos = "C:\\Users\\Eu\\Documents\\GitHub\\Projeto\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\filmesEscolhidos.csv";
    private Path path;
    private Path pathEscolhidos;

    @PostConstruct
    public void init() {
        this.path = Paths.get(caminhoFilmes);
        this.pathEscolhidos = Paths.get(caminhoFilmesEscolhidos);
    }


    public List<Movie> listarTodos() {
        Random random = new Random();
        int n1, n2;
        List<Movie> moviesList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            moviesList = br.lines().map(this::converterLinhaEmMovie).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Movie> listaDoisFilmes = new ArrayList<>();

        n1 = random.nextInt(moviesList.size());
        n2 = random.nextInt(moviesList.size());
        while (n1 == n2) {
            n2 = random.nextInt(moviesList.size());
        }

        listaDoisFilmes.add(moviesList.get(n1));
        listaDoisFilmes.add(moviesList.get(n2));

        return listaDoisFilmes;

    }


    public Movie converterLinhaEmMovie(String linha) {
        StringTokenizer st = new StringTokenizer(linha, ";");
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
        return String.format("%s;%s;%s;%s;%s;%s\n", movie.getTitle(), movie.getYear(), movie.getImdbId(), movie.getRating(), movie.getVotes(), movie.getScore());
    }

}

