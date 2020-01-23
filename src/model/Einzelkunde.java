package model;

import java.io.Serializable;

public class Einzelkunde extends Kunde implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	private String vorname;
	private String nachname;
	private long fuehrerausweisNummer;

	// erweiterter Konstruktor für Registration
	public Einzelkunde(String sUN, String ort, int plz, String land, int alter, String telefonNummer, String email,
			String username, String password, String kkInhaber, long kkNummer, String kkAblaufdatum, int kkPruefnummer,
			String vorname, String nachname, long fuehrerausweisNummer) {
		super(sUN, ort, plz, land, alter, telefonNummer, email, username, password, kkInhaber, kkNummer, kkAblaufdatum,
				kkPruefnummer);
		setVorname(vorname);
		setNachname(nachname);
		setFuehrerausweisNummer(fuehrerausweisNummer);
	}

	// String to String Methode
	public String toString() {
		return "EinzelkundenID: " + this.getKundenNummer() + ", Vorname: " + this.getVorname() + ", Nachname: "
				+ this.getNachname() + ", Email: " + this.getEmail() + ", Username: " + this.getUsername()
				+ ", Passwort: " + this.getPassword() + ", Gesperrt?: " + this.isGesperrt();
	}

	//getters und setters
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
