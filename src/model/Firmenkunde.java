package model;

import java.util.Date;

public class Firmenkunde extends Kunde {

	private String firmenname;
	private int personalNummer; // nicht string

	// Erweiterter Konstruktor für Registration
	
	public Firmenkunde(String sUN, String ort, int plz, String land, int alter, String telefonNummer, String email,
			String username, String password, String kkInhaber, long kkNummer, String kkAblaufdatum, int kkPruefnummer,
			String firmenname) {
		super(sUN, ort, plz, land, alter, telefonNummer, email, username, password, kkInhaber, kkNummer, kkAblaufdatum, kkPruefnummer);
		setFirmenname(firmenname);
	}

	//Strint to String für Objektanzeige
	public String toString() {
		return "Kunde:: Nummer= " + this.getKundenNummer() + " Email= " + this.getEmail() + " Firmenname= "
				+ this.firmenname;
	}
	
	public String getFirmenname() {
		return firmenname;
	}

	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}

	public int getPersonalNummer() {
		return personalNummer;
	}

	public void setPersonalNummer(int personalNummer) {
		this.personalNummer = personalNummer;
	}

}
