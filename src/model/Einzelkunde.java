package model;

import java.util.Date;

public class Einzelkunde extends Kunde {

	private String vorname;
	private String nachname;
	private long fuehrerausweisNummer; // nicht double

	// erweiterter Konstruktor für Registration
	public Einzelkunde(String sUN, String ort, int plz, String land, int alter, String telefonNummer, String email,
			String username, String password, String kkInhaber, long kkNummer, String kkAblaufdatum, int kkPruefnummer,
			String vorname, String nachname, long fuehrerausweisNummer) {
		setVorname(vorname);
		setNachname(nachname);
		setFuehrerausweisNummer(fuehrerausweisNummer);
	}

	// String to String Methode
	public String toString() {
		return "Kunde:: Nummer= " + this.getKundenNummer() + " Email= " + this.getEmail() + " Nachname= "
				+ this.nachname;
	}

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

	public long getFuehrerausweisNummer() {
		return fuehrerausweisNummer;
	}

	public void setFuehrerausweisNummer(long fuehrerausweisNummer) {
		this.fuehrerausweisNummer = fuehrerausweisNummer;
	}

}
