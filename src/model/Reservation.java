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

public class Reservation implements Serializable{
	
	private static final long serialVersionUID = -299482035708790407L;

	private int reservationsID;	//wird momentan manuell gefüllt bis reservationsliste über kundenUI befüllt wird
	private int autoID; // readonly
	private int KundenNummer; // readonly
	private GregorianCalendar reservationVon;
	private GregorianCalendar reservationBis;
	private boolean inReparatur = false;
	private boolean istGereinigt = true;
	private double reservationsKosten;
	private double reservationsPreis = 100;
	private double sicherheitsLeistung = 1000.00; // doppelt bei reparaturen und hier
	private double reparaturKosten = 0;

	

	public boolean checkDate() { // datumVon, datumBis : date
		return inReparatur; // noch inkorrekt
	}

	public void addReservation() {

	}

	public void deleteReservation() {

	}

	public void auftragsBestaetigung() { // wirklich void?

	}

	public double reservationsKostenBerechnung() {
		return reservationsPreis;
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

	public double getReservationsPreis() {
		return reservationsPreis;
	}

	public void setReservationsPreis(double reservationsPreis) {
		this.reservationsPreis = reservationsPreis;
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
	
	// StringtoString für Anzeige einer Reservation
		public String toString() {
			return "Reservation:: ReservationsNummer=" + this.getReservationsID() + " AutoID= " + this.getAutoID()
					+ " Kundennummer= " + this.getKundenNummer() + " needsReparatur= " + this.isInReparatus() + " ist Gereinigt= " + this.isIstGereinigt() + " Reserviert von=" + " Reservationskosten= "
					+ this.getReservationsKosten() + " Von= " + this.reservationVon.getTime() + " Bis= " + this.reservationBis.getTime();
		}
		
		//temporäres main, bis reservationsliste über kundenUI befüllt wird
		public static void main(String[] args) {

			List<Reservation> reservationsListe = new ArrayList<Reservation>();

			List<Reservation> importReservationsListe = new ArrayList<Reservation>();
			try {
				FileInputStream fis = new FileInputStream("Reservationsliste.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				// write object to file
				importReservationsListe = (ArrayList) ois.readObject();
				System.out.println("Done");
				// closing resources
				ois.close();
				fis.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			for (Reservation reservation : importReservationsListe) {
				reservationsListe.add(reservation);
				System.out.println(reservation);
			}

			Reservation varReservation = new Reservation();
			varReservation.setReservationsID(2);
			varReservation.setAutoID(2);
			varReservation.setKundenNummer(2);
			varReservation.reservationVon = new GregorianCalendar(2020,1,4);
			varReservation.reservationBis = new GregorianCalendar(2020,1,6);
			varReservation.reservationsKosten = 200;
			varReservation.setIstGereinigt(false);

			reservationsListe.add(varReservation);
			System.out.println(varReservation);

			try {
				FileOutputStream fos = new FileOutputStream("Reservationsliste.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				// write object to file
				oos.writeObject(reservationsListe);
				System.out.println("Done");
				// closing resources
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

}
