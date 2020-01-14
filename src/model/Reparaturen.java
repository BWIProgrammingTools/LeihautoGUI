package model;
public class Reparaturen {

	private int reparaturID = 0;
	private int audoID; // verbindung
	private int reservationsID;// verbindung
	private double reparaturKosten;
	private Garage garage;
	private Reparaturtyp reparaturTyp;
	private boolean istGereinigt = true;
	private double reservationsKosten;
	private double reservationsPreis = 100.00;
	private double sicherheitsLeistung = 1000.00; //doppelt bei reservation und hier

	public double reparaturKostenBerechnung() { // berechnung einbauen
		return reparaturKosten;
	}

	public void addReparatur() { // evt zu konstruktor ändern?

	}
	
	public void reparaturAbschluss() {
		
	}

	public int getReparaturID() {
		return reparaturID;
	}

	public void setReparaturID(int reparaturID) {
		this.reparaturID = reparaturID;
	}

	public int getAudoID() {
		return audoID;
	}

	public void setAudoID(int audoID) {
		this.audoID = audoID;
	}

	public int getReservationsID() {
		return reservationsID;
	}

	public void setReservationsID(int reservationsID) {
		this.reservationsID = reservationsID;
	}

	public double getReparaturKosten() {
		return reparaturKosten;
	}

	public void setReparaturKosten(double reparaturKosten) {
		this.reparaturKosten = reparaturKosten;
	}

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	public Reparaturtyp getReparaturTyp() {
		return reparaturTyp;
	}

	public void setReparaturTyp(Reparaturtyp reparaturTyp) {
		this.reparaturTyp = reparaturTyp;
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
