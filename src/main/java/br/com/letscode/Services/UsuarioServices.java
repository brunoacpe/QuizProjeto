package br.com.letscode.Services;

import br.com.letscode.DAO.RankingDAO;
import br.com.letscode.DAO.UsuarioDAO;
import br.com.letscode.Exceptions.CadastroDeUsuarioInvalido;
import br.com.letscode.Exceptions.UsuarioNaoEncontrado;
import br.com.letscode.Exceptions.VidaInsuficiente;
import br.com.letscode.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices {

    private UsuarioDAO usuarioDAO;
    private RankingDAO rankingDAO;

    @Autowired
    public UsuarioServices (UsuarioDAO usuarioDAO, RankingDAO rankingDAO){
        this.usuarioDAO = usuarioDAO;
        this.rankingDAO = rankingDAO;
    }

    public Optional<Usuario> procurarUsuario(Usuario usuario) throws UsuarioNaoEncontrado, VidaInsuficiente {
        return usuarioDAO.procurarUsuario(usuario.getNome(),usuario.getSenha());
    }
    public List<Usuario> listar() {
        return this.usuarioDAO.listarTodos();
    }

    public Usuario criarUsuario(Usuario usuario) throws CadastroDeUsuarioInvalido {
        String nome = usuario.getNome();
        String senha = usuario.getSenha();
        if (!(nome.length() > 4 && nome.length() < 8) || !(senha.length() > 4 && senha.length() < 8)){
            throw new CadastroDeUsuarioInvalido();
        }
        return usuarioDAO.persistirUsuario(usuario);
    }


    public Optional<Usuario> atualizarPontos(Usuario usuario, double pontos) throws IOException {
            usuario.setPontuação(usuario.getPontuação()+pontos);
            return usuarioDAO.removerUsuarioReescrever(Optional.ofNullable(usuario));
    }

    public Optional<Usuario> vidas(Optional<Usuario> usuario, boolean acertouPergunta) throws IOException {
        int vidasIniciais = usuario.get().getVidas();
        int vidasAtualizadas;
        if (acertouPergunta) {
            vidasAtualizadas = usuario.get().getVidas();
            System.out.println("Você acertou! \nVidas para tentativas =  " + vidasAtualizadas);
        }else {
            vidasAtualizadas = vidasIniciais--;
            usuario.get().setVidas(vidasAtualizadas);
            System.out.println("Você errou! \nVidas para tentativas =  " + vidasAtualizadas);
        }
        usuario = usuarioDAO.removerUsuarioReescrever(usuario);
        if (usuario.get().getVidas()==0){
            return rankingDAO.persistirUsuario(usuario);
        }
        return usuario;
    }

}

