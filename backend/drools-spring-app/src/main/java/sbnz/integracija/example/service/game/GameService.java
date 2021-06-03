package sbnz.integracija.example.service.game;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.facts.Game;
import sbnz.integracija.example.repository.GameRepository;

@Service
@RequiredArgsConstructor
public class GameService implements RecommendGamesUseCase {
	
	private final GameRepository gameRepostiory;
	private final KieContainer kieContainer;

	@Override
	public List<Game> recommendGames() {
		
		KieSession kieSession = kieContainer.newKieSession();
		List<Game> games = gameRepostiory.findAll();
		
		for (Game game : games) {
			kieSession.insert(game);
		}

		kieSession.fireAllRules();
		
		QueryResults results = kieSession.getQueryResults("Get games");
		for(QueryResultsRow r: results) {
			Game g = (Game) r.get("$g");
			System.out.println(g.getScore());
		}
		kieSession.dispose();
		
		return games;
	}
	

}
