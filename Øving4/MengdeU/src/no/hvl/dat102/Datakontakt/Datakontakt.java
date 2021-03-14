package no.hvl.dat102.Datakontakt;

import java.util.Iterator;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.tabell.TabellMengde;

public class Datakontakt  {
	

	
	//private TabellMengde<Medlem>medlemsTabell = new TabellMengde<Medlem>(); 
	private int antallMedlemmer; 
	private Medlem[]medlemsTabell; 
	 
	
	public Datakontakt(int antall) {
		
		this.antallMedlemmer = 0;
		this.medlemsTabell = new Medlem[antall]; 
	}

	public void leggTilMedlem(Medlem person) {
		medlemsTabell[antallMedlemmer] = person;
		antallMedlemmer++; 
	}
	
	public int finnMedlemsIndeks(String navn) {
		
		for(int i = 0; i<antallMedlemmer; i++) {
			if(medlemsTabell[i].getNavn() == navn) {
				return i; 
			}
		}
		 return -1; 
		
	}	 
	
	public void finnPartnerFor(String navn) {
		int indeks = finnMedlemsIndeks(navn);
		if(indeks!= -1 && medlemsTabell[indeks].getStatusIndeks()==-1) {
		for(int i = 0; i <antallMedlemmer; i++) {
				if(medlemsTabell[indeks].passerTil(medlemsTabell[i]) && medlemsTabell[i].getStatusIndeks()==-1) {
				medlemsTabell[indeks].setStatusIndeks(i);
				medlemsTabell[i].setStatusIndeks(indeks);
				}
			}
		}
		
	}
	
	public void tilbakestillStatus(String navn) {
		
		int indeks = medlemsTabell[finnMedlemsIndeks(navn)].getStatusIndeks();
		if(indeks != -1) {
		medlemsTabell[finnMedlemsIndeks(navn)].setStatusIndeks(-1);
		medlemsTabell[indeks].setStatusIndeks(-1);
		}
	}
	
	public void print() {
		for(int i = 0; i <antallMedlemmer; i++) {
			medlemsTabell[i].utskrift();
		}
	}

}
