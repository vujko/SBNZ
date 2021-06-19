package sbnz.integracija.example.service.WeeklyService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.dto.WeeklyReportDto;
import sbnz.integracija.example.facts.Game;
import sbnz.integracija.example.facts.Purchase;
import sbnz.integracija.example.facts.Rating;
import sbnz.integracija.example.facts.RegisteredUser;
import sbnz.integracija.example.facts.WeeklyProfitTrigger;
import sbnz.integracija.example.repository.GameRepository;
import sbnz.integracija.example.repository.NotificationRepository;
import sbnz.integracija.example.repository.PurchaseRepository;
import sbnz.integracija.example.repository.RatingRepository;
import sbnz.integracija.example.service.knowledge.KnowledgeService;

@Service
@RequiredArgsConstructor
public class WeeklyService implements GetWeeklyReportUseCase {
	
	private final GameRepository gameRepository;
	private final RatingRepository ratingRepository;
	private final KnowledgeService knowledgeService;
	private final PurchaseRepository purchaseRepository;
	
	@Override
	public WeeklyReportDto getWeeklyReport() {
		
		List<Game> games = gameRepository.findAll();
		List<Purchase> purchases = purchaseRepository.findAll();
		List<Rating> ratings = ratingRepository.findAll();
		
		WeeklyProfitTrigger trigger = new WeeklyProfitTrigger();
		knowledgeService.getReportsSession().insert(trigger);

		for (Game game : games) {
			knowledgeService.getReportsSession().insert(game);
		}
		
		for(int i = 1; i < purchases.size(); i++) {
			knowledgeService.getReportsSession().insert(purchases.get(i));
		}
		
		for(int i = 1; i < ratings.size(); i++) {
			knowledgeService.getReportsSession().insert(ratings.get(i));
		}
		
		//Fire rules
		knowledgeService.getReportsSession().fireAllRules();
		knowledgeService.disposeRueportsSession();
		
		return new WeeklyReportDto(games, trigger.getProfitSum());

	}
}
