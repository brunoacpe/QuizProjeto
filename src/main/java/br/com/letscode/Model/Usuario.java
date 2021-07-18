package br.com.letscode.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Usuario {

    @NotNull(message = "O nome é obrigatório.")
    private String nome;
    @NotNull(message = "A senha é obrigatória.")
    private String senha;
    private double pontuação;
    private int vidas = 3 ;
    private List<Movie> listaDoJogador;
    private double combo = 1;

}
