package br.com.letscode.DAO;

import br.com.letscode.Model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    private String caminhoFilmes = "C:\\Users\\Vitoria\\QuizMovieProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\filmes.csv";
    private String caminhoFilmesEscolhidos = "C:\\Users\\Vitoria\\QuizMovieProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\filmesEscolhidos.csv";
    private Path path;
    private Path pathEscolhidos;

    @PostConstruct
    public void init() {
        this.path = Paths.get(caminhoFilmes);
        this.pathEscolhidos = Paths.get(caminhoFilmesEscolhidos);
    }


    public List<Movie> listarDoisFilmes() {
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
        escreverFilmesTemporarios(listaDoisFilmes);
        return listaDoisFilmes;

    }

    public List<Movie> escreverFilmesTemporarios(List<Movie> movieList) {
        try(var bf = Files.newBufferedWriter(pathEscolhidos, StandardOpenOption.APPEND)){
            for (int i =0; i< movieList.size();i++){
                bf.write(formatar(movieList.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieList;
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

    public List<Movie> lerArquivoQuiz() throws IOException {
        List<Movie> moviesList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(pathEscolhidos)) {
            moviesList = br.lines().map(this::converterLinhaEmMovie).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        apagarQuiz();
        return moviesList;
    }

    public void apagarQuiz() throws IOException {
        Files.delete(pathEscolhidos);
        PrintWriter pw = new PrintWriter("C:\\Users\\Vitoria\\QuizMovieProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\filmesEscolhidos.csv");
        pw.close();
    }
}

