package no.hvl.dat102.mengde.tabell;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.LinearNode;

public class TabellMengde<T> implements MengdeADT<T> {
	// ADT-en Mengde implementert som tabell
	//
	private final static Random tilf = new Random();
	private final static int STDK = 100;
	private static final int NOT_FOUND = -1;
	private int antall;
	private T[] tab;

	public TabellMengde() {
		this(STDK);
	}

	public TabellMengde(int start) {
		antall = 0;
		tab = (T[]) (new Object[start]);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			if (antall == tab.length) {
				utvidKapasitet();
			}
			tab[antall] = element;
			antall++;
		}
	}

	private void utvidKapasitet() {
		T[] hjelpetabell = (T[]) (new Object[2 * tab.length]);
		for (int i = 0; i < tab.length; i++) {
			hjelpetabell[i] = tab[i];
		}
		tab = hjelpetabell;
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		T svar = null;
		int indeks = tilf.nextInt(antall);
		svar = tab[indeks];
		tab[indeks] = tab[antall - 1];
		antall--;

		return svar;
	}

	@Override
	public T fjern(T element) {
		//TODO
		// S�ker etter og fjerner element. Returnerer null-ref ved ikke-funn
		
		T result; 
		int index = find(element); 
		if(index == NOT_FOUND) {
			System.out.println("Element not found");
			return null; 
		}
		result = tab[index]; 
		antall--;
		
		//shift the appropriate elements(Close the gap)
		for(int scan = index; scan < antall; scan++) {
				tab[scan] = tab[scan+1];
			tab[antall] = null; 
			
		}
		antall--;
		return result; 
	}
	
	/*
	 * Hjelpemetode for å finne et element
	 */
	private int find(T target) {
		int scan = 0; 
		int result = NOT_FOUND; 
		if(!erTom()) {
			while(result == NOT_FOUND && scan < antall) {
				if(target.equals(tab[scan])) {
					result = scan; 
					
				}else
					scan++;
			}
		}
		return result; 
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		for (int i = 0; (i < antall) && (!funnet); i++) {
			if (tab[i].equals(element)) {
				funnet = true;
			}
		}
		return (funnet);
	}
	
	/*
	 * N�r vi overkj�rer (override) equals- meteoden er det anbefalt at vi ogs�
	 * overkj�rer hascode-metoden da en del biblioterker burker hascode sammen med
	 * equals. Vi kommer tilbake til forklaring og bruk av hascode senere i faget.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + antall;
		result = prime * result + Arrays.deepHashCode(tab);
		return result;
	}

	@Override
	public boolean equals(Object m2) {
		//TODO
		TabellMengde<T> Tm2 = (TabellMengde<T>)m2; 
		boolean likeMengder = Tm2.antall() == antall; 
		Iterator<T> Tm2I = Tm2.oppramser();
		while(Tm2I.hasNext() && likeMengder) {
			if(!inneholder(Tm2I.next())) {
				likeMengder = false; 
			}
		}
		return likeMengder; 
		
		
		
		
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext())
			leggTil(teller.next());
	}

	/*
	 * Denne versjonen av unionen er lite effekktiv
	 * 
	 * @Override public MengdeADT<T> union(MengdeADT<T> m2) { TabellMengde<T> begge
	 * = new TabellMengde<T>(); for (int i = 0; i < antall; i++) {
	 * begge.leggTil(tab[i]); } Iterator<T> teller = m2.oppramser();
	 * 
	 * while (teller.hasNext()) { begge.leggTil(teller.next()); } return
	 * (MengdeADT<T>)begge; }
	 */
	@Override

	public MengdeADT<T> union(MengdeADT<T> m2) {
		//TODO
		MengdeADT<T> begge = new TabellMengde<T>();
		T element = null;
		Iterator<T> thisIterator = this.oppramser();
		
		while(thisIterator.hasNext())
			((TabellMengde<T>)begge).settInn(thisIterator.next()); 
		
		begge.leggTilAlle(m2);
		
		return begge;
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new TabellMengde<T>();
		T element = null;
		
		Iterator<T> thisIterator = this.oppramser(); 
		
		while(thisIterator.hasNext()) {
			element = thisIterator.next();
			if(m2.inneholder(element))
				snittM.leggTil(element);
		}
		
		
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		//TODO
		MengdeADT<T> differensM = new TabellMengde<T>();
		T element;
		/*
		 * Fyll ut
		 * 
		 * if (!m2.inneholder(element)) ((TabellMengde<T>) differensM).settInn(element);
		 */
		
		Iterator<T>thisIterator = this.oppramser();
		while(thisIterator.hasNext()) {
			element = thisIterator.next();
			if(!m2.inneholder(element)) {
				((TabellMengde<T>)differensM).settInn(element);
			}
				
		}
		
		

		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		//TODO
		boolean erUnderMengde = true;
		// ...
		return false;
	}

	@Override
	public Iterator<T> oppramser() {
		return new TabellIterator<T>(tab, antall);
	}

	private void settInn(T element) {
		if (antall == tab.length) {
			utvidKapasitet();
		}
		tab[antall] = element;
		antall++;
	}
	public String toString() {
		String resultat = ""; 
		T aktuell = tab[0]; 
		while(aktuell != null) {
			int i = 0; 
		
			resultat += tab[i].toString() + "\t";
			i++;  
		}
		return resultat; 
	}


}// class
