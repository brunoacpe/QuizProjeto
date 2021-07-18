package br.com.letscode.Services;

import br.com.letscode.DAO.UsuarioDAO;
import br.com.letscode.Exceptions.CadastroDeUsuarioInvalido;
import br.com.letscode.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices {

    //TODO- Escrever o DAO;
    private UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioServices (UsuarioDAO usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }

    public Optional<Usuario> procurarUsuario(Usuario usuario){
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

    public Usuario atualizarPontos(Usuario usuario, boolean result){
        if(result){
            usuario.setPontuação(usuario.getPontuação()+1);
            return usuarioDAO.atualizarRanking(usuario);
        }
        //todo
        return null;
    }

}
