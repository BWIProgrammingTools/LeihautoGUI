package model;

import java.io.Serializable;

public class Firmenkunde extends Kunde implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	private String firmenname;

	// Erweiterter Konstruktor für Firmenkunden
	public Firmenkunde(String sUN, String ort, int plz, String land, int alter, String telefonNummer, String email,
			String username, String password, String kkInhaber, long kkNummer, String kkAblaufdatum, int kkPruefnummer,
			String firmenname) {
		super(sUN, ort, plz, land, alter, telefonNummer, email, username, password, kkInhaber, kkNummer, kkAblaufdatum,
				kkPruefnummer);
		setFirmenname(firmenname);
	}

	// Strint to String für Objektanzeige
	public String toString() {
		return "FirmenkundenID: " + this.getKundenNummer() + ", Firmenname: " + this.getFirmenname() + ", Email: "
				+ this.getEmail() + ", Username: " + this.getUsername() + ", Passwort: " + this.getPassword()
				+ ", Gesperrt?: " + this.isGesperrt();
	}

	//getters und setters
	public String getFirmenname() {
		return firmenname;
	}

	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}

}
