package sbnz.integracija.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.facts.Game;
import sbnz.integracija.example.service.game.RecommendGamesUseCase;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/game", produces = "application/json")
public class GameController {
	
	private final RecommendGamesUseCase recomender;

	@GetMapping(value = "/recommend")
	public List<Game> test() {
		return recomender.recommendGames();
	}
}
