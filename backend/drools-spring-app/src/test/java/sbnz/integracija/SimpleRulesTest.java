package sbnz.integracija;



import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.dto.RecommendDto;
import sbnz.integracija.example.facts.Game;
import sbnz.integracija.example.facts.Rating;
import sbnz.integracija.example.facts.Tag;
import sbnz.integracija.example.facts.Tag.TagType;


public class SimpleRulesTest {
	
	private KieSession kSession;
	private Game game;
	private Tag tag;
	private RecommendDto userInput;
	
	@Before
	public void initialize() {
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        kSession =  kContainer.newKieSession();
        tag = new Tag(null, TagType.GENRE, "fps");
        game = new Game(null, "Call Of Duty 2", "Activision", "Activision", new HashSet<Rating>(), new HashSet<Tag>(Arrays.asList(tag)), (float)20.0, "image1", (float)0.0, 0, 0, 0);
        userInput = new RecommendDto("fps", 10.0f, 25.0f, "PC", "War", "Multiplayer", "Early access");
        
	}
	
	@Test
	public void testGenre() {
		kSession.insert(game);
		kSession.setGlobal("userInput", userInput);
		
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
	}

}
