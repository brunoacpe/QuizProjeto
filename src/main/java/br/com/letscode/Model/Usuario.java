package br.com.letscode.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String senha;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double pontuação;
    @JsonIgnore
    private int vidas = 3 ;
    @JsonIgnore
    private double combo = 1;


}
