package br.com.letscode.DAO;

import br.com.letscode.Model.Usuario;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Component
public class RankingDAO {

    private String caminho = "C:\\Users\\Vitoria\\QuizMovieBattle\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\ranking.csv";
    private Path path;

    @PostConstruct
    public void init(){
        this.path = Paths.get(caminho);
    }

    public Optional<Usuario> persistirUsuario(Optional<Usuario> usuario){
        try(BufferedWriter bf = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            bf.write(formatar(usuario));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> listarTodos(){
        List<Usuario> usuarioList = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(path)){
            usuarioList = br.lines().map(this::converterLinhaEmRanking).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        usuarioList.sort(Comparator.comparing(Usuario::getPontuação));
        Collections.reverse(usuarioList);

        return usuarioList;
    }

    public List<Usuario> listarOrdenado(){
        List<Usuario> usuarioList = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(path)){
            usuarioList = br.lines().map(this::converterLinhaEmRanking).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO- AINDA IMPLEMENTAR ESSE MÉTODO.
        return usuarioList;
    }

    private Usuario converterLinhaEmRanking(String linha) {
        StringTokenizer st = new StringTokenizer(linha,";");
        Usuario usuario = new Usuario();
        usuario.setNome(st.nextToken());
        usuario.setPontuação(Integer.valueOf(st.nextToken()));
        return usuario;
    }
    private String formatar(Optional<Usuario> usuario){
        return String.format("%s,%s",usuario.get().getNome(),usuario.get().getPontuação());
    }
}
