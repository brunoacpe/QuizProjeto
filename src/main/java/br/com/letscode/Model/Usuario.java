package br.com.letscode.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
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


    private String nome;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String senha;
    private int pontuação;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int vidas = 3 ;


}
