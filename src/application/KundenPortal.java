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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Auto;
import model.Reservation;

public class KundenPortal implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	@FXML
	private DatePicker reservierenVon;

	@FXML
	private DatePicker reservierenBis;

	@FXML
	private Button autoReservieren;

	@FXML
	private TextField testfeld;

	// Integer arrayList für reservierte AutoIDs
	List<Integer> reservierteIDs = new ArrayList<Integer>();

	// Integer arrayList für alle AutoIDs
	List<Integer> alleAutoIDs = new ArrayList<Integer>();


	private MainAdmin parent;

	public KundenPortal() {

	}

	public void handleFreieAutosButton() throws IOException {

		// lokale Variable für DatePickerVon
		int yearVon = reservierenVon.getValue().getYear();
		int monthVon = reservierenVon.getValue().getMonthValue();
		int dayVon = reservierenVon.getValue().getDayOfMonth();
		GregorianCalendar kalenderVon = new GregorianCalendar(yearVon, monthVon, dayVon);
		//hier wird das von Datum in eine Liste geschrieben
		try {
			FileOutputStream fos = new FileOutputStream("KalenderVon.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(kalenderVon);
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// lokale Variable für DatePickerBis
		int yearBis = reservierenBis.getValue().getYear();
		int monthBis = reservierenBis.getValue().getMonthValue();
		int dayBis = reservierenBis.getValue().getDayOfMonth();
		GregorianCalendar kalenderBis = new GregorianCalendar(yearBis, monthBis, dayBis);
		System.out.println(kalenderVon.getTime());
		System.out.println(kalenderBis.getTime());
		//hier wird das bis Datum in eine Liste geschrieben
		try {
			FileOutputStream fos = new FileOutputStream("KalenderBis.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(kalenderBis);
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		// der leeren Reservationsliste angefügt
		for (Reservation existingReservation : importReservationsListe) {
			emptyReservationsListe.add(existingReservation);
		}

		// hier werden diejenigen Autos herausgesucht, welche zum angegebenen Zeitpunkt
		// NICHT verfügbar sind
		for (int i = 0; i < emptyReservationsListe.size(); i++) {

			// dieser Vergleich sollte die belegten Autos der Liste im angegebenen Zeitaum
			// ausgeben
			if (kalenderVon.before(emptyReservationsListe.get(i).getReservationBis())
					&& kalenderBis.after(emptyReservationsListe.get(i).getReservationVon())) {

				// hier wird die entsprechende AutoID, welche reserviert ist der ArrayListe
				// reservierteID geadded
				reservierteIDs.add(emptyReservationsListe.get(i).getAutoID());

			}
		}

		// hier wird eine leere ArrayList erstellt
		List<Auto> emptyAutoListe = new ArrayList<Auto>();

		// hier startet der Import der bestehenden Autoliste
		List<Auto> importAutoListe = new ArrayList<Auto>();
		try {
			FileInputStream fis = new FileInputStream("Autoliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			importAutoListe = (ArrayList) ois.readObject();
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
		}

		// hier werden alle AutoIDs in die alleAutoIDs Arrayliste geladen
		for (int ii = 0; ii < emptyAutoListe.size(); ii++) {
			alleAutoIDs.add(emptyAutoListe.get(ii).getId());
		}

		// hier ziehen wir die IDs der reservierten Autos von allen IDs ab
		alleAutoIDs.removeAll(reservierteIDs);
		System.out.println(alleAutoIDs);
		
		// hier wird die Liste mit den freien Autos herausgeschrieben
		try {
			FileOutputStream fos = new FileOutputStream("FreieAutosListe.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(alleAutoIDs);
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//hier wird der neue Dialog für die eigentliche Reservation geöffnet
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoReservieren.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Auto reservieren");
		stage.setScene(new Scene(root, 700, 700));
		stage.showAndWait();
		
	}
	
}
