package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Reservation;

public class AutoReservieren implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	@FXML
	private DatePicker reservierenVon;

	@FXML
	private DatePicker reservierenBis;

	@FXML
	private TextField reservationVon;

	@FXML
	private TextField reservationBis;

	@FXML
	private Button autoReservieren;

	@FXML
	private TextField testfeld;

	private MainAdmin parent;

	public AutoReservieren() {

	}

	public void handleAutoReservierenButton() {
		// hier wird eine leere ArrayList erstellt
		List<Reservation> emptyReservationsListe = new ArrayList<Reservation>();

		// hier startet der Import der bestehenden Kundenliste
		List<Reservation> importReservationsListe = new ArrayList<Reservation>();
		try {
			FileInputStream fis = new FileInputStream("Reservationsliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			importReservationsListe = (ArrayList) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		// hier werden die kunden der bestehenden Liste als Objekte herausgefiltert und
		// der leeren Kundenliste angefügt
		for (Reservation existingReservation : importReservationsListe) {
			emptyReservationsListe.add(existingReservation);
		}

		// hier werden diejenigen Autos herausgesucht, welche zum angegebenen Zeitpunkt
		// NICHT verfügbar sind
		for (int i = 0; i < emptyReservationsListe.size(); i++) {
			// dieser Vergleich sollte die freien Autos der Liste im angegebenen Zeitaum printen
//			if (emptyReservationsListe.get(i).getReservationVon().after(reservierenVon.getValue())
//					&& emptyReservationsListe.get(i).getReservationVon().after(reservierenVon.getValue())
//					|| emptyReservationsListe.get(i).getReservationBis().before(reservierenVon.getValue())
//							&& emptyReservationsListe.get(i).getReservationBis().before(reservierenBis.getValue())) {
			//dieser Vergleich sollte die Reservierten Autos der Liste im angegebenen Zeitraum printen
//			if (emptyReservationsListe.get(i).getReservationVon().before(reservierenVon.getValue())
//					&& emptyReservationsListe.get(i).getReservationBis().after(reservierenVon.getValue())
//					|| (emptyReservationsListe.get(i).getReservationVon().before(reservierenBis.getValue())
//							&& emptyReservationsListe.get(i).getReservationBis().after(reservierenBis.getValue()))) {
			//Test ob überhaupt funktioniert
			if (emptyReservationsListe.get(i).getReservationsID() == i+1) {
				System.out.println(emptyReservationsListe.get(i).getAutoID());
			} else {
				System.out.println("didnt work");
			}
		}

//		//hier wird die aktualisierte Reservationsliste wieder herausgeschrieben
//		try {
//			FileOutputStream fos = new FileOutputStream("Reservationsliste.ser");
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			// write object to file
//			oos.writeObject(emptyReservationsListe);
//			System.out.println("Done");
//			// closing resources
//			oos.close();
//			fos.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}
