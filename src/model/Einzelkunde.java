package model;
public class Einzelkunde extends Kunde {

	private String vorname;
	private String nachname;
	private int fuehrerausweisNummer; // nicht dooble

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public int getFuehrerausweisNummer() {
		return fuehrerausweisNummer;
	}

	public void setFuehrerausweisNummer(int fuehrerausweisNummer) {
		this.fuehrerausweisNummer = fuehrerausweisNummer;
	}

}
