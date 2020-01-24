package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;

public class Reservation implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	private int reservationsID = 1;
	private int autoID;
	private int KundenNummer;
	private String fahrerVorname;
	private String fahrerNachname;
	private long fuehrerausweisNummer;
	private GregorianCalendar reservationVon;
	private GregorianCalendar reservationBis;
	private boolean inReparatur = false;
	private boolean istGereinigt = true;
	private double reservationsKosten;
	private double endkosten = 0;
	private double verzugskosten = 0;
	private double sicherheitsLeistung = 1000.00; // doppelt bei reparaturen und hier
	private double reparaturKosten = 0;
	private double sonstigeKosten = 0;
	private int anzahlVerzugsTage = 0;

	// Konstruktor f�r Reservation
	public Reservation(int autoID, int kundenNummer, String fahrerVorname, String fahrerNachname,
			long fuehrerausweisNummer, GregorianCalendar reservationVon, GregorianCalendar reservationBis,
			double reservationsKosten) {
		setAutoID(autoID);
		setKundenNummer(kundenNummer);
		setFahrerVorname(fahrerVorname);
		setFahrerNachname(fahrerNachname);
		setFuehrerausweisNummer(fuehrerausweisNummer);
		setReservationVon(reservationVon);
		setReservationBis(reservationBis);
		setReservationsKosten(reservationsKosten);
		setIstGereinigt(false);
	}

	// getters und setters
	public int getReservationsID() {
		return reservationsID;
	}

	public void setReservationsID(int reservationsID) {
		this.reservationsID = reservationsID;
	}

	public int getAutoID() {
		return autoID;
	}

	public void setAutoID(int autoID) {
		this.autoID = autoID;
	}

	public int getKundenNummer() {
		return KundenNummer;
	}

	public void setKundenNummer(int kundenNummer) {
		KundenNummer = kundenNummer;
	}

	public String getFahrerVorname() {
		return fahrerVorname;
	}

	public void setFahrerVorname(String fahrerVorname) {
		this.fahrerVorname = fahrerVorname;
	}

	public String getFahrerNachname() {
		return fahrerNachname;
	}

	public void setFahrerNachname(String fahrerNachname) {
		this.fahrerNachname = fahrerNachname;
	}

	public long getFuehrerausweisNummer() {
		return fuehrerausweisNummer;
	}

	public void setFuehrerausweisNummer(long fuehrerausweisNummer) {
		this.fuehrerausweisNummer = fuehrerausweisNummer;
	}

	public GregorianCalendar getReservationVon() {
		return reservationVon;
	}

	public void setReservationVon(GregorianCalendar reservationVon) {
		this.reservationVon = reservationVon;
	}

	public GregorianCalendar getReservationBis() {
		return reservationBis;
	}

	public void setReservationBis(GregorianCalendar reservationBis) {
		this.reservationBis = reservationBis;
	}

	public boolean isInReparatus() {
		return inReparatur;
	}

	public void setInReparatus(boolean needsReparatus) {
		this.inReparatur = needsReparatus;
	}

	public boolean isIstGereinigt() {
		return istGereinigt;
	}

	public void setIstGereinigt(boolean istGereinigt) {
		this.istGereinigt = istGereinigt;
	}

	public double getReservationsKosten() {
		return reservationsKosten;
	}

	public void setReservationsKosten(double reservationsKosten) {
		this.reservationsKosten = reservationsKosten;
	}

	public double getSicherheitsLeistung() {
		return sicherheitsLeistung;
	}

	public void setSicherheitsLeistung(double sicherheitsLeistung) {
		this.sicherheitsLeistung = sicherheitsLeistung;
	}

	public double getReparaturKosten() {
		return reparaturKosten;
	}

	public void setReparaturKosten(double reparaturKosten) {
		this.reparaturKosten = reparaturKosten;
	}

	public double getEndkosten() {
		return endkosten;
	}

	public void setEndkosten(double endkosten) {
		this.endkosten = endkosten;
	}

	public double getVerzugskosten() {
		return verzugskosten;
	}

	public void setVerzugskosten(double verzugskosten) {
		this.verzugskosten = verzugskosten;
	}

	public int getAnzahlVerzugsTage() {
		return anzahlVerzugsTage;
	}

	public void setAnzahlVerzugsTage(int anzahlVerzugsTage) {
		this.anzahlVerzugsTage = anzahlVerzugsTage;
	}

	public double getSonstigeKosten() {
		return sonstigeKosten;
	}

	public void setSonstigeKosten(double sonstigeKosten) {
		this.sonstigeKosten = sonstigeKosten;
	}

	// StringtoString f�r Anzeige einer Reservation
	public String toString() {
		return "ReservationsNummer: " + this.getReservationsID() + ", AutoID: " + this.getAutoID() + ", Kundennummer: "
				+ this.getKundenNummer() + ", Reservationskosten: " + this.getReservationsKosten()
				+ ", Reparaturkosten: " + this.getReparaturKosten() + ", Anzahl Verzugstage: "
				+ this.getAnzahlVerzugsTage() + ", Verzugskosten: " + this.getVerzugskosten() + ", sonstige Kosten: "
				+ this.getSonstigeKosten() + ", Endkosten: " + this.endkosten + ", needsReparatur: "
				+ this.isInReparatus() + ", ist Gereinigt: " + this.isIstGereinigt() + ", Von= "
				+ this.reservationVon.getTime() + ", Bis= " + this.reservationBis.getTime();
	}

	// Methode f�r das erfassen der Reservationen
	public void ReservationErfassen(Reservation varReservation) {
		// hier wird eine leere ArrayList erstellt
		List<Reservation> emptyReservationListe = new ArrayList<Reservation>();

		// hier startet der Import der bestehenden Reservationsliste
		List<Reservation> importReservationListe = new ArrayList<Reservation>();
		try {
			FileInputStream fis = new FileInputStream("Reservationsliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			importReservationListe = (ArrayList) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * hier werden die Reservationen der bestehenden Liste als Objekte
		 * herausgefiltert und der leeren Reservationsliste angef�gt und die ID nach
		 * oben gez�hlt
		 */
		for (Reservation existingReservation : importReservationListe) {
			emptyReservationListe.add(existingReservation);
			++this.reservationsID;
		}

		/*
		 * hier wird die neue Reservation der urspr�nglich leeren aber mittlerweile
		 * bef�llten liste angef�gt
		 */
		emptyReservationListe.add(varReservation);

		// hier wird die aktualisierte Reservationsliste wieder herausgeschrieben
		try {
			FileOutputStream fos = new FileOutputStream("Reservationsliste.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(emptyReservationListe);
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
