package br.com.letscode.Controllers;


import br.com.letscode.DAO.MovieDAO;
import br.com.letscode.DAO.UsuarioDAO;
import br.com.letscode.Model.Movie;
import br.com.letscode.Model.Usuario;
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

    @Autowired
    public QuizController(MovieDAO movieDAO, UsuarioDAO usuarioDAO){
        this.movieDAO = movieDAO;
        this.usuarioDAO = usuarioDAO;
    }


    @GetMapping
    public List<Movie> listarFilmes(){
        //No caso só retorna 2 filmes;
        return movieDAO.listarTodos();
    }

    //Post -> enviar requisição usuário, senha e id do filme/série vencedor (Retornar True ou false);
    //Post -> enviar requisição usuário, senha e id do filme/série vencedor (Retornar True ou false);
    @PostMapping
    public boolean verificarResultado(@PathVariable String opcaoSelecionada, @RequestBody Usuario usuario){
        Optional<Usuario> jogador = usuarioDAO.listarTodos().stream().filter(u -> u.equals(usuario)).findFirst();
        List<Movie> opcoesDoQuiz = listarFilmes();
        Collections.sort(opcoesDoQuiz,  Comparator.comparing(Movie::getScore));
        boolean result = false;
        if(jogador.isPresent()){
            if(opcoesDoQuiz.get(0).getImdbId().equals(opcaoSelecionada)){
                result = true;
            }else{
                result = false;
            }
        }
        return result;
    }



}
