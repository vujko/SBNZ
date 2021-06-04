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
import sbnz.integracija.example.facts.RegisteredUser;
import sbnz.integracija.example.facts.Tag;
import sbnz.integracija.example.facts.Tag.TagType;


public class SimpleRulesTest {
	
	private KieSession kSession;
	private Game game;
	private Tag tag;
	private RecommendDto userInput;
	private RegisteredUser tempUser;
	
	@Before
	public void initialize() {
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        kSession =  kContainer.newKieSession();
        game = new Game(null, "Call Of Duty 2", "Activision", "Activision", new HashSet<Rating>(), new HashSet<Tag>(), (float)20.0, "image1", (float)0.0, 0, 0, 0);
        userInput = new RecommendDto("fps", 10.0f, 25.0f, "PC", "War", "Multiplayer", "Early access");
        kSession.setGlobal("userInput", userInput);
        tempUser = new RegisteredUser(null, "a@gmail.com", "pass", "first", "las", null);
        kSession.setGlobal("tempUser", tempUser);
	}
	
	@Test
	public void testGenreInput() {
        tag = new Tag(null, TagType.GENRE, "fps");
		game.setTags(new HashSet<Tag>(Arrays.asList(tag)));
		
		kSession.insert(game);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(10, game.getScore());
	}
	
	@Test
	public void testPriceAboveRangeInput() {
		//set price out of range
		game.setPrice(50);
		kSession.insert(game);
		
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
	}
	
	@Test
	public void testPriceBelowRangeInput() {
		//set price out of range
		game.setPrice(50);
		kSession.insert(game);
		
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
	}
	
	@Test
	public void testThemeInput() {
        tag = new Tag(null, TagType.THEME, "War");
		game.setTags(new HashSet<Tag>(Arrays.asList(tag)));
		
		kSession.insert(game);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(10, game.getScore());
	}
	
	@Test
	public void testSpecialSectionInput() {
        tag = new Tag(null, TagType.SPECIAL_SECTION, "Early access");
		game.setTags(new HashSet<Tag>(Arrays.asList(tag)));
		
		kSession.insert(game);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(10, game.getScore());
	}
	
	@Test
	public void testPlatformInput() {
        tag = new Tag(null, TagType.PLATFORM, "PC");
		game.setTags(new HashSet<Tag>(Arrays.asList(tag)));
		
		kSession.insert(game);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(10, game.getScore());
	}
	
	@Test
	public void testPlayerSupportInput() {
        tag = new Tag(null, TagType.PLAYER_SUPPORT, "Multiplayer");
		game.setTags(new HashSet<Tag>(Arrays.asList(tag)));
		
		kSession.insert(game);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(10, game.getScore());
	}
	
	@Test
	public void testPopularityRule() {
        game.setNumOfDownloads(101);
		
		kSession.insert(game);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(10, game.getScore());
	}
	
	
	@Test
	public void testHighRatedRule() {
        game.setAverageRating(5f);
		
		kSession.insert(game);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(10, game.getScore());
	}

}
