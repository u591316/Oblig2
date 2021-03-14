package no.hvl.dat102.Datakontakt;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.tabell.TabellMengde;

public class Medlem {

	private String navn; 
	private MengdeADT<Hobby> hobbyer; 
	private int statusIndeks; 
	
	
	public Medlem(String navn, MengdeADT<Hobby> hobbyer) {
		super();
		this.navn = navn;
		this.hobbyer = hobbyer;
		this.statusIndeks = -1; 
		
	}
	
	
	public boolean passerTil(Medlem medlem2) {
		boolean passer = false; 		
		
		if(this.getHobbyer().equals(medlem2.getHobbyer())) {
			
	//		this.setStatusIndeks(medlem2.getStatusIndeks());
			passer = true; 
		}
		return passer; 
	}
	
	
	
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public MengdeADT<Hobby> getHobbyer() {
		return hobbyer;
	}

	public void setHobbyer(MengdeADT<Hobby> hobbyer) {
		this.hobbyer = hobbyer;
	}

	public int getStatusIndeks() {
		return statusIndeks;
	}

	public void setStatusIndeks(int statusIndeks) {
		this.statusIndeks = statusIndeks;
	}

	@Override
	public String toString() {
		String res = "Navn:\t" + navn + "\t"; 
		res += "Hobbyer:\t " + hobbyer.toString() + "\t" + "Status indeks:\t " + statusIndeks; 
		return res; 
	}
	
	public void utskrift() {
		
		System.out.println(this.toString());
	}
	

	
	
	
}
