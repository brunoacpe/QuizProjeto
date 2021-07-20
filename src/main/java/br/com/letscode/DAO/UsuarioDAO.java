package br.com.letscode.DAO;

import br.com.letscode.Exceptions.UsuarioNaoEncontrado;
import br.com.letscode.Exceptions.VidaInsuficiente;
import br.com.letscode.Model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


@Setter
@Getter
@Component
public class UsuarioDAO {

    private String caminho = "C:\\Users\\Vitoria\\QuizMovieBattle\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\usuarios.csv";
    private String caminhoRanking = "C:\\Users\\Vitoria\\QuizMovieBattle\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\ranking.csv";
    private Path pathUsuarios;
    private Path pathRanking;

    @PostConstruct
    public void init(){
        this.pathUsuarios = Paths.get(caminho);
        this.pathRanking = Paths.get(caminhoRanking);
    }

    public Usuario persistirUsuario(Usuario usuario){
        try(var bf = Files.newBufferedWriter(pathUsuarios, StandardOpenOption.APPEND)){
            bf.write(formatar(usuario));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> listarTodos(){
        List<Usuario> usuarioList = new ArrayList<>();
        try(var br = Files.newBufferedReader(pathUsuarios)){
            usuarioList = br.lines().map(this::converterLinhaEmUsuario).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioList;
    }

    public Optional<Usuario> procurarUsuario(String nome, String senha) throws VidaInsuficiente, UsuarioNaoEncontrado {
        List<Usuario> listUsuario = listarTodos();
        Optional<Usuario> usuario = listUsuario.stream()
                .filter(n -> n.getNome().equalsIgnoreCase(nome)&& n.getSenha().equalsIgnoreCase(senha)).findAny();

        if (usuario.isEmpty()){
            throw new UsuarioNaoEncontrado();
        }
        if(usuario.get().getVidas()==0){
            throw new VidaInsuficiente();
        }

        return usuario;
    }

    public Usuario converterLinhaEmUsuario(String linha){
        var st = new StringTokenizer(linha,";");
        var usuario = new Usuario();
        usuario.setNome(st.nextToken());
        usuario.setSenha(st.nextToken());
        usuario.setVidas(Integer.parseInt(st.nextToken()));
        return usuario;
    }

    public String formatar(Usuario usuario) {
        return String.format("%s;%s;%s\n",usuario.getNome(),usuario.getSenha(),usuario.getVidas());
    }

    public Optional<Usuario> removerUsuarioReescrever(Optional<Usuario> usuario) throws IOException {
        List<String>  x = new ArrayList<>();
        String y = "";
        String line;
        try(var br = Files.newBufferedReader(pathUsuarios)){
            while((line = br.readLine())!=null){
                if(!line.contains(usuario.get().getNome())){
                    x.add(line);
                } else {
                    y = line;
                }
            }
        }

        Files.delete(pathUsuarios);
        var writer = new PrintWriter("C:\\Users\\Vitoria\\QuizMovieBattle\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\usuarios.csv", StandardCharsets.UTF_8);
        for(String s:x){
            writer.println(s);
        }
        writer.println(y);
        writer.close();

        return usuario;
    }

}
