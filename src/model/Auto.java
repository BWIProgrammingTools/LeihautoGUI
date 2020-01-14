package model;

public class Auto {

	private int id;
	private Marke marke;
	private Treibstoff treibstoff;
	private boolean automatik = false;
	private String farbe;
	private boolean blockiert = false;

	public void lock() {
		blockiert = true;
	}

	public void unlock() {
		blockiert = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Marke getMarke() {
		return marke;
	}

	public void setMarke(Marke marke) {
		this.marke = marke;
	}

	public Treibstoff getTreibstoff() {
		return treibstoff;
	}

	public void setTreibstoff(Treibstoff treibstoff) {
		this.treibstoff = treibstoff;
	}

	public boolean isAutomatik() {
		return automatik;
	}

	public void setAutomatik(boolean automatik) {
		this.automatik = automatik;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	public boolean isBlockiert() {
		return blockiert;
	}

}
