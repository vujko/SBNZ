package sbnz.integracija;

import static org.junit.Assert.assertEquals;



import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.integracija.example.facts.Notification;
import sbnz.integracija.example.facts.LoginEvent;
import sbnz.integracija.example.facts.PurchaseEvent;


public class CEPRulesTest {
	
	private KieSession kSession;
	
	@Before
	public void initialize() {
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        kSession =  kContainer.newKieSession("eventsSession");
	}
	
	@Test
	public void testTooPurchases() {
		
		for(int i = 0; i < 10; i++) {
			kSession.insert(new PurchaseEvent("bodroza.joca1@gmail.com"));
		}
		
		
		Notification notification = new Notification();
		kSession.insert(notification);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(2, notification.getCode());
	}
	
	@Test
	public void testTooManyLogins() {
		
		for(int i = 0; i < 5; i++) {
			kSession.insert(new LoginEvent("bodroza.joca1@gmail.com"));
		}
		
		Notification notification = new Notification();
		kSession.insert(notification);
		int num = kSession.fireAllRules();
		
		assertEquals(1, num);
		assertEquals(1, notification.getCode());
	}
	
	
	

}
