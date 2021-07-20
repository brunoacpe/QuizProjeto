package br.com.letscode.Controllers;


import br.com.letscode.DAO.MovieDAO;
import br.com.letscode.DAO.UsuarioDAO;
import br.com.letscode.Exceptions.UsuarioNaoEncontrado;
import br.com.letscode.Exceptions.VidaInsuficiente;
import br.com.letscode.Model.Movie;
import br.com.letscode.Model.Usuario;
import br.com.letscode.Services.JogoServices;
import br.com.letscode.Services.MovieServices;
import br.com.letscode.Services.QuizzServices;
import br.com.letscode.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequestMapping("/quiz")
@RestController
public class QuizController {

    private UsuarioServices usuarioServices;
    private MovieServices movieServices;
    private QuizzServices quizzServices;
    private JogoServices jogoServices;

    @Autowired
    public QuizController(UsuarioServices usuarioServices, MovieServices movieServices,QuizzServices quizzServices,JogoServices jogoServices){
        this.usuarioServices = usuarioServices;
        this.movieServices = movieServices;
        this.quizzServices = quizzServices;
        this.jogoServices = jogoServices;
    }


    @GetMapping
    public List<Movie> listarFilmes(){
        return movieServices.filmesAleatorios();
    }

    @PostMapping
    public boolean verificarResultado(@PathVariable String opcaoSelecionada, @RequestBody Usuario usuario) throws UsuarioNaoEncontrado, VidaInsuficiente, IOException {
        Optional<Usuario> jogador = usuarioServices.procurarUsuario(usuario);
        //fiz uma pequena alteraçao comentar com a galera
        List<Movie> opcoesDoQuiz = movieServices.filmesAleatorios();//aqui que busca os filmes
        Collections.sort(opcoesDoQuiz,  Comparator.comparing(Movie::getScore));
        Optional<Movie> opcaoSelecionadaValida = opcoesDoQuiz.stream()
                .filter(m -> m.getImdbId().equals(opcaoSelecionada))
                .findFirst();
        boolean result = false;
        if(jogador.isPresent() && opcaoSelecionadaValida.isPresent()){
            if(opcoesDoQuiz.get(0).getImdbId().equals(opcaoSelecionada)){
                result = true;
                jogador.get().setCombo(jogador.get().getCombo()+0.1);
                usuarioServices.atualizarPontos(usuario, quizzServices.pontosFeitos(jogador.get()));
            }else{
                result = false;
                jogador.get().setCombo(1);
            }
        }
        usuarioServices.vidas(jogador, result);
        jogoServices.atualizarJogo(jogador);
        return result;
    }




}

