package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Auto;
import model.Einzelkunde;
import model.Firmenkunde;
import model.Kunde;
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
	private TextField strasseUndNummer;
	@FXML
	private TextField ort;
	@FXML
	private TextField plz;
	@FXML
	private TextField land;
	@FXML
	private TextField alter;
	@FXML
	private TextField telefonNummer;
	@FXML
	private TextField email;
	@FXML
	private TextField passwort;
	@FXML
	private TextField kkNummer;
	@FXML
	private TextField kkInhaber;
	@FXML
	private TextField kkAblaufdatum;
	@FXML
	private TextField kkPruefnummer;
	@FXML
	private Button kundendatenAendern;

	// Integer arrayList für reservierte AutoIDs
	List<Integer> reservierteIDs = new ArrayList<Integer>();

	// Integer arrayList für alle AutoIDs
	List<Integer> alleAutoIDs = new ArrayList<Integer>();

	// Integer arrayList für alle deaktivierten AutoIDs
	List<Integer> alleDeaktiviertenAutoIDs = new ArrayList<Integer>();

	// Integer arrayList für alle blockierte AutoIDs
	List<Integer> alleBlockiertenAutoIDs = new ArrayList<Integer>();

	// funktioniert nicht als lokale Variable, deshalb hier
	public int eingeloggterUserID;

	public KundenPortal() {

	}

	@SuppressWarnings("unchecked")
	public void initialize() {
		// hier findet die berechnung der Fahrerfelder statt

		/*
		 * zuerst wird die LoginID hereingeladen (muss über die ArrayListe geschehen,
		 * Integer alleine scheint nicht zu funktionieren
		 */
		List<Integer> neueEingeloggterUserIDList = new ArrayList<Integer>();
		try {
			FileInputStream fis = new FileInputStream("EingeloggterUserList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			neueEingeloggterUserIDList = (ArrayList<Integer>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < neueEingeloggterUserIDList.size(); i++) {
			eingeloggterUserID = neueEingeloggterUserIDList.get(i);
		}

		// hier wird die Kundenliste reingeladen

		// hier startet der Import der bestehenden Kundenliste
		List<Kunde> emptyKundenListe = new ArrayList<Kunde>();
		try {
			FileInputStream fis = new FileInputStream("Kundenliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			emptyKundenListe = (ArrayList<Kunde>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// hier wird mit einer for Schlaufe durch die importierte Kundenliste iteriert
		for (int i = 0; i < emptyKundenListe.size(); i++) {
			/*
			 * hier werden die aktuellen Kundendaten in die entsprechenden Textfelder
			 * übertragen
			 */
			if (emptyKundenListe.get(i).getKundenNummer() == eingeloggterUserID) {
				strasseUndNummer.setText(emptyKundenListe.get(i).getStrasseUndNummer());
				ort.setText(emptyKundenListe.get(i).getOrt());
				plz.setText(String.valueOf(emptyKundenListe.get(i).getPlz()));
				land.setText(emptyKundenListe.get(i).getLand());
				alter.setText(String.valueOf(emptyKundenListe.get(i).getAlter()));
				telefonNummer.setText(emptyKundenListe.get(i).getTelefonNummer());
				email.setText(emptyKundenListe.get(i).getEmail());
				passwort.setText(emptyKundenListe.get(i).getPassword());
				kkNummer.setText(String.valueOf(emptyKundenListe.get(i).getKkNummer()));
				kkInhaber.setText(emptyKundenListe.get(i).getKkInhaber());
				kkPruefnummer.setText(String.valueOf(emptyKundenListe.get(i).getKkPruefnummer()));
				kkAblaufdatum.setText(emptyKundenListe.get(i).getKkAblaufdatum());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void handleFreieAutosButton() throws IOException {
		/*
		 * zuerst werden die ArrayListen gecleared, damit bei doppelter Ausführung die
		 * Liste nicht mit doppelten Elementen befüllt wird
		 */
		reservierteIDs.clear();
		alleAutoIDs.clear();

		// lokale Variable für DatePickerVon
		int yearVon = reservierenVon.getValue().getYear();
		int monthVon = (reservierenVon.getValue().getMonthValue()) - 1;
		int dayVon = reservierenVon.getValue().getDayOfMonth();
		GregorianCalendar kalenderVon = new GregorianCalendar(yearVon, monthVon, dayVon);
		// hier wird das von Datum in eine Liste geschrieben
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
		int monthBis = (reservierenBis.getValue().getMonthValue()) - 1;
		int dayBis = reservierenBis.getValue().getDayOfMonth();
		GregorianCalendar kalenderBis = new GregorianCalendar(yearBis, monthBis, dayBis);

		// hier wird das bis Datum in eine Liste geschrieben
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
		if (kalenderVon.before(kalenderBis)) {
			// hier startet der Import der bestehenden Kundenliste
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
			 * hier werden diejenigen Autos herausgesucht, welche zum angegebenen Zeitpunkt
			 * NICHT verfügbar sind
			 */
			for (int i = 0; i < emptyReservationsListe.size(); i++) {

				/*
				 * dieser Vergleich sollte die belegten Autos der Liste im angegebenen Zeitaum
				 * ausgeben
				 */
				if (kalenderVon.before(emptyReservationsListe.get(i).getReservationBis())
						&& kalenderBis.after(emptyReservationsListe.get(i).getReservationVon())
						&& emptyReservationsListe.get(i).isIstGereinigt() == false) {

					/*
					 * hier wird die entsprechende AutoID, welche reserviert ist der ArrayListe
					 * reservierteID geadded
					 */
					reservierteIDs.add(emptyReservationsListe.get(i).getAutoID());

				}
			}

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

			// hier werden alle AutoIDs der alleAutoIDs Arrayliste hinzugefüt
			for (int ii = 0; ii < emptyAutoListe.size(); ii++) {
				alleAutoIDs.add(emptyAutoListe.get(ii).getId());
			}

			/*
			 * hier werden alle deaktivierten AutoIDs in die deaktivierteAutoIDs Arrayliste
			 * geladen
			 */
			for (int iii = 0; iii < emptyAutoListe.size(); iii++) {
				// if, damit deaktivierte und in reparatur autos nicht angezeigt werden
				if (emptyAutoListe.get(iii).isDeaktiviert()) {
					alleDeaktiviertenAutoIDs.add(emptyAutoListe.get(iii).getId());
				}
			}
			/*
			 * hier werden alle blockierten AutoIDs in die blockiertealleAutoIDs Arrayliste
			 * geladen
			 */
			for (int iiii = 0; iiii < emptyAutoListe.size(); iiii++) {
				// if, damit deaktivierte und in reparatur autos nicht angezeigt werden
				if (emptyAutoListe.get(iiii).isBlockiert()) {
					alleBlockiertenAutoIDs.add(emptyAutoListe.get(iiii).getId());
				}
			}

			/*
			 * hier ziehen wir die IDs der reservierten,deaktiverten und blockierten Autos
			 * von allen IDs ab
			 */
			alleAutoIDs.removeAll(reservierteIDs);
			alleAutoIDs.removeAll(alleDeaktiviertenAutoIDs);
			alleAutoIDs.removeAll(alleBlockiertenAutoIDs);

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

			// hier wird der neue Dialog für die eigentliche Reservation geöffnet
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoReservieren.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOpacity(1);
			stage.setTitle("Auto reservieren");
			stage.setScene(new Scene(root, 900, 700));
			stage.showAndWait();
		} else {
			JOptionPane.showMessageDialog(null, "Das Von-Datum liegt nach dem Bis-Datum");
		}

	}

	@SuppressWarnings("unchecked")
	public void handleKundendatenAendernButton() {
		// hier startet der Import der bestehenden Kundenliste
		List<Kunde> emptyKundenListe = new ArrayList<Kunde>();
		try {
			FileInputStream fis = new FileInputStream("Kundenliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			emptyKundenListe = (ArrayList<Kunde>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// hier wird mit einer for Schlaufe durch die importierte Kundenliste iteriert
		for (int i = 0; i < emptyKundenListe.size(); i++) {
			/*
			 * hier werden die aktuellen Kundendaten in die entsprechenden Textfelder
			 * übertragen
			 */
			if (emptyKundenListe.get(i).getKundenNummer() == eingeloggterUserID) {
				if (strasseUndNummer.getText().isEmpty() || ort.getText().isEmpty() || plz.getText().isEmpty()
						|| land.getText().isEmpty() || alter.getText().isEmpty() || telefonNummer.getText().isEmpty()
						|| email.getText().isEmpty() || passwort.getText().isEmpty() || kkInhaber.getText().isEmpty()
						|| kkNummer.getText().isEmpty() || kkAblaufdatum.getText().isEmpty()
						|| kkPruefnummer.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Es müssen alle Felder ausgefüllt werden");
				} else
				// hier werden die Werte wieder unter dem entsprechenden Kunden gespeichert
				if (Integer.parseInt(alter.getText()) > 17 && emptyKundenListe.get(i) instanceof Einzelkunde) {
					emptyKundenListe.get(i).setAlter(Integer.parseInt(alter.getText()));
					emptyKundenListe.get(i).setStrasseUndNummer(strasseUndNummer.getText());
					emptyKundenListe.get(i).setPlz(Integer.parseInt(plz.getText()));
					emptyKundenListe.get(i).setOrt(ort.getText());
					emptyKundenListe.get(i).setLand(land.getText());
					emptyKundenListe.get(i).setTelefonNummer(telefonNummer.getText());
					emptyKundenListe.get(i).setEmail(email.getText());
					emptyKundenListe.get(i).setPassword(passwort.getText());
					emptyKundenListe.get(i).setKkNummer(Long.parseLong(kkNummer.getText()));
					emptyKundenListe.get(i).setKkInhaber(kkInhaber.getText());
					emptyKundenListe.get(i).setKkAblaufdatum(kkAblaufdatum.getText());
					emptyKundenListe.get(i).setKkPruefnummer(Integer.parseInt(kkPruefnummer.getText()));
					JOptionPane.showMessageDialog(null, "Die Kundendaten wurden erfolgreich geändert");
				} else if (Integer.parseInt(alter.getText()) > 1700 && emptyKundenListe.get(i) instanceof Firmenkunde) {
					emptyKundenListe.get(i).setAlter(Integer.parseInt(alter.getText()));
					emptyKundenListe.get(i).setStrasseUndNummer(strasseUndNummer.getText());
					emptyKundenListe.get(i).setPlz(Integer.parseInt(plz.getText()));
					emptyKundenListe.get(i).setOrt(ort.getText());
					emptyKundenListe.get(i).setLand(land.getText());
					emptyKundenListe.get(i).setTelefonNummer(telefonNummer.getText());
					emptyKundenListe.get(i).setEmail(email.getText());
					emptyKundenListe.get(i).setPassword(passwort.getText());
					emptyKundenListe.get(i).setKkNummer(Long.parseLong(kkNummer.getText()));
					emptyKundenListe.get(i).setKkInhaber(kkInhaber.getText());
					emptyKundenListe.get(i).setKkAblaufdatum(kkAblaufdatum.getText());
					emptyKundenListe.get(i).setKkPruefnummer(Integer.parseInt(kkPruefnummer.getText()));
					JOptionPane.showMessageDialog(null, "Die Kundendaten wurden erfolgreich geändert");
				} else if (emptyKundenListe.get(i) instanceof Einzelkunde) {

					JOptionPane.showMessageDialog(null, "Sie müssen mindestens 18 Jahre alt sein");
				} else {
					JOptionPane.showMessageDialog(null, "Das Gründungsjahr muss korrekt angegeben werden");
				}
			}
		}

		// hier wird die aktualisierte kundenliste wieder herausgeschrieben
		try {
			FileOutputStream fos = new FileOutputStream("Kundenliste.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(emptyKundenListe);
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
