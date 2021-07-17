package br.com.letscode.Services;

import br.com.letscode.DAO.UsuarioDAO;
import br.com.letscode.Exceptions.CadastroDeUsuarioInvalido;
import br.com.letscode.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServices {

    private UsuarioDAO usuarioDAO;

    @Autowired
    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public List<Usuario> listar() {
        return this.usuarioDAO.listarTodos();
    }

    public Usuario criarUsuario(Usuario usuario) throws CadastroDeUsuarioInvalido {
        String nome = usuario.getNome();
        String senha = usuario.getSenha();
        if (!(nome.length() >= 5 && nome.length() <= 10) || !(senha.length() >= 4 && senha.length() <= 8)){
            throw new CadastroDeUsuarioInvalido();
        }
            return usuarioDAO.persistirUsuario(usuario);
    }

}
