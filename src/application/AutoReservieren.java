package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import model.Einzelkunde;
import model.Kunde;
import model.Reservation;

public class AutoReservieren implements Serializable {

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
	private TextField reservationsKosten;

	@FXML
	private TextField fahrerVornameField;

	@FXML
	private TextField fahrerNachnameField;

	@FXML
	private TextField fuehrerscheinField;

	@FXML
	private TextField reserviertVon;

	@FXML
	private TextField reserviertBis;

	@FXML
	private Button handleAutoReservierenButton;

	// funktioniert nicht als lokale Variable, deshalb hier
	public int eingeloggterUserID;

	// initialize des Fensters
	public void initialize() {
		// hier findet die berechnung der Strings für die Combobox statt

		// Liste für Dropdown
		List<String> strings = new ArrayList<>();

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
			// hier werden die entsprechenden AutoIds für den Combobox String übergeben
			strings.add(Integer.toString(emptyFreieAutoListe.get(i)));

		}
		// hier werden die IDs der Liste hinzugefügt
		autoIDBox.setItems(FXCollections.observableArrayList(strings));

		/*
		 * hier findet die berechnung der Fahrerfelder statt zuerst wird die LoginID
		 * hereingeladen (muss über die ArrayListe geschehen, Integer alleine scheint
		 * nicht zu funktionieren)
		 */
		List<Integer> eingeloggterUserIDList = new ArrayList<Integer>();
		List<Integer> neueEingeloggterUserIDList = new ArrayList<Integer>();
		try {
			FileInputStream fis = new FileInputStream("EingeloggterUserList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			eingeloggterUserIDList = (ArrayList) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		for (Integer varInt : eingeloggterUserIDList) {
			neueEingeloggterUserIDList.add(varInt);
		}
		for (int i = 0; i < neueEingeloggterUserIDList.size(); i++) {
			eingeloggterUserID = neueEingeloggterUserIDList.get(i);
		}

		// hier wird eine leere ArrayList erstellt
		List<Kunde> emptyKundenListe = new ArrayList<Kunde>();

		// hier startet der Import der bestehenden Kundenliste
		List<Kunde> importKundenListe = new ArrayList<Kunde>();
		try {
			FileInputStream fis = new FileInputStream("Kundenliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			importKundenListe = (ArrayList) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * hier werden die kunden der bestehenden Liste als Objekte herausgefiltert und
		 * der leeren Kundenliste angefügt
		 */
		for (Kunde existingKunde : importKundenListe) {
			emptyKundenListe.add(existingKunde);
		}

		// hier wird mit einer for Schlaufe durch die importierte Kundenliste iteriert
		for (int i = 0; i < emptyKundenListe.size(); i++) {
			/*
			 * wenn username und passwort zusammen auf der Liste und der Kunde nicht
			 * blockiert ist, geht es hier weiter
			 */
			if (emptyKundenListe.get(i).getKundenNummer() == eingeloggterUserID
					&& emptyKundenListe.get(i) instanceof Einzelkunde) {
				fahrerVornameField.setText(((Einzelkunde) emptyKundenListe.get(i)).getVorname());
				fahrerNachnameField.setText(((Einzelkunde) emptyKundenListe.get(i)).getNachname());
				fuehrerscheinField
						.setText(String.valueOf((((Einzelkunde) emptyKundenListe.get(i)).getFuehrerausweisNummer())));
			}

		}

		/*
		 * Abschnitt für Reservationsdaten hier startet der Import des von Datums statt
		 */
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
		// Textfeld wird gesetzt
		reserviertVon.setText(String.valueOf(kalenderVon.getTime()));

		// hier startet der Import des bis Datums
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
		// Textfeld wird gesetzt
		reserviertBis.setText(String.valueOf(kalenderBis.getTime()));

	}

	// Konstruktor
	public AutoReservieren() {

	}

	// Methode für Anzeige der Autos
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
		/*
		 * hier werden die Autos der bestehenden Liste als Objekte herausgefiltert und
		 * der leeren Autoliste angefügt
		 */
		for (Auto existingAuto : importAutoListe) {
			emptyAutoListe.add(existingAuto);
		}

		// hier wird mit einer for Schlaufe durch die importierte Autoliste iteriert
		for (int i = 0; i < emptyAutoListe.size(); i++) {
			// hier werden diverse Werte berechnet, gemäss des gewählten Autos
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

	/** Methode für den AutoReservierenButton */
	public void handleAutoReservierenButton(ActionEvent event) {
		if (fahrerVornameField.getText().isEmpty() || fahrerNachnameField.getText().isEmpty()
				|| fuehrerscheinField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Es müssen alle Fahrerangaben hinterlegt werden");
		} else {
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
			// hier wird die Reservation instanziert und als Objekt weitergegeben
			Reservation varReservation = new Reservation(Integer.parseInt(autoIDBox.getValue()),
					this.eingeloggterUserID, fahrerVornameField.getText(), fahrerNachnameField.getText(),
					Long.parseLong(fuehrerscheinField.getText()), kalenderVon, kalenderBis,
					Double.parseDouble(reservationsKosten.getText()));
			varReservation.ReservationErfassen(varReservation);
			// event, dass fenster geschlossen wird
			((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
		}

	}
}
