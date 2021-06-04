package sbnz.integracija.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.dto.RecommendDto;
import sbnz.integracija.example.facts.Game;
import sbnz.integracija.example.service.game.RecommendGamesUseCase;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/game", produces = "application/json")
public class GameController {
	
	private final RecommendGamesUseCase recomender;

	@PostMapping(value = "/recommend")
	public List<Game> test(@RequestBody RecommendDto dto) {
		return recomender.recommendGames(dto);
	}
}
