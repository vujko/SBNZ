package sbnz.integracija;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.facts.*;
import sbnz.integracija.example.facts.RegisteredUser.UserRelation;
import sbnz.integracija.example.facts.Tag.TagType;

public class ComplexRulesTest {
	
	private KieSession kSession;
	
	@Before
	public void initialize() {
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        kSession =  kContainer.newKieSession("complexRulesSession");
	}
	
	@Test
	public void testSimilarUserRule() {
		Tag tag1 = new Tag(null, TagType.GENRE, "fps");
        Tag tag2 = new Tag(null, TagType.THEME, "war");
        Tag tag3 = new Tag(null, TagType.PLATFORM, "PC");
        Set<Tag> tags = new HashSet<>(Arrays.asList(
        		tag1,
        		tag2,
        		tag3,
        		new Tag(null, TagType.SPECIAL_SECTION, "early access"),
        		new Tag(null, TagType.PLAYER_SUPPORT, "Multiplayer")));
        RegisteredUser tempUser = new RegisteredUser(null, "a@gmail.com", "pass", "first", "las", null);
        tempUser.setTags(tags);
        kSession.setGlobal("tempUser", tempUser);
        
        tags = new HashSet<>(Arrays.asList(
        		tag1,
        		tag2,
        		tag3));
        RegisteredUser simUser = new RegisteredUser(null, "b@gmail.com", "pass", "first", "las", null);
        simUser.setTags(tags);
        kSession.insert(simUser);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(UserRelation.USER_SIMIRAL, simUser.getStatus());
	}
	
	@Test
	public void testSimilarUserRuleFail() {
		Tag tag1 = new Tag(null, TagType.GENRE, "fps");
        Tag tag2 = new Tag(null, TagType.THEME, "war");
        Tag tag3 = new Tag(null, TagType.PLATFORM, "PC");
        Set<Tag> tags = new HashSet<>(Arrays.asList(
        		tag1,
        		tag2,
        		tag3,
        		new Tag(null, TagType.SPECIAL_SECTION, "early access"),
        		new Tag(null, TagType.PLAYER_SUPPORT, "Multiplayer")));
        RegisteredUser tempUser = new RegisteredUser(null, "a@gmail.com", "pass", "first", "las", null);
        tempUser.setTags(tags);
        kSession.setGlobal("tempUser", tempUser);
        
        tags = new HashSet<>(Arrays.asList(tag1));
        RegisteredUser simUser = new RegisteredUser(null, "b@gmail.com", "pass", "first", "las", null);
        simUser.setTags(tags);
        kSession.insert(simUser);
		tags = new HashSet<>(Arrays.asList(tag1));
		simUser.setTags(tags);
		int num = kSession.fireAllRules();
		
		assertEquals(0, num);
		assertNotEquals(UserRelation.USER_SIMIRAL, simUser.getStatus());
	}
	
	@Test
	public void testSimilarGamesRule() {
		RegisteredUser tempUser = new RegisteredUser(null, "a@gmail.com", "pass", "first", "las", null);
		RegisteredUser simUser = new RegisteredUser(null, "b@gmail.com", "pass", "first", "las", null);
		
		simUser.setStatus(UserRelation.USER_SIMIRAL);
		
		Game game1 = new Game(null, "Call Of Duty 2", "Activision", "Activision", new HashSet<Rating>(),
        		new HashSet<Tag>(), (float)20.0, "image1", (float)0.0, 0, 0, 0, Game.GameStatus.NA);
		Game game2 = new Game(null, "PUBG", "PUBG Corporation", "PUBG Corporation", new HashSet<Rating>(),
        		new HashSet<Tag>(), (float)20.0, "image1", (float)0.0, 0, 0, 0, Game.GameStatus.NA);
		Game game3 = new Game(null, "PUBG New State", "PUBG Corporation", "PUBG Corporation", new HashSet<Rating>(),
        		new HashSet<Tag>(), (float)20.0, "image1", (float)0.0, 0, 0, 0, Game.GameStatus.NA);

		Rating tempUserGame1Rating = new Rating(null, tempUser, game1, LocalDateTime.of(2021, Month.JUNE, 15, 0, 0), 4);
		Rating simUserGame1Rating = new Rating(null, simUser, game1, LocalDateTime.of(2021, Month.JUNE, 3, 0, 0), 4);
		Set<Rating> game1Ratings = new HashSet<>(Arrays.asList(
        		tempUserGame1Rating,
        		simUserGame1Rating
        		));
		Rating randomUserGame2Rating = new Rating(null, null, game2, LocalDateTime.of(2021, Month.JUNE, 11, 0, 0), 3);
		Rating simUserGame2Rating = new Rating(null, simUser, game2, LocalDateTime.of(2021, Month.JUNE, 11, 0, 0), 4);
		//game2 average rating 3.5
		Set<Rating> game2Ratings = new HashSet<>(Arrays.asList(
				simUserGame2Rating,
				randomUserGame2Rating
        		));
		
		Rating simUserGame3Rating = new Rating(null, simUser, game3, LocalDateTime.of(2021, Month.JUNE, 16, 0, 0), 5);
		//game3 average rating 4.5
		Set<Rating> game3Ratings= new HashSet<>(Arrays.asList(
				simUserGame3Rating
        		));
		
		game1.setRatings(game1Ratings);
		game2.setRatings(game2Ratings);
		game3.setRatings(game3Ratings);
		

		Purchase tempUserGame1Purchase = new Purchase(null, tempUser, game1, LocalDateTime.of(2021, Month.JUNE, 14, 0, 0));
		Purchase simUserGame1Purchase = new Purchase(null, simUser, game1, LocalDateTime.of(2021, Month.JUNE, 2, 0, 0));
		Purchase simUserGame2Purchase = new Purchase(null, simUser, game2, LocalDateTime.of(2021, Month.JUNE, 10, 0, 0));
		Purchase simUserGame3Purchase = new Purchase(null, simUser, game3, LocalDateTime.of(2021, Month.JUNE, 15, 0, 0));

		tempUser.addPurchase(tempUserGame1Purchase);
		simUser.addPurchase(simUserGame1Purchase);
		simUser.addPurchase(simUserGame2Purchase);
		simUser.addPurchase(simUserGame3Purchase);
		
		tempUser.addRating(tempUserGame1Rating);
		simUser.addRating(simUserGame1Rating);
		simUser.addRating(simUserGame2Rating);
		simUser.addRating(simUserGame3Rating);
		
		kSession.setGlobal("tempUser", tempUser);
		kSession.insert(simUser);
		
		kSession.insert(game1);
		kSession.insert(game2);
		kSession.insert(game3);
		
		
		kSession.insert(tempUserGame1Rating);
		kSession.insert(simUserGame1Rating);
		kSession.insert(simUserGame2Rating);
		kSession.insert(randomUserGame2Rating);
		kSession.insert(simUserGame3Rating);
		int num = kSession.fireAllRules();
		assertEquals(1, num);
		assertEquals(10, game3.getScore());
		
		
		
		
	}

}
