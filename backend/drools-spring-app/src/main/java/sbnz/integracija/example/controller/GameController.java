package sbnz.integracija.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

	@RequestMapping(value = "/test")
	public String test() {
		return "bla";
	}
}
