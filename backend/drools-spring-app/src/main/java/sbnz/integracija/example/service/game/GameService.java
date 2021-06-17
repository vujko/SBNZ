package sbnz.integracija.example.service.game;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.dto.RecommendDto;
import sbnz.integracija.example.facts.Game;
import sbnz.integracija.example.facts.RegisteredUser;
import sbnz.integracija.example.repository.GameRepository;
import sbnz.integracija.example.repository.RegistratedUserRepository;
import sbnz.integracija.example.service.knowledge.KnowledgeService;

@Service
@RequiredArgsConstructor
public class GameService implements RecommendGamesUseCase {
	
	private final RegistratedUserRepository registratedUserRepository;
	private final GameRepository gameRepostiory;
	private final KnowledgeService knowledgeService;

	@Override
	public List<Game> recommendGames(RecommendDto dto) {
		
		
		// Get resoures that we need
		List<Game> games = gameRepostiory.findAll();
		List<RegisteredUser> users = registratedUserRepository.findAll();	
		RegisteredUser tempUser = users.get(0);
	
		//Set up session
		knowledgeService.getRulesSession().setGlobal("userInput", dto);
		
		for (Game game : games) {
			knowledgeService.getRulesSession().insert(game);
		}
		
		knowledgeService.getRulesSession().setGlobal("tempUser", tempUser);
		
		for(int i = 1; i < users.size(); i++) {
			knowledgeService.getRulesSession().insert(users.get(i));
		}

		//Fire rules
		knowledgeService.getRulesSession().fireAllRules();
		
		//Return result
		List<Game> recommendList = new ArrayList<Game>();
		
		QueryResults results = knowledgeService.getRulesSession().getQueryResults("Get games");
		for(QueryResultsRow r: results) {
			recommendList.add((Game) r.get("$g"));
		}
		
		knowledgeService.disposeRulesSession();
		
		return recommendList;
	}
	

}
