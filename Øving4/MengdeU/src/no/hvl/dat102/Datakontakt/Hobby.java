package no.hvl.dat102.Datakontakt;

public class Hobby {
	
	private String hobbyNavn;
	
	public Hobby(String hobby) {
		hobbyNavn = hobby; 
	}

	@Override
	public String toString() {
		return "<" + hobbyNavn + ">";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hobbyNavn == null) ? 0 : hobbyNavn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hobby other = (Hobby) obj;
		if (hobbyNavn == null) {
			if (other.hobbyNavn != null)
				return false;
		} else if (!hobbyNavn.equals(other.hobbyNavn))
			return false;
		return true;
	}

	public String getHobbyNavn() {
		return hobbyNavn;
	}

	public void setHobbyNavn(String hobbyNavn) {
		this.hobbyNavn = hobbyNavn;
	}
	
	

}
