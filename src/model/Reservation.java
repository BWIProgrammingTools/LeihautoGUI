package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Reservation implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	private int reservationsID = 1; // wird momentan manuell gefüllt bis reservationsliste über kundenUI befüllt
									// wird
	private int autoID; // readonly
	private int KundenNummer; // readonly
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

	// Konstruktor für Reservation
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

	// Methode für Reservation erfassen

	public boolean checkDate() { // datumVon, datumBis : date
		return inReparatur; // noch inkorrekt
	}

	public void addReservation() {

	}

	public void deleteReservation() {

	}

	public void auftragsBestaetigung() { // wirklich void?

	}

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
		endkosten = endkosten;
	}

	public double getVerzugskosten() {
		return verzugskosten;
	}

	public void setVerzugskosten(double verzugskosten) {
		this.verzugskosten = verzugskosten;
	}

	// StringtoString für Anzeige einer Reservation
	public String toString() {
		return "Reservation:: ReservationsNummer=" + this.getReservationsID() + " AutoID= " + this.getAutoID()
				+ " Kundennummer= " + this.getKundenNummer() + " needsReparatur= " + this.isInReparatus()
				+ " ist Gereinigt= " + this.isIstGereinigt() + " Reservationskosten= " + this.getReservationsKosten()
				+ " Verzugskosten= " + this.verzugskosten + " Endkosten= " + this.endkosten + " Von= "
				+ this.reservationVon.getTime() + " Bis= " + this.reservationBis.getTime();
	}

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
			System.out.println("Import Done");
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		// hier werden die Reservationen der bestehenden Liste als Objekte
		// herausgefiltert und
		// der leeren Reservationsliste angefügt
		for (Reservation existingReservation : importReservationListe) {
			emptyReservationListe.add(existingReservation);
			System.out.println(existingReservation);
			++this.reservationsID;
		}

		// hier wird die neue Reservation der ursprünglich leeren aber mittlerweile
		// befüllten
		// liste angefügt
		emptyReservationListe.add(varReservation);
		System.out.println(varReservation);

		// hier wird die aktualisierte Reservationsliste wieder herausgeschrieben
		try {
			FileOutputStream fos = new FileOutputStream("Reservationsliste.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(emptyReservationListe);
			System.out.println("Export Done");
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
