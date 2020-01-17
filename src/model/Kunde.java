package model;

import javax.swing.JOptionPane;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Kunde implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	private int kundenNummer = 0;
	private String strasseUndNummer;
	private String ort;
	private int plz;
	private String land;
	private int alter;
	private String telefonNummer; // string? <- falsch in klassendiagramm? oder wegen abstÃ¤nden?
	private String email;
	private String username;
	private String password;
	private boolean gesperrt = false;
	private String kkInhaber;
	private long kkNummer;
	private String kkAblaufdatum; // nicht string
	private int kkPruefnummer;
//neu dazugefÃ¼gt
	private String lockReason;

	// Konstruktor für Tests
	public Kunde() {
		setUsername("test");
		setPassword("1234");
		setEmail("test@test.test");
		setAlter(99);
	}

	// Konstruktor für Tests
	public Kunde(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	// Konstruktor für Registration
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
		setGesperrt(false);
		setKkInhaber(kkInhaber);
		setKkNummer(kkNummer);
		setKkAblaufdatum(kkAblaufdatum); // nicht string
		setKkPruefnummer(kkPruefnummer);
		setLockReason("");
	}

	public void lockKunde(String lockReason) {
		this.lockReason = lockReason;
		setGesperrt(true);
	}

	public void unlockKunde() {
		setLockReason("");
		setGesperrt(false);
	}

	public String passwordAendern(String oldPassword, String password) { // kontrolle bzw login

		if (password == oldPassword) {
			this.password = password;
			return "";
		} else {
			System.err.println("falsches password");
			return "falsches password";
		}
	}

	public void passwordVergessen(String username, String email, int alter) {
		if (this.email.equals(email) && this.alter == alter) {

			Kunde.infoBox(getPassword());
		} else {
			System.err.println("user nicht gefunden");
			Kunde.infoBox();
			return;
		}

	}

	public static void infoBox(String password) {
		JOptionPane.showMessageDialog(null, password);
	}

	public static void infoBox() {
		JOptionPane.showMessageDialog(null, "no password for you, idiot!");
	}

// || oder ((ab == ab && ab == 1) || 1==1)
	public void passwordZurueckSetzen(int kundenNummer, String email, int alter) {
		if (this.email == email && this.alter == alter) {
			setPassword("1234");
			System.out.println("Wir haben Ihr Password auf 1234 gesetzt");

		} else {
			System.err.println("user nicht gefunden");
		}
	}

	public Boolean login(String password) {

		if (this.password.equals(password)) {
			System.out.println("eingeloggt"); // brauchen wir ein logged in status?
			return true;
		}
		return false;
	}

	public void addKunde() {

	}

	// Methode für Kundenregistration
	public void registration(Kunde varKunde) {
		// hier wird eine leere ArrayList erstellt
		List<Kunde> emptyKundenListe = new ArrayList<Kunde>();

		// hier startet der Import der bestehenden Kundenliste
		List<Kunde> importKundenListe = new ArrayList<Kunde>();
		try {
			FileInputStream fis = new FileInputStream("Kundenliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			importKundenListe = (ArrayList) ois.readObject();
			System.out.println("Done");
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		// hier werden die kunden der bestehenden Liste als Objekte herausgefiltert und
		// der leeren Kundenliste angefügt
		for (Kunde existingKunde : importKundenListe) {
			emptyKundenListe.add(existingKunde);
			System.out.println(existingKunde);
			++this.kundenNummer;
		}
		// hier wird ein neuer Kunde instanziert
		// varKundenTest = new KundenTest();

		// hier wird der neue Kunde der ursprünglich leeren aber mittlerweile befüllten
		// liste angefügt
		emptyKundenListe.add(varKunde);
		System.out.println(varKunde);

		// hier wird die aktualisierte kundenliste wieder herausgeschrieben
		try {
			FileOutputStream fos = new FileOutputStream("Kundenliste.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(emptyKundenListe);
			System.out.println("Done");
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// String to String Methode
	public String toString() {
		return "Kunde:: Nummer=" + this.kundenNummer + " Email=" + this.email;
	}

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
		System.out.println(password);
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
