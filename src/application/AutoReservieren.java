package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Auto;

public class AutoReservieren implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

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
	private TextField reservationsKosten;

	@FXML
	private Button handleAutoReservierenButton;

	// initialize für combobox
	public void initialize() {
		// hier findet die berechnung der Strings für die Combobox statt
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
			importFreieAutoListe = (ArrayList) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		// hier werden die freien Autos der bestehenden Liste als Objekte
		// herausgefiltert und
		// der leeren freie Autoliste angefügt
		for (Integer freiesAuto : importFreieAutoListe) {
			emptyFreieAutoListe.add(freiesAuto);

		}
		// hier wird mit einer for Schlaufe durch die importierte FreiesAutoliste
		// iteriert
		for (int i = 0; i < emptyFreieAutoListe.size(); i++) {
			// hier werden die entsprechenden AutoIds für den Combobox String übergeben
			strings.add(Integer.toString(emptyFreieAutoListe.get(i)));

		}

		autoIDBox.setItems(FXCollections.observableArrayList(strings));

	}

	private MainAdmin parent;

	public AutoReservieren() {

	}

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

		// hier wird mit einer for Schlaufe durch die importierte Autoliste iteriert
		for (int i = 0; i < emptyAutoListe.size(); i++) {
			// hier werden die entsprechenden Felder beschrieben
			if (emptyAutoListe.get(i).getId() == Integer.parseInt(autoIDBox.getValue())) {
				// lokale Variablen für Anzeige der Reservationskosten
				double autoKostenProTag;
				double gesamtReservationsKosten;
				autoKostenProTag = emptyAutoListe.get(i).getKostenProTag();
				// hier werden die Anzeigefelder beschrieben
				autoMarkeField.setText(emptyAutoListe.get(i).getMarke());
				autoFarbeField.setText(emptyAutoListe.get(i).getFarbe());
				autoGetriebeField.setText(emptyAutoListe.get(i).getGetriebe());
				autoTreibstoffField.setText(emptyAutoListe.get(i).getTreibstoff());

				// hier startet der Import des von Datums für die Berechnung der Anzahl Tage
				GregorianCalendar kalenderVon = new GregorianCalendar();
				try {
					FileInputStream fis = new FileInputStream("KalenderVon.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					// write object to file
					kalenderVon = (GregorianCalendar) ois.readObject();
					// closing resources
					ois.close();
					fis.close();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}

				// hier startet der Import des bis Datums für die Berechnung der Anzahl Tage
				GregorianCalendar kalenderBis = new GregorianCalendar();
				try {
					FileInputStream fis = new FileInputStream("KalenderBis.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					// write object to file
					kalenderBis = (GregorianCalendar) ois.readObject();
					// closing resources
					ois.close();
					fis.close();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				// hier werden die Anzahl Tage berechnet
				long anzahlTage = (((((kalenderBis.getTimeInMillis() - kalenderVon.getTimeInMillis()) / 1000) / 60)
						/ 60) / 24);

				// hier werden die Kosten berechnet
				gesamtReservationsKosten = anzahlTage * autoKostenProTag;

				// hier wird das Feld Reservationskosten beschrieben
				reservationsKosten.setText(String.valueOf(gesamtReservationsKosten));

			}
		}

	}

	// Methode für den AutoReservierenButton
	public void handleAutoReservierenButton() {

	}
}
