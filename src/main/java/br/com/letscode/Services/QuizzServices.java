package br.com.letscode.Services;

import br.com.letscode.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizzServices {

    private UsuarioServices usuarioServices;
    @Autowired
    public QuizzServices (UsuarioServices usuarioServices){
        this.usuarioServices = usuarioServices;
    }

    public double pontosFeitos(Usuario usuario){
        double pontos = 100;
        pontos = pontos * usuario.getCombo();
        pontos += usuario.getPontuação();
        usuario.setPontuação(pontos);
        return pontos;
    }

}
