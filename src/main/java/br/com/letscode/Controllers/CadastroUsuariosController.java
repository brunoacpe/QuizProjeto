package br.com.letscode.Controllers;


import br.com.letscode.Exceptions.CadastroDeUsuarioInvalido;
import br.com.letscode.Model.Movie;
import br.com.letscode.Model.Usuario;
import br.com.letscode.Services.MovieServices;
import br.com.letscode.Services.UsuarioServices;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/usuarios")
@RestController
public class CadastroUsuariosController {


    private UsuarioServices usuariosService;


    @Autowired
    public void setUsuariosService(UsuarioServices usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public List<Usuario> listarFilmes(){
        return this.usuariosService.listar();
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) throws CadastroDeUsuarioInvalido {
        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setNome(usuario.getNome());
        usuarioNovo.setSenha(usuario.getSenha());
        usuarioNovo.setVidas(3);
        usuarioNovo.setPontuação(0);
        return this.usuariosService.criarUsuario(usuario);
    }
}
