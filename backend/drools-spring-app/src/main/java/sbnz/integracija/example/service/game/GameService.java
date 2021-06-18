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
import sbnz.integracija.example.security.api.AuthenticationService;

@Service
@RequiredArgsConstructor
public class GameService implements RecommendGamesUseCase {
	
	private final RegistratedUserRepository registratedUserRepository;
	private final GameRepository gameRepostiory;
	private final KieContainer kieContainer;
	private final AuthenticationService authenticationService;

	@Override
	public List<Game> recommendGames(RecommendDto dto) {
		
		KieSession kieSession = kieContainer.newKieSession();
		
		// Get resoures that we need
		List<Game> games = gameRepostiory.findAll();
		List<RegisteredUser> users = registratedUserRepository.findAll();	
		RegisteredUser tempUser = (RegisteredUser) this.authenticationService.getAuthenticated();
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getId() == tempUser.getId() ) {
				users.remove(i);
				break;
			}
		}
	
		//Set up session
		kieSession.setGlobal("userInput", dto);
		
		for (Game game : games) {
			kieSession.insert(game);
		}
		
		kieSession.setGlobal("tempUser", tempUser);
		
		for(int i = 1; i < users.size(); i++) {
			kieSession.insert(users.get(i));
		}

		//Fire rules
		kieSession.fireAllRules();
		
		//Return result
		List<Game> recommendList = new ArrayList<Game>();
		
		QueryResults results = kieSession.getQueryResults("Get games");
		for(QueryResultsRow r: results) {
			recommendList.add((Game) r.get("$g"));
		}
		
		kieSession.dispose();
		
		return recommendList;
	}
	

}
