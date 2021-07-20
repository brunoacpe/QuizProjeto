package br.com.letscode.Services;

import br.com.letscode.DAO.RankingDAO;
import br.com.letscode.Model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RankingServices {

    private RankingDAO rankingDAO;

    public RankingServices(RankingDAO rankingDAO){
        this.rankingDAO = rankingDAO;
    }

    public List<Usuario> listarRanking(){
        return rankingDAO.listarTodos();
    }
}
