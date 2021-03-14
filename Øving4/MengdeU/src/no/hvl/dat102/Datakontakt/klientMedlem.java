package no.hvl.dat102.Datakontakt;

import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class klientMedlem {

	public static void main(String[] args) {

		Datakontakt tab = new Datakontakt(30); 
		
		Hobby h1 = new Hobby("ski"); 
		Hobby h2 = new Hobby("volleyball"); 
		
		KjedetMengde<Hobby>hMengde = new KjedetMengde<Hobby>(); 
		KjedetMengde<Hobby>hMengde2 = new KjedetMengde<Hobby>();
		
		hMengde.leggTil(h1);
		hMengde2.leggTil(h2);
		
		Medlem medlem1 = new Medlem("kåre", hMengde); 
		Medlem medlem2 = new Medlem("Stein", hMengde2); 
		Medlem medlem3 = new Medlem("Frank", hMengde); 
		Medlem medlem4 = new Medlem("Karl", hMengde2); 
		
		tab.leggTilMedlem(medlem1);
		tab.leggTilMedlem(medlem2);
		tab.leggTilMedlem(medlem3);
		tab.leggTilMedlem(medlem4);
		
		tab.finnPartnerFor("Kåre");
		tab.finnPartnerFor("Stein");
		tab.finnPartnerFor("Frank");
		tab.finnPartnerFor("Karl");
		
		tab.print();
	}

}
