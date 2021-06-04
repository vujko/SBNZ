package sbnz.integracija;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class SimpleRulesTest {
	
	private KieSession kSession;
	private Game game;
	
	@Before
	public void initialize() {
		
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession("simple-rules-test-session");
        game = new Game("Call Of Duty 2", "Activision", "Activision", new HashSet<Rating>(), new HashSet<Tag>(),  20.0, "image1", 0.0, 0, 0, 0);
        
	}
	
	@Test
	public void testGenre() {
		kSession.insert(game);
		int num = kSession.fireAllRules();
		assertEquals(num, 1);
	}

}
