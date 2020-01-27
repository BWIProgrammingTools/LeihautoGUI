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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Auto;
import model.Einzelkunde;
import model.Firmenkunde;
import model.Kunde;
import model.Reservation;

public class AutoNeuzuweisung implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	// Felder im GUI
	@FXML
	private ComboBox<String> reservationsID;

	@FXML
	private TextField kundenNameField;

	@FXML
	private TextField kundenVornameField;

	@FXML
	private TextField kundenStrasseField;

	@FXML
	private TextField kundenOrtField;

	@FXML
	private TextField kundenPLZField;

	@FXML
	private TextField telNummer;

	@FXML
	private TextField markeField;

	@FXML
	private TextField farbeField;

	@FXML
	private TextField getriebeartField;

	@FXML
	private TextField treibstoffartField;

	@FXML
	private TextField tagesSatzField;

	@FXML
	private TextField autoIDField;

	@FXML
	private Button AutoSelektierenButton;

	// diverse Listen f�r die �bergabe von Werten

	// Integer arrayList f�r reservierte AutoIDs
	List<Integer> reservierteIDs = new ArrayList<Integer>();

	// Integer arrayList f�r alle AutoIDs
	List<Integer> alleAutoIDs = new ArrayList<Integer>();

	// Integer arrayList f�r alle deaktivierten AutoIDs
	List<Integer> alleDeaktiviertenAutoIDs = new ArrayList<Integer>();

	// Integer arrayList f�r alle blockierte AutoIDs
	List<Integer> alleBlockiertenAutoIDs = new ArrayList<Integer>();

	/**
	 * Initialize f�r die aktuelle Szene mit den ReservationsIDs in der Combobox
	 */
	@SuppressWarnings("unchecked")
	public void initialize() throws IOException {

		// hier findet die berechnung der Strings f�r die Combobox statt

		// Liste f�r Dropdown
		List<String> strings = new ArrayList<>();

		// hier startet der Import der bestehenden Reservationsliste
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
			/*
			 * hier werden die IDs der ComboBox hinzugef�gt, wo der Boolean inReparatur
			 * false ist
			 */
			if (emptyReservationsListe.get(i).isInReparatus() == false
					&& emptyReservationsListe.get(i).isIstGereinigt() == false) {
				strings.add(Integer.toString(emptyReservationsListe.get(i).getReservationsID()));

			}
		}
		// hier werden die IDs der Liste hinzugef�gt
		reservationsID.setItems(FXCollections.observableArrayList(strings));
	}

	/**
	 * Hier werden die Textfelder der Szene anhand der gew�hlten ReservationsID
	 * gesetzt und die gew�hlte ID in ein File f�r eine sp�tere Verwendung geeschrieben
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void zeigeReservationsAngaben() {
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
			// wenn reservationsID = Combobox Zahl ist wird fortgefahren
			if (emptyReservationsListe.get(i).getReservationsID() == Integer.parseInt(reservationsID.getValue())) {

				/*
				 * hier wird die ID der aktuellen Reservation (mittels lokaler Variable) in ein
				 * File geschrieben (muss Liste sein, da die weiterverwendung mittels einfachem
				 * Integer nicht zu funktionieren scheint
				 */
				List<Integer> aktuelleReservationsIDList = new ArrayList<Integer>();
				aktuelleReservationsIDList.add(emptyReservationsListe.get(i).getReservationsID());

				// hier wird das von Datum in eine Liste geschrieben
				try {
					FileOutputStream fos = new FileOutputStream("aktuelleReservationsID.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					// write object to file
					oos.writeObject(aktuelleReservationsIDList);
					// closing resources
					oos.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				/*
				 * hier wird eine lokal Variable f�r die entsprechende KundenID der Reservation
				 * vergeben
				 */
				int kundenID = emptyReservationsListe.get(i).getKundenNummer();

				// hier wird wieder die komplette Kundenliste reingeladen

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
				for (int ii = 0; ii < emptyKundenListe.size(); ii++) {
					// hier werden die entsprechenden Felder, sofern Einzelkunde bef�llt
					if (emptyKundenListe.get(ii).getKundenNummer() == kundenID
							&& emptyKundenListe.get(ii) instanceof Einzelkunde) {
						kundenNameField.setText(((Einzelkunde) emptyKundenListe.get(ii)).getNachname());
						kundenVornameField.setText(((Einzelkunde) emptyKundenListe.get(ii)).getVorname());
						telNummer.setText(String.valueOf(emptyKundenListe.get(ii).getTelefonNummer()));
						kundenStrasseField.setText(emptyKundenListe.get(ii).getStrasseUndNummer());
						kundenPLZField.setText(String.valueOf(emptyKundenListe.get(ii).getPlz()));
						kundenOrtField.setText(emptyKundenListe.get(ii).getOrt());

					} else if (emptyKundenListe.get(ii).getKundenNummer() == kundenID
							&& emptyKundenListe.get(ii) instanceof Firmenkunde) {
						// hier werden die entsprechenden Felder,sofern Firmenkunde, bef�llt
						kundenNameField.setText(((Firmenkunde) emptyKundenListe.get(ii)).getFirmenname());
						kundenVornameField.setText("");
						telNummer.setText(String.valueOf(emptyKundenListe.get(ii).getTelefonNummer()));
						kundenStrasseField.setText(emptyKundenListe.get(ii).getStrasseUndNummer());
						kundenPLZField.setText(String.valueOf(emptyKundenListe.get(ii).getPlz()));
						kundenOrtField.setText(emptyKundenListe.get(ii).getOrt());
					}

					/*
					 * hier wird eine lokal Variable f�r die entsprechende AutoID der Reservation
					 * vergeben
					 */
					int autoID = emptyReservationsListe.get(i).getAutoID();

					// hier wird wieder die komplette Autoliste reingeladen

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
					for (int iii = 0; iii < emptyAutoListe.size(); iii++) {
						// hier werden die entsprechenden Felder beschrieben
						if (emptyAutoListe.get(iii).getId() == autoID) {
							autoIDField.setText(String.valueOf(emptyAutoListe.get(iii).getId()));
							markeField.setText(emptyAutoListe.get(iii).getMarke());
							farbeField.setText(emptyAutoListe.get(iii).getFarbe());
							getriebeartField.setText(emptyAutoListe.get(iii).getGetriebe());
							treibstoffartField.setText(emptyAutoListe.get(iii).getTreibstoff());
							tagesSatzField.setText(String.valueOf(emptyAutoListe.get(iii).getKostenProTag()));

						}
					}
				}

			}

		}

	}

	/**
	 * Durch das Dr�cken des Auto selektieren Buttons passiert in dieser Methode
	 * folgendes:
	 * 1. Die Reservationsliste wird importiert
	 * 2. Das Von und Bis-Datum der gew�hlten Reservation (Combobox) wird in zwei Files exportiert
	 * 3. Die freien Autos werden ermittelt (die Liste aller Autos wird importiert, die IDs der in diesem
	 *    Zeitraum reservierten Autos (nur diejenigen, bei welchen die Reservation noch nicht abgeschlossen
	 *    wurde), die IDs aller blockierten und die IDs aller deaktiverten Autos werden von der 
	 *    kompletten Autoliste abgezogen). Die freien Autos werden ebenfalls in eine neue Liste exportiert.
	 * 4. Die n�chste Szene wird ge�ffnet
	 */
	@SuppressWarnings("unchecked")
	public void handleAutoSelektierenButton(ActionEvent event) throws IOException {
		/*
		 * zuerst werden die ArrayListen gecleared, damit bei doppelter Ausf�hrung die
		 * Liste nicht mit doppelten Elementen bef�llt wird
		 */
		reservierteIDs.clear();
		alleAutoIDs.clear();

		// hier werden die von und bis daten herausgeschrieben

		// hier startet der Import der bestehenden Reservationsliste
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
			// wenn reservationsID = Combobox Zahl ist wird fortgefahren
			if (emptyReservationsListe.get(i).getReservationsID() == Integer.parseInt(reservationsID.getValue())) {
				/*
				 * lokale Variable f�r entsprechendes reservationsVon der ausgew�hlten
				 * Reservation
				 */
				GregorianCalendar kalenderVon = emptyReservationsListe.get(i).getReservationVon();

				/*
				 * lokale Variable f�r entsprechendes reservationsVon der ausgew�hlten
				 * reservation
				 */
				GregorianCalendar kalenderBis = emptyReservationsListe.get(i).getReservationBis();

				// Die Reservationsliste f�r den Vergleich erneut komplett reingeladen werden

				// hier startet der Import der bestehenden Reservationsliste
				List<Reservation> emptyReservationsListe2 = new ArrayList<Reservation>();
				try {
					FileInputStream fis = new FileInputStream("Reservationsliste.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					// write object to file
					emptyReservationsListe2 = (ArrayList<Reservation>) ois.readObject();
					// closing resources
					ois.close();
					fis.close();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}

				/*
				 * hier werden diejenigen Autos herausgesucht, welche zum angegebenen Zeitpunkt
				 * NICHT verf�gbar sind
				 */
				for (int ii = 0; ii < emptyReservationsListe2.size(); ii++) {

					/*
					 * dieser Vergleich sollte die belegten Autos der Liste im angegebenen Zeitaum
					 * ausgeben
					 */
					if (kalenderVon.before(emptyReservationsListe2.get(ii).getReservationBis())
							&& kalenderBis.after(emptyReservationsListe2.get(ii).getReservationVon())
							&& emptyReservationsListe2.get(ii).isIstGereinigt() == false) {

						/*
						 * hier wird die entsprechende AutoID, welche reserviert ist der ArrayListe
						 * reservierteID geadded
						 */
						reservierteIDs.add(emptyReservationsListe2.get(ii).getAutoID());

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

				// hier werden alle AutoIDs der alleAutoIDs Arrayliste hinzugef�t
				for (int iii = 0; iii < emptyAutoListe.size(); iii++) {
					alleAutoIDs.add(emptyAutoListe.get(iii).getId());
				}

				/*
				 * hier werden alle deaktivierten AutoIDs in die deaktivierteAutoIDs Arrayliste
				 * geladen
				 */
				for (int iiii = 0; iiii < emptyAutoListe.size(); iiii++) {
					// if, damit deaktivierte und in reparatur autos nicht angezeigt werden
					if (emptyAutoListe.get(iiii).isDeaktiviert()) {
						alleDeaktiviertenAutoIDs.add(emptyAutoListe.get(iiii).getId());
					}
				}
				/*
				 * hier werden alle blockierten AutoIDs in die blockiertealleAutoIDs Arrayliste
				 * geladen
				 */
				for (int iiiii = 0; iiiii < emptyAutoListe.size(); iiiii++) {
					// if, damit deaktivierte und in reparatur autos nicht angezeigt werden
					if (emptyAutoListe.get(iiiii).isBlockiert()) {
						alleBlockiertenAutoIDs.add(emptyAutoListe.get(iiiii).getId());
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

				// hier wird der neue Dialog f�r die eigentliche Reservation ge�ffnet
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoZuweisungAuswaehlen.fxml"));
				Parent root = fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setOpacity(1);
				stage.setTitle("Auto ausw�hlen");
				stage.setScene(new Scene(root, 900, 700));
				stage.showAndWait();

			}
		}
		// event dass Fenster geschlossen wird
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
	}

}
