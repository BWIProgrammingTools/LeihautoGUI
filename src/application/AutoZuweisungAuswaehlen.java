package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Auto;
import model.Reservation;

public class AutoZuweisungAuswaehlen implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;
	// Felder im GUI
	@FXML
	private ComboBox<String> autoIDBox;

	@FXML
	private TextField autoMarkeField;

	@FXML
	private TextField autoFarbeField;

	@FXML
	private TextField autoGetriebeField;

	@FXML
	private TextField autoTreibstoffField;

	@FXML
	private Button autoZuweisungAuswaehlenButton;

	// funktioniert nicht als lokale Variable, deshalb hier
	public int aktuelleReservationsID;

	// initialize des Fensters
	@SuppressWarnings("unchecked")
	public void initialize() {

		// Liste für Dropdown
		List<String> strings = new ArrayList<>();
		// String markenString = new String();

		// hier wird eine leere ArrayList erstellt
		List<Integer> emptyFreieAutoListe = new ArrayList<Integer>();

		// hier startet der Import der bestehenden FreiesAutoliste
		List<Integer> importFreieAutoListe = new ArrayList<Integer>();
		try {
			FileInputStream fis = new FileInputStream("FreieAutosListe.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			importFreieAutoListe = (ArrayList<Integer>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * hier werden die freien Autos der bestehenden Liste als Objekte
		 * herausgefiltert und der leeren freie Autoliste angefügt
		 */
		for (Integer freiesAuto : importFreieAutoListe) {
			emptyFreieAutoListe.add(freiesAuto);

		}
		/*
		 * hier wird mit einer for Schlaufe durch die importierte FreiesAutoliste
		 * iteriert
		 */
		for (int i = 0; i < emptyFreieAutoListe.size(); i++) {
			/*
			 * hier werden die IDs der ComboBox hinzugefügt
			 */
			strings.add(Integer.toString(emptyFreieAutoListe.get(i)));

		}
		// hier wird die IDs der Liste übergeben
		autoIDBox.setItems(FXCollections.observableArrayList(strings));

	}

	// Konsturktor
	public AutoZuweisungAuswaehlen() {

	}

	// Methode für das Beschreiben der Felder
	@SuppressWarnings("unchecked")
	@FXML
	public void zeigeAuto() {

		// hier wird eine leere ArrayList erstellt
		List<Auto> emptyAutoListe = new ArrayList<Auto>();

		// hier startet der Import der bestehenden Autoliste
		List<Auto> importAutoListe = new ArrayList<Auto>();
		try {
			FileInputStream fis = new FileInputStream("Autoliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			importAutoListe = (ArrayList<Auto>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * hier werden die Autos der bestehenden Liste als Objekte herausgefiltert und
		 * der leeren Autoliste angefügt
		 */
		for (Auto existingAuto : importAutoListe) {
			emptyAutoListe.add(existingAuto);
		}

		// hier wird mit einer for Schlaufe durch die importierte Autoliste iteriert
		for (int i = 0; i < emptyAutoListe.size(); i++) {
			// hier werden die entsprechenden Felder beschrieben
			if (emptyAutoListe.get(i).getId() == Integer.parseInt(autoIDBox.getValue())) {
				autoMarkeField.setText(emptyAutoListe.get(i).getMarke());
				autoFarbeField.setText(emptyAutoListe.get(i).getFarbe());
				autoGetriebeField.setText(emptyAutoListe.get(i).getGetriebe());
				autoTreibstoffField.setText(emptyAutoListe.get(i).getTreibstoff());
			}
		}

	}

	// Methode für den AutoReservierenButton
	@SuppressWarnings("unchecked")
	public void handleAutoZuweisungAuswaehlenButton(ActionEvent event) {
		// hier findet die berechnung der Fahrerfelder statt

		/*
		 * zuerst wird die LoginID hereingeladen (muss über die ArrayListe
		 * geschehen,Integer alleine scheint nicht zu funktionieren)
		 */
		List<Integer> aktuelleReservationsIDList = new ArrayList<Integer>();
		List<Integer> neueAktuelleReservationsIDList = new ArrayList<Integer>();
		try {
			FileInputStream fis = new FileInputStream("aktuelleReservationsID.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			aktuelleReservationsIDList = (ArrayList<Integer>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		for (Integer varInt : aktuelleReservationsIDList) {
			neueAktuelleReservationsIDList.add(varInt);
		}
		// hier wird die ID gesetzt
		for (int i = 0; i < neueAktuelleReservationsIDList.size(); i++) {
			aktuelleReservationsID = neueAktuelleReservationsIDList.get(i);
		}

		// hier startet der Import des von Datums für die Berechnung der Anzahl Tage

		// hier wird eine leere ArrayList erstellt
		List<Reservation> emptyReservationsListe = new ArrayList<Reservation>();

		// hier startet der Import der bestehenden reservationsliste
		List<Reservation> importReservationsListe = new ArrayList<Reservation>();
		try {
			FileInputStream fis = new FileInputStream("Reservationsliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			importReservationsListe = (ArrayList<Reservation>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * hier werden die Reservationen der bestehenden Liste als Objekte
		 * herausgefiltert und der leeren Reservationsliste angefügt
		 */
		for (Reservation existingReservation : importReservationsListe) {
			emptyReservationsListe.add(existingReservation);
		}

		/*
		 * hier wird mit einer for Schlaufe durch die importierte Reservationsliste
		 * iteriert
		 */
		for (int i = 0; i < emptyReservationsListe.size(); i++) {
			if (emptyReservationsListe.get(i).getReservationsID() == aktuelleReservationsID) {
				emptyReservationsListe.get(i).setAutoID(Integer.parseInt(autoIDBox.getValue()));

				// Message vor dem Schliessen
				JOptionPane.showMessageDialog(null, "Das Auto mit der ID " + autoIDBox.getValue()
						+ " wurde der Reservation mit der ID " + aktuelleReservationsID + " neu zugewiesen");
			}

			// hier wird die aktualisierte Reservationsliste wieder herausgeschrieben
			try {
				FileOutputStream fos = new FileOutputStream("Reservationsliste.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				// write object to file
				oos.writeObject(emptyReservationsListe);
				// closing resources
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// event dass Fenster geschlossen wird
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
	}
}
