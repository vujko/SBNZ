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
import sbnz.integracija.example.facts.Purchase;
import sbnz.integracija.example.facts.Rating;
import sbnz.integracija.example.facts.RegisteredUser;
import sbnz.integracija.example.repository.GameRepository;
import sbnz.integracija.example.repository.PurchaseRepository;
import sbnz.integracija.example.repository.RatingRepository;
import sbnz.integracija.example.repository.RegistratedUserRepository;
import sbnz.integracija.example.security.api.AuthenticationService;
import sbnz.integracija.example.service.knowledge.KnowledgeService;


@Service
@RequiredArgsConstructor
public class GameService implements RecommendGamesUseCase {
	
	private final RegistratedUserRepository registratedUserRepository;
	private final GameRepository gameRepostiory;
	private final KieContainer kieContainer;
	private final AuthenticationService authenticationService;
	private final KnowledgeService knowledgeService;
	private final PurchaseRepository purchaseRepository;
	private final RatingRepository ratingRepository;
	
	
	@Override
	public List<Game> recommendGames(RecommendDto dto) {
		
		
		// Get resoures that we need
		List<Game> games = gameRepostiory.findAll();
		List<RegisteredUser> users = registratedUserRepository.findAll();
		List<Purchase> purchases = purchaseRepository.findAll();
		List<Rating> ratings = ratingRepository.findAll();
		RegisteredUser tempUser = (RegisteredUser) this.authenticationService.getAuthenticated();
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getId() == tempUser.getId() ) {
				users.remove(i);
				break;
			}
		}
	
		//Set up session
		knowledgeService.getRulesSession().setGlobal("userInput", dto);
		
		for (Game game : games) {
			knowledgeService.getRulesSession().insert(game);
		}
		
		knowledgeService.getRulesSession().setGlobal("tempUser", tempUser);
		
		for(int i = 1; i < users.size(); i++) {
			knowledgeService.getRulesSession().insert(users.get(i));
		}
		
		for(int i = 1; i < purchases.size(); i++) {
			knowledgeService.getRulesSession().insert(purchases.get(i));
		}
		
		for(int i = 1; i < ratings.size(); i++) {
			knowledgeService.getRulesSession().insert(ratings.get(i));
		}

		//Fire rules
<<<<<<< HEAD
=======
		knowledgeService.getRulesSession().fireAllRules();
>>>>>>> a94d03a2396872c34417bd6e475d43e9b4852988
		
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
