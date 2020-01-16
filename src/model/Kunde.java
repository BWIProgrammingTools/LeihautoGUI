package model;
import javax.swing.JOptionPane;

import java.util.Date;

public class Kunde {

	private int kundenNummer = 1;
	private String strasse;
	private String ort;
	private int plz;
	private String land;
	private int alter;
	private int telefonNummer; // string? <- falsch in klassendiagramm? oder wegen abständen?
	private String email;
	private String username;
	private String password;
	boolean gesperrt = false;
	private String kkInhaber;
	private long kkNummer;
	private Date kkAblaufdatum; // nicht string
	private int kkPruefnummer;
//neu dazugefügt
	private String lockReason;

	public Kunde() {
		setUsername("test");
		setPassword("1234");
		setEmail("test@test.test");
		setAlter(99);
	}

	public Kunde(String username, String password) {
		setUsername(username);
		setPassword(password);
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

	public void passwordVergessen(String login, String email, int alter) {
		if (this.email.equals(email) && this.alter == alter) {

			Kunde.infoBox(getPassword());
		} else {
			System.err.println("user nicht gefunden");
			Kunde.infoBox();
			return;
		}

	}
	
	  public static void infoBox(String password)
	    {
	        JOptionPane.showMessageDialog(null, password);
	    }
	  
	  public static void infoBox()
	    {
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

	public void adKunde() {

	}

	public int getKundenNummer() {
		return kundenNummer;
	}

	public void setKundenNummer(int kundenNummer) {
		this.kundenNummer = kundenNummer;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
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

	public int getTelefonNummer() {
		return telefonNummer;
	}

	public void setTelefonNummer(int telefonNummer) {
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

	public Date getKkAblaufdatum() {
		return kkAblaufdatum;
	}

	public void setKkAblaufdatum(Date kkAblaufdatum) {
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
