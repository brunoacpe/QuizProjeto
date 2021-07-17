package br.com.letscode.DAO;

import br.com.letscode.Model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.beans.BeanProperty;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


@Setter
@Getter
@Component
public class UsuarioDAO {

    private String caminho = "C:\\Users\\Eu\\Documents\\GitHub\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\usuarios.csv";
    private String caminhoRanking = "C:\\Users\\Eu\\Documents\\GitHub\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\ranking.csv";
    private Path pathUsuarios;
    private Path pathRanking;

    @PostConstruct
    public void init(){
        this.pathUsuarios = Paths.get(caminho);
        this.pathRanking = Paths.get(caminhoRanking);
    }

    public Usuario persistirUsuario(Usuario usuario){
        try(BufferedWriter bf = Files.newBufferedWriter(pathUsuarios, StandardOpenOption.APPEND)){
            bf.write(formatar(usuario));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> listarTodos(){
        List<Usuario> usuarioList = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(pathUsuarios)){
            usuarioList = br.lines().map(this::converterLinhaEmUsuario).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioList;
    }

    public void procurarUsuario(){
        //TODO - Método que ira fazer a autenticação para ver se o usuário realmente existe e pode jogar;
    }

    public Usuario converterLinhaEmUsuario(String linha){
        StringTokenizer st = new StringTokenizer(linha,";");
        Usuario usuario = new Usuario();
        usuario.setNome(st.nextToken());
        usuario.setSenha(st.nextToken());
        usuario.setVidas(Integer.parseInt(st.nextToken()));
        return usuario;
    }

    public String formatar(Usuario usuario) {
        return String.format("%s;%s;%s\n",usuario.getNome(),usuario.getSenha(),usuario.getVidas());
    }
}
