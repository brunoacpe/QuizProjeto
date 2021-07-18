package br.com.letscode.Controllers;


import br.com.letscode.DAO.MovieDAO;
import br.com.letscode.DAO.UsuarioDAO;
import br.com.letscode.Model.Movie;
import br.com.letscode.Model.Usuario;
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

    @Autowired
    public QuizController(UsuarioServices usuarioServices, MovieServices movieServices,QuizzServices quizzServices){
        this.usuarioServices = usuarioServices;
        this.movieServices = movieServices;
        this.quizzServices = quizzServices;
    }


    @GetMapping
    public List<Movie> listarFilmes(Usuario usuario){
        //No caso só retorna 2 filmes;
        //TODO -- Parte aonde vem a implementação do guilherme ->>
        return movieServices.filmesAleatorios(usuario);
    }

    //Post -> enviar requisição usuário, senha e id do filme/série vencedor (Retornar True ou false);
    //TODO Criar exceção de senha/usuário inválido e de ImdbId do filme inválido
    @PostMapping
    public boolean verificarResultado(@PathVariable String opcaoSelecionada, @RequestBody Usuario usuario){
        Optional<Usuario> jogador = usuarioServices.procurarUsuario(usuario);
        //fiz uma pequena alteraçao comentar com a galera
        List<Movie> opcoesDoQuiz = listarFilmes(jogador.get());
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
        return result;
    }




}

