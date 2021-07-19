package br.com.letscode.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String senha;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double pontuação;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int vidas = 3 ;
    private double combo = 1;


}
