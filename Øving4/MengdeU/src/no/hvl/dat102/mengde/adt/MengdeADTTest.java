package no.hvl.dat102.mengde.adt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class MengdeADTTest {


	
	private MengdeADT<Integer>liste; 
	private MengdeADT<Integer>liste_2; 
	private MengdeADT<Integer>unionRiktig;
	private MengdeADT<Integer>union;
	private MengdeADT<Integer>differensRiktig;
	private MengdeADT<Integer>snittRiktig; 
	private MengdeADT<Integer>snitt;
	
	private Integer e0 = 0; 
	private Integer e1 = 1;
	private Integer e2 = 2; 
	private Integer e3 = 3; 
	private Integer e4 = 4; 
	
	
	protected abstract MengdeADT<Integer>reset(); 
	
	
	@BeforeEach
	public final void setup() {
		
		liste = reset(); 
		liste_2 = reset(); 
		unionRiktig = reset();
		union = reset(); 
		differensRiktig = reset();
		snitt = reset(); 
		snittRiktig = reset(); 
	}
	
	@Test
	public void union() {
		
		
		
		liste.leggTil(e0);
		liste.leggTil(e1);
		liste.leggTil(e4);
		liste_2.leggTil(e0);
		liste_2.leggTil(e3);
		liste_2.leggTil(e1);
		
	   
		unionRiktig.leggTil(e0);
		unionRiktig.leggTil(e1);
		unionRiktig.leggTil(e4);
		
		
		union = liste.union(liste); 
		
		assertTrue(unionRiktig.equals(union));
		
		 
	


	}
	@Test
	public void differens() {
		liste.leggTil(e0);
		liste.leggTil(e1);
		liste.leggTil(e2);
		liste.leggTil(e3); 
		liste_2.leggTil(e0);
		liste_2.leggTil(e1);

		differensRiktig.leggTil(e2);
		differensRiktig.leggTil(e3);
		
		assertTrue(liste.differens(liste_2).equals(differensRiktig));
	}
	
	@Test
	public void snitt() {
		liste.leggTil(e0);
		liste.leggTil(e1);
		liste.leggTil(e2);
		liste.leggTil(e3);
		liste.leggTil(e4);
		liste_2.leggTil(e0);
		liste_2.leggTil(e4);
		
		snitt = liste.snitt(liste_2); 
		
		snittRiktig.leggTil(e0);
		snittRiktig.leggTil(e4);
		
		assertTrue(snitt.equals(snittRiktig));
		
	}
	
	
	
	

}
