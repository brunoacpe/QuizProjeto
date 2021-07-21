package br.com.letscode.DAO;

import br.com.letscode.Model.Usuario;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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

@Component
public class JogosDAO {

    private String caminho = "C:\\Users\\Vitoria\\QuizMovieBattle\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\jogos.csv";
    private Path path;

    @PostConstruct
    public void init(){
        this.path = Paths.get(caminho);
    }

    public Usuario persistirJogo(Optional<Usuario> usuario){
        try(BufferedWriter bf = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            bf.write(formatar(usuario.get()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuario.get();
    }

    public List<Usuario> listarTodosJogos(){
        List<Usuario> usuarioList = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(path)){
            usuarioList = br.lines().map(this::converterLinhaEmJogo).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioList;
    }

    public Usuario atualizarJogo(Optional<Usuario> usuario) throws IOException {
        List<String> x = new ArrayList<>();
        String line;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            while ((line = br.readLine()) != null) {
                if (!line.contains(usuario.get().getNome())) {
                    x.add(line);
                }
            }
        }
        Files.delete(path);
        PrintWriter writer = new PrintWriter("C:\\Users\\Vitoria\\QuizMovieBattle\\QuizProjeto\\src\\main\\java\\br\\com\\letscode\\Files\\jogos.csv", StandardCharsets.UTF_8);
        for(String s:x){
            writer.println(s);
        }
        writer.print(formatarOptional(usuario));
        writer.close();

        return usuario.get();
    }

    public Usuario converterLinhaEmJogo(String linha){
        StringTokenizer st = new StringTokenizer(linha,";");
        Usuario usuario = new Usuario();
        usuario.setNome(st.nextToken());
        usuario.setPontuação(Double.valueOf(st.nextToken()));
        return usuario;
    }
    public String formatarOptional(Optional<Usuario> usuario){
        return String.format("%s;%s\r\n",usuario.get().getNome(),usuario.get().getPontuação());
    }
    public String formatar(Usuario usuario){
        return String.format("%s;%s\r\n",usuario.getNome(),usuario.getPontuação());
    }

}
