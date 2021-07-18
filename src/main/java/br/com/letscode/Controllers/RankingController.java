package br.com.letscode.Controllers;

import br.com.letscode.Model.Usuario;
import br.com.letscode.Services.RankingServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/ranking")
@RestController
public class RankingController {

    private RankingServices rankingServices;

    public RankingController(RankingServices rankingServices){
        this.rankingServices = rankingServices;
    }

    @GetMapping
    public List<Usuario> listarRankingOrdenado(){
        return rankingServices.listarRanking();
    }


}
