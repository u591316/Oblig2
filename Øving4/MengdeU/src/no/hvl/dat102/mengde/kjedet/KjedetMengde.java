package no.hvl.dat102.mengde.kjedet;

//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.*;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.*;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}//

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			LinearNode<T> node = new LinearNode<T>(element);
			node.setNeste(start);
			start = node;
			antall++;
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		LinearNode<T> forgjenger, aktuell;
		T resultat = null;

		int valg = rand.nextInt(antall) + 1;
		if (valg == 1) {
			resultat = start.getElement();
			start = start.getNeste();
		} else {
			forgjenger = start;
			for (int nr = 2; nr < valg; nr++) {
				forgjenger = forgjenger.getNeste();
			}
			aktuell = forgjenger.getNeste();
			resultat = aktuell.getElement();
			forgjenger.setNeste(aktuell.getNeste());
		}
		antall--;

		return resultat;

	}//

	@Override
	public T fjern(T element) {

		if (erTom())
			throw new EmptyCollectionException("mengde");

		boolean funnet = false;
		LinearNode<T> forgjenger = null, aktuell;
		T resultat = null;
		aktuell = start;
		if (element.equals(start.getElement())) {
			resultat = start.getElement();
			start = start.getNeste();
			antall--;
		
		} else {
			while (!funnet && aktuell.getNeste() != null) {
				forgjenger = aktuell;
				aktuell = aktuell.getNeste();

				if (aktuell.getElement().equals(element)) {
					funnet = true;
					forgjenger.setNeste(aktuell.getNeste());
					resultat = aktuell.getElement();
					antall--;
				}

			}
		}
		
		return resultat;
	}//

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}

	@Override
	public boolean equals(Object m2) {
		
		KjedetMengde<T> km2 = (KjedetMengde<T>) m2; 
		boolean likeMengder = km2.antall() == antall; 
		Iterator<T> km2i = km2.oppramser();
		while(km2i.hasNext() && likeMengder)
			if(!inneholder(km2i.next()))
				likeMengder = false; 
		return likeMengder; 
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		MengdeADT<T> begge = new KjedetMengde<T>();
		LinearNode<T> aktuell = start;
		Iterator<T> thisIterator = this.oppramser();
		T element = null;
//		if(this.equals(m2)) 
//			begge = this; 
		
		while(thisIterator.hasNext()) {
			((KjedetMengde<T>) begge).settInn(thisIterator.next());
			
		}
		begge.leggTilAlle(m2);
//		begge = this; 
		
		return begge;
		
		}
	
	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new KjedetMengde<T>();
		T element;
		 
		Iterator<T> iterator = this.oppramser();
		while(iterator.hasNext()) {
		
			element =  iterator.next(); 
			
		if(m2.inneholder(element))
			snittM.leggTil(element);
		
		}
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new KjedetMengde<T>();
		T element;
		
		Iterator<T> thisIterator = this.oppramser(); 
		while(thisIterator.hasNext()) {
			element = thisIterator.next();
			if(!m2.inneholder(element))
				((KjedetMengde<T>)differensM).leggTil(element);
		}

		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		// Fyll ut
		return erUnderMengde;
	}

	@Override
	public Iterator<T> oppramser() {
		return new KjedetIterator<T>(start);
	}

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}
	public String toString() {
		String resultat = ""; 
		LinearNode<T> aktuell = start; 
		while(aktuell != null) {
			resultat += aktuell.getElement().toString() + "\t";
			aktuell = aktuell.getNeste(); 
		}
		return resultat; 
	}

}// class
