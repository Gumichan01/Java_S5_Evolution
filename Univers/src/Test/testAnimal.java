package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testAnimal {

	//private Animal a1,a2;
	
	
	@Before
	public void setUp(){
		
		try{
		
			// a1 = new Loup(...);
			// a2 = new Mouton(...);
			
		}catch(Exception e){
			
			System.out.println("Erreur fatal : "+e.getMessage());
		}
		
	}
	
	// Test si les invariants de classe sont respectés
	@Test
	public void testInvariants() {
		fail("Le test des invariants n'est pas encore défini");
		
		//  0 <= Age <= durre de vie ?
		//assertTrue(a1.getAge() >= 0 && a1.getAge() < a1.getDureeVie());
		//assertTrue(a2.getAge() >= 0 && a2.getAge() < a2.getDureeVie());
		
		//assertFalse(a1.enCoursDeReproduction());
		//assertFalse(a2.enCoursDeReproduction());
		
	}

}
