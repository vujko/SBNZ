package sbnz.integracija;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.dto.RecommendDto;
import sbnz.integracija.example.facts.Game;
import sbnz.integracija.example.facts.Purchase;
import sbnz.integracija.example.facts.Rating;
import sbnz.integracija.example.facts.RegisteredUser;
import sbnz.integracija.example.facts.Tag;
import sbnz.integracija.example.facts.RegisteredUser.UserRelation;
import sbnz.integracija.example.facts.Tag.TagType;
import sbnz.integracija.example.facts.WeeklyProfitTrigger;

public class ReportTest {

private KieSession kSession;
	
	@Before
	public void initialize() {
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        kSession =  kContainer.newKieSession("reportsSession");
	}
	
	@Test
	public void gamesOfTheWeekTest() {
		Game game1 = new Game(null, "Call Of Duty 2", "Activision", "Activision", new HashSet<Rating>(),
        		new HashSet<Tag>(), (float)20.0, "image1", (float)0.0, 0, 0, 0, Game.GameStatus.NA);
		
		Rating tempUserGame1Rating = new Rating(null, null, game1, LocalDateTime.of(2021, Month.JUNE, 15, 0, 0), 4);
		Rating simUserGame1Rating = new Rating(null, null, game1, LocalDateTime.of(2021, Month.JUNE, 3, 0, 0), 4);
		Purchase tempUserGame1Purchase = new Purchase(null, null, game1, LocalDateTime.of(2021, Month.JUNE, 14, 0, 0));
		Purchase simUserGame1Purchase = new Purchase(null, null, game1, LocalDateTime.of(2021, Month.JUNE, 2, 0, 0));
		
		kSession.insert(game1);
		kSession.insert(tempUserGame1Rating);
		kSession.insert(simUserGame1Rating);
		kSession.insert(tempUserGame1Purchase);
		kSession.insert(simUserGame1Purchase);
		
		int num = kSession.fireAllRules();
		assertEquals(1, num);
		assertEquals(17, game1.getScore());
	}
	
	@Test
	public void WeeklyProfitTest() {
		Game game1 = new Game(null, "Call Of Duty 2", "Activision", "Activision", new HashSet<Rating>(),
        		new HashSet<Tag>(), (float)20.0, "image1", (float)0.0, 0, 0, 0, Game.GameStatus.NA);
		
		Purchase tempUserGame1Purchase = new Purchase(null, null, game1, LocalDateTime.of(2021, Month.JUNE, 14, 0, 0));
		Purchase simUserGame1Purchase = new Purchase(null, null, game1, LocalDateTime.of(2021, Month.JUNE, 2, 0, 0));
		WeeklyProfitTrigger wpt = new WeeklyProfitTrigger();
		kSession.insert(game1);
		kSession.insert(tempUserGame1Purchase);
		kSession.insert(simUserGame1Purchase);
		kSession.insert(wpt);
		
		int num = kSession.fireAllRules();
		assertEquals(1, num);
		assertEquals(20.0, wpt.getProfitSum(), 0.05);
	}
}
