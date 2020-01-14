package model;
import java.util.Date;

public class Reservation {

	private int reservationsID = 0;
	private int autoID; // readonly
	private int KundenNummer; // readonly
	private Date reservationVon;
	private Date reservationBis;
	private boolean needsReparatus = false;
	private boolean istGereinigt = true;
	private double reservationsKosten;
	private double reservationsPreis = 100;
	private double sicherheitsLeistung = 1000.00; // doppelt bei reparaturen und hier

	public boolean checkDate() { // datumVon, datumBis : date
		return needsReparatus; // noch inkorrekt
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

	public Date getReservationVon() {
		return reservationVon;
	}

	public void setReservationVon(Date reservationVon) {
		this.reservationVon = reservationVon;
	}

	public Date getReservationBis() {
		return reservationBis;
	}

	public void setReservationBis(Date reservationBis) {
		this.reservationBis = reservationBis;
	}

	public boolean isNeedsReparatus() {
		return needsReparatus;
	}

	public void setNeedsReparatus(boolean needsReparatus) {
		this.needsReparatus = needsReparatus;
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

}
