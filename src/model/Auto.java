package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Auto implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	private int id = 1;
	private String marke;
	private String treibstoff;
	private String getriebe;
	// automatik wird momentan nicht benötigt, allenfalls zu einem späteren
	// zeitpunkt ersetzen
	private boolean automatik = false;
	private String farbe;
	private boolean blockiert;

	// Konstruktor für Autos
	public Auto(String marke, String treibstoff, String getriebe, String farbe) {
		setMarke(marke);
		setTreibstoff(treibstoff);
		setGetriebe(getriebe);
		setFarbe(farbe);
	}

	// String to String Methode für Objektausgabe
	public String toString() {
		return "Auto:: ID= " + this.getId() + " marke= " + this.getMarke() + " Farbe= " + this.getFarbe()
				+ " Getriebe: " + this.getGetriebe() + " Treibstoffart: " + this.getTreibstoff();
	}
	
	// Methode für Autoregistration
		public void autoErfassen(Auto varAuto) {
			// hier wird eine leere ArrayList erstellt
			List<Auto> emptyAutoListe = new ArrayList<Auto>();

			// hier startet der Import der bestehenden Autoliste
			List<Auto> importAutoListe = new ArrayList<Auto>();
			try {
				FileInputStream fis = new FileInputStream("Autoliste.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				// write object to file
				importAutoListe = (ArrayList) ois.readObject();
				System.out.println("Import Done");
				// closing resources
				ois.close();
				fis.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			// hier werden die Autos der bestehenden Liste als Objekte herausgefiltert und
			// der leeren Autoliste angefügt
			for (Auto existingAuto : importAutoListe) {
				emptyAutoListe.add(existingAuto);
				System.out.println(existingAuto);
				++this.id;
			}

			// hier wird das neue Auto der ursprünglich leeren aber mittlerweile befüllten
			// liste angefügt
			emptyAutoListe.add(varAuto);
			System.out.println(varAuto);

			// hier wird die aktualisierte Autoliste wieder herausgeschrieben
			try {
				FileOutputStream fos = new FileOutputStream("Autoliste.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				// write object to file
				oos.writeObject(emptyAutoListe);
				System.out.println("Export Done");
				// closing resources
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	public String getGetriebe() {
		return getriebe;
	}

	public void setGetriebe(String getriebe) {
		this.getriebe = getriebe;
	}

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

	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public String getTreibstoff() {
		return treibstoff;
	}

	public void setTreibstoff(String treibstoff) {
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

	public void setBlockiert(boolean blockiert) {
		this.blockiert = blockiert;
	}

}
