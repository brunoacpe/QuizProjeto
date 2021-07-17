package br.com.letscode.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

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
    private int pontuação;
    private int vidas = 3 ;

}
