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

	/**
	 * Initialize für die aktuelle Szene mit den AutoIDs der ermittelten freien
	 * Autos Liste in der Combobox
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {

		// Liste für Dropdown
		List<String> strings = new ArrayList<>();
		// String markenString = new String();

		// hier startet der Import der bestehenden FreiesAutoliste
		List<Integer> emptyFreieAutoListe = new ArrayList<Integer>();
		try {
			FileInputStream fis = new FileInputStream("FreieAutosListe.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			emptyFreieAutoListe = (ArrayList<Integer>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
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

	/**
	 * Hier werden die Textfelder der Szene anhand der gewählten AutoID gesetzt
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void zeigeAuto() {
		// hier startet der Import der bestehenden Autoliste
		List<Auto> emptyAutoListe = new ArrayList<Auto>();
		try {
			FileInputStream fis = new FileInputStream("Autoliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			emptyAutoListe = (ArrayList<Auto>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
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

	/**
	 * Durch das Drücken des Auto reservieren Buttons passiert in dieser Methode
	 * folgendes: 1. Die ID der aktuellen Reservation wird von der vorher
	 * generierten Liste importiert 2. Die komplette ReservationsListe wird
	 * importiert 3. Bei der entsprechenden Reservation wird die AutoID aus der
	 * Combobox neu gesetzt 4. Eine MessageBox wird angezeigt und das Fenster
	 * geschlossen
	 */
	@SuppressWarnings("unchecked")
	public void handleAutoZuweisungAuswaehlenButton(ActionEvent event) {
		// hier findet die berechnung der Fahrerfelder statt

		/*
		 * zuerst wird die LoginID hereingeladen (muss über die ArrayListe
		 * geschehen,Integer alleine scheint nicht zu funktionieren)
		 */
		List<Integer> neueAktuelleReservationsIDList = new ArrayList<Integer>();
		try {
			FileInputStream fis = new FileInputStream("aktuelleReservationsID.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			neueAktuelleReservationsIDList = (ArrayList<Integer>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// hier wird die ID gesetzt
		for (int i = 0; i < neueAktuelleReservationsIDList.size(); i++) {
			aktuelleReservationsID = neueAktuelleReservationsIDList.get(i);
		}

		// hier startet der Import des von Datums für die Berechnung der Anzahl Tage

		// hier startet der Import der bestehenden reservationsliste
		List<Reservation> emptyReservationsListe = new ArrayList<Reservation>();
		try {
			FileInputStream fis = new FileInputStream("Reservationsliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			emptyReservationsListe = (ArrayList<Reservation>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
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
