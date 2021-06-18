package sbnz.integracija;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.dto.RecommendDto;
import sbnz.integracija.example.facts.*;

public class ComplexRulesTest {
	
	private KieSession kSession;
	
	@Before
	public void initialize() {
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        kSession =  kContainer.newKieSession("rulesSession");      
	}
	
//	@Test
//	public void testSimilarUserRule() {
//		Tag tag1 = new Tag(null, TagType.GENRE, "fps");
//        Tag tag2 = new Tag(null, TagType.THEME, "war");
//        Tag tag3 = new Tag(null, TagType.PLATFORM, "PC");
//        Set<Tag> tags = new HashSet<>(Arrays.asList(
//        		tag1,
//        		tag2,
//        		tag3,
//        		new Tag(null, TagType.SPECIAL_SECTION, "early access"),
//        		new Tag(null, TagType.PLAYER_SUPPORT, "Multiplayer")));
//        RegisteredUser tempUser = new RegisteredUser(null, "a@gmail.com", "pass", "first", "las", null);
//        tempUser.setTags(tags);
//        kSession.setGlobal("tempUser", tempUser);
//        
//        tags = new HashSet<>(Arrays.asList(
//        		tag1,
//        		tag2,
//        		tag3));
//        RegisteredUser simUser = new RegisteredUser(null, "b@gmail.com", "pass", "first", "las", null);
//        simUser.setTags(tags);
//        kSession.insert(simUser);
//        
//		int num = kSession.fireAllRules();
//		
//		assertEquals(1, num);
//		assertTrue(simUser.isSimilar());
//	}
//	
//	@Test
//	public void testSimilarUserRuleFail() {
//		Tag tag1 = new Tag(null, TagType.GENRE, "fps");
//        Tag tag2 = new Tag(null, TagType.THEME, "war");
//        Tag tag3 = new Tag(null, TagType.PLATFORM, "PC");
//        Set<Tag> tags = new HashSet<>(Arrays.asList(
//        		tag1,
//        		tag2,
//        		tag3,
//        		new Tag(null, TagType.SPECIAL_SECTION, "early access"),
//        		new Tag(null, TagType.PLAYER_SUPPORT, "Multiplayer")));
//        RegisteredUser tempUser = new RegisteredUser(null, "a@gmail.com", "pass", "first", "las", null);
//        tempUser.setTags(tags);
//        kSession.setGlobal("tempUser", tempUser);
//        
//        tags = new HashSet<>(Arrays.asList(tag1));
//        RegisteredUser simUser = new RegisteredUser(null, "b@gmail.com", "pass", "first", "las", null);
//        simUser.setTags(tags);
//        kSession.insert(simUser);
//		tags = new HashSet<>(Arrays.asList(tag1));
//		simUser.setTags(tags);
//		int num = kSession.fireAllRules();
//		
//		assertEquals(0, num);
//		assertFalse(simUser.isSimilar());
//	}
//	
//	@Test
//	public void testSimilarGamesRule() {
//		RegisteredUser tempUser = new RegisteredUser(null, "a@gmail.com", "pass", "first", "las", null);
//		RegisteredUser simUser = new RegisteredUser(null, "b@gmail.com", "pass", "first", "las", null);
//		
//		simUser.setSimilar(true);
//		
//		
//		Set<Purchase> tags = new HashSet<>(Arrays.asList(
//        		new Purchase(null, TagType.SPECIAL_SECTION, "early access"),
//        		new Purchase(null, TagType.PLAYER_SUPPORT, "Multiplayer")));
//	}

}
