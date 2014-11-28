/**
 * 
 */
package Test;

import static org.junit.Assert.*;

import java.awt.Rectangle;


import org.junit.Before;
import org.junit.Test;

import ExceptionUnivers.DimensionNonValideException;
import ExceptionUnivers.ValeurNegativeException;
import Modele.Matiere;
import Modele.Sexe;
import Modele.Univers;

/**
 * @author luxon
 *
 */
public class testUnivers {

	private Univers u;
	private int l,h;
	private int nbM, nbL;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		l = 16;
		h = 16;
		
		nbM = 10;
		nbL = 5;
		
		try{
			
			u = new Univers(l,h, nbM, nbL);
			
		}catch(Exception e){
			
			System.out.println("Erreur fatal : "+e.getMessage());
		}
		
		
	}


	// Vérifie que l'Univers à bien été instancié 
	@Test
	public void testNull(){
		
		assertNotNull(u);
		
	}
	
	
	// Teste la validité des dimensions
	@Test
	public void testDimension(){
		
		if(u == null) fail();
		
		assertEquals(l, u.getLargeur());
		assertEquals(h, u.getHauteur());
		
	}
	
	// Vérifie que la matrice herbes est bien initialisée
	@Test
	public void testMatrices(){
		
		if(u == null) fail();
		
		assertEquals(l, u.getHerbes().length);
		assertEquals(h,u.getHerbes()[0].length);
		
		for(int i = 0; i < u.getHerbes().length; i++ ){
		
			for(int j = 0; j < u.getHerbes()[0].length; j++ ){
			
				assertTrue(u.getHerbes()[i][j]);
			}
		}
		
	}
	
	
	// Verifie l'état de l'arraylist
	@Test
	public void testArrayList(){
		
		fail("toto");
		/*if(u == null) fail();
		
		assertEquals(0, u.getMatieres().size());
		
		Matiere m; 
		
		try{
			
			u.getMatieres().add(null);
			assertEquals(1, u.getMatieres().size());	// taille 1 ?
			
			assertNotNull(u.enlever(m));	// On enlève ce qu'on a mis dans la liste
			
			assertNull(u.enlever(m)); 	// On enleve m d'une liste vide 
		
		}catch(Exception e){
			
			fail("Exception levée : "+e.getMessage());
		}
		
		assertEquals(0, u.getMatieres().size());	// La liste a */
		
	}
	
	// On teste si les exceptions sont bien levées
	
	@Test(expected=ValeurNegativeException.class)
	public void testVal1Exception() throws ValeurNegativeException, DimensionNonValideException{
		
		new Univers(1, 10, -1, 1);
		
	}
	
	@Test(expected=ValeurNegativeException.class)
	public void testVal2Exception() throws ValeurNegativeException, DimensionNonValideException{
		
		new Univers(1, 10, 1, -1);
		
	}
	
	@Test(expected=DimensionNonValideException.class)
	public void testVal3Exception() throws ValeurNegativeException, DimensionNonValideException{
		
		new Univers(-1, 10, 1, 1);
		
	}
	
	@Test(expected=DimensionNonValideException.class)
	public void testVal4Exception() throws ValeurNegativeException, DimensionNonValideException{
		
		new Univers(1, -10, 1, 1);
		
	}	
	
	// On verifie juste si le cas où on a trop d'animaux renvoie une exception
	@Test(expected=UnsupportedOperationException.class)
	public void testTropAnimaux() throws ValeurNegativeException, DimensionNonValideException{
		
		new Univers(1, 1, 40, 40);
		
	}
	

}
