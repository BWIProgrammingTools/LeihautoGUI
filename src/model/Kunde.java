package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.io.Serializable;

public class Kunde implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	private int kundenNummer = 1;
	private String strasseUndNummer;
	private String ort;
	private int plz;
	private String land;
	private int alter;
	private String telefonNummer;
	private String email;
	private String username;
	private String password;
	private boolean gesperrt = false;
	private String kkInhaber;
	private long kkNummer;
	private String kkAblaufdatum; // nicht string
	private int kkPruefnummer;
	private String lockReason;

	// Konstruktor für Kunde
	public Kunde(String sUN, String ort, int plz, String land, int alter, String telefonNummer, String email,
			String username, String password, String kkInhaber, long kkNummer, String kkAblaufdatum,
			int kkPruefnummer) {
		setStrasseUndNummer(sUN);
		setOrt(ort);
		setPlz(plz);
		setLand(land);
		setAlter(alter);
		setTelefonNummer(telefonNummer);
		setEmail(email);
		setUsername(username);
		setPassword(password);
		setKkInhaber(kkInhaber);
		setKkNummer(kkNummer);
		setKkAblaufdatum(kkAblaufdatum); // nicht string
		setKkPruefnummer(kkPruefnummer);
	}

	public void lockKunde(String lockReason) {
		this.lockReason = lockReason;
		setGesperrt(true);
	}

	public void unlockKunde() {
		setLockReason("");
		setGesperrt(false);
	}

	// Methode für Kundenregistration
	@SuppressWarnings("unchecked")
	public void registration(Kunde varKunde) {
		// hier startet der Import der bestehenden Kundenliste
		List<Kunde> emptyKundenListe = new ArrayList<Kunde>();
		try {
			FileInputStream fis = new FileInputStream("Kundenliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			emptyKundenListe = (ArrayList<Kunde>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * hier werden die kunden der bestehenden Liste als Objekte herausgefiltert und
		 * der leeren Kundenliste angefügt und die ID hochgezählt
		 */
		for (Kunde existingKunde : emptyKundenListe) {
			System.out.println(existingKunde);
			++this.kundenNummer;
		}

		/*
		 * hier wird der neue Kunde der ursprünglich leeren aber mittlerweile befüllten
		 * liste angefügt
		 */
		emptyKundenListe.add(varKunde);
		System.out.println(varKunde);

		// hier wird die aktualisierte kundenliste wieder herausgeschrieben
		try {
			FileOutputStream fos = new FileOutputStream("Kundenliste.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(emptyKundenListe);
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,
				"Sie haben sich erfolgreich registriert.\nIhre Kundennummer lautet: " + varKunde.getKundenNummer());
	}

	// String to String Methode für Objektanzeige
	public String toString() {
		return "Kundennummer:" + this.kundenNummer + " Username: " + this.username + " Passwort: " + this.password
				+ " Email:" + this.email;
	}

	// getters und setters
	public int getKundenNummer() {
		return kundenNummer;
	}

	public void setKundenNummer(int kundenNummer) {
		this.kundenNummer = kundenNummer;
	}

	public String getStrasseUndNummer() {
		return strasseUndNummer;
	}

	public void setStrasseUndNummer(String strasseUndNummer) {
		this.strasseUndNummer = strasseUndNummer;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	public String getTelefonNummer() {
		return telefonNummer;
	}

	public void setTelefonNummer(String telefonNummer) {
		this.telefonNummer = telefonNummer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isGesperrt() {
		return gesperrt;
	}

	public void setGesperrt(boolean gesperrt) {
		this.gesperrt = gesperrt;
	}

	public String getKkInhaber() {
		return kkInhaber;
	}

	public void setKkInhaber(String kkInhaber) {
		this.kkInhaber = kkInhaber;
	}

	public long getKkNummer() {
		return kkNummer;
	}

	public void setKkNummer(long kkNummer) {
		this.kkNummer = kkNummer;
	}

	public String getKkAblaufdatum() {
		return kkAblaufdatum;
	}

	public void setKkAblaufdatum(String kkAblaufdatum) {
		this.kkAblaufdatum = kkAblaufdatum;
	}

	public int getKkPruefnummer() {
		return kkPruefnummer;
	}

	public void setKkPruefnummer(int kkPruefnummer) {
		this.kkPruefnummer = kkPruefnummer;
	}

	public void setLockReason(String lockReason) {
		this.lockReason = lockReason;
	}

	public String getLockReason() {
		return lockReason;
	}

}
