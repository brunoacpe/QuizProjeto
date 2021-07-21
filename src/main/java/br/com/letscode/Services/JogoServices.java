package br.com.letscode.Services;

import br.com.letscode.DAO.JogosDAO;
import br.com.letscode.Model.Usuario;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;


@Component
public class JogoServices {

    private JogosDAO jogoDAO;

    public JogoServices(JogosDAO jogoDAO){
        this.jogoDAO = jogoDAO;
    }


    public Usuario atualizarJogo(Optional<Usuario> usuario) throws IOException {
        jogoDAO.persistirJogo(usuario);
        return jogoDAO.atualizarJogo(usuario);
    }
}
