package br.com.letscode.Controllers;


import br.com.letscode.DAO.MovieDAO;
import br.com.letscode.DAO.UsuarioDAO;
import br.com.letscode.Model.Movie;
import br.com.letscode.Model.Usuario;
import br.com.letscode.Services.MovieServices;
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

    private MovieDAO movieDAO;
    private UsuarioDAO usuarioDAO;
    private MovieServices movieServices;

    @Autowired
    public QuizController(MovieDAO movieDAO, UsuarioDAO usuarioDAO, MovieServices movieServices){
        this.movieDAO = movieDAO;
        this.usuarioDAO = usuarioDAO;
        this.movieServices = movieServices;
    }


    @GetMapping
    public List<Movie> listarFilmes(){
        //No caso só retorna 2 filmes;
        return movieDAO.listarTodos();
    }

    //Post -> enviar requisição usuário, senha e id do filme/série vencedor (Retornar True ou false);
    //TODO Criar exceção de senha/usuário inválido e de ImdbId do filme inválido
    @PostMapping
    public boolean verificarResultado(@PathVariable String opcaoSelecionada, @RequestBody Usuario usuario){
        Optional<Usuario> jogador = usuarioDAO.listarTodos().stream().filter(u -> u.equals(usuario)).findFirst();
        List<Movie> opcoesDoQuiz = listarFilmes();
        Collections.sort(opcoesDoQuiz,  Comparator.comparing(Movie::getScore));
        Optional<Movie> opcaoSelecionadaValida = opcoesDoQuiz.stream().filter(m -> m.getImdbId().equals(opcaoSelecionada)).findFirst();
        boolean result = false;
        if(jogador.isPresent() && opcaoSelecionadaValida.isPresent()){
            if(opcoesDoQuiz.get(0).getImdbId().equals(opcaoSelecionada)){
                result = true;
                this.atualizarPontos(usuario, result);
            }else{
                result = false;
                this.atualizarPontos(usuario, result);
            }
        }
        return result;
    }

    public void atualizarPontos(Usuario usuario, boolean result){
        //TODO colocar o método de UsuarioSerives aqui !
    }



}
