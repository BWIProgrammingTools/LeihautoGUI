package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

//	@FXML
//	private ComboBox<String> autoIDBox;
//
//	@FXML
//	private TextField autoMarkeField;
//
//	@FXML
//	private TextField autoFarbeField;
//
//	@FXML
//	private TextField autoGetriebeField;
//
//	@FXML
//	private TextField autoTreibstoffField;

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

	// Integer arrayList für reservierte AutoIDs
	List<Integer> reservierteIDs = new ArrayList<Integer>();

	// Integer arrayList für alle AutoIDs
	List<Integer> alleAutoIDs = new ArrayList<Integer>();

	// Integer arrayList für alle deaktivierten AutoIDs
	List<Integer> alleDeaktiviertenAutoIDs = new ArrayList<Integer>();

	// Integer arrayList für alle blockierte AutoIDs
	List<Integer> alleBlockiertenAutoIDs = new ArrayList<Integer>();

	// initialize des Fensters
	public void initialize() throws IOException {
		// hier wird eine leere ArrayList erstellt
		// hier findet die berechnung der Strings für die Combobox statt

		// Liste für Dropdown
		List<String> strings = new ArrayList<>();

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

		// hier wird mit einer for Schlaufe durch die importierte Reservationsliste
		// iteriert
		for (int i = 0; i < emptyReservationsListe.size(); i++) {
			// hier werden die IDs der ComboBox hinzugefügt, wo der Boolean inReparatur
			// false ist
			if (emptyReservationsListe.get(i).isInReparatus() == false
					&& emptyReservationsListe.get(i).isIstGereinigt() == false) {
				strings.add(Integer.toString(emptyReservationsListe.get(i).getReservationsID()));

			}
		}

		reservationsID.setItems(FXCollections.observableArrayList(strings));
	}

	private MainAdmin parent;

	public AutoNeuzuweisung() {

	}

	@FXML
	public void zeigeReservationsAngaben() {
		// hier wird eine leere ArrayList erstellt
		List<Reservation> emptyReservationsListe = new ArrayList<Reservation>();

		// hier startet der Import der bestehenden reservationsliste
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

		// hier wird mit einer for Schlaufe durch die importierte Reservationsliste
		// iteriert
		for (int i = 0; i < emptyReservationsListe.size(); i++) {
			// wenn reservationsID = Combobox Zahl ist wird fortgefahren
			if (emptyReservationsListe.get(i).getReservationsID() == Integer.parseInt(reservationsID.getValue())) {

				// hier wird die ID der aktuellen Reservation (mittels lokaler Variable) in
				// ein File geschrieben (muss Liste sein, da die weiterverwendung mittels
				// einfachem Integer nicht zu funktionieren scheint
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

				// hier wird eine lokal Variable für die entsprechende AutoID der Reservation
				// vergeben
				int kundenID = emptyReservationsListe.get(i).getKundenNummer();

				// hier wird wieder die komplette Autoliste reingeladen

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

				// hier werden die kunden der bestehenden Liste als Objekte herausgefiltert und
				// der leeren Kundenliste angefügt
				for (Kunde existingKunde : importKundenListe) {
					emptyKundenListe.add(existingKunde);
				}

				// hier wird mit einer for Schlaufe durch die importierte Kundenliste iteriert
				for (int ii = 0; ii < emptyKundenListe.size(); ii++) {
					// hier wird der entsprechende Kunde gemäss ID gesperrt
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
						kundenNameField.setText(((Firmenkunde) emptyKundenListe.get(ii)).getFirmenname());
						kundenVornameField.setText("");
						telNummer.setText(String.valueOf(emptyKundenListe.get(ii).getTelefonNummer()));
						kundenStrasseField.setText(emptyKundenListe.get(ii).getStrasseUndNummer());
						kundenPLZField.setText(String.valueOf(emptyKundenListe.get(ii).getPlz()));
						kundenOrtField.setText(emptyKundenListe.get(ii).getOrt());
					}

					// hier wird eine lokal Variable für die entsprechende AutoID der Reservation
					// vergeben
					int autoID = emptyReservationsListe.get(i).getAutoID();

					// hier wird wieder die komplette Autoliste reingeladen

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

	// Methode für die AutoSelektierung
	public void handleAutoSelektierenButton() throws IOException {
		// zuerst werden die ArrayListen gecleared, damit bei doppelter Ausführung die
		// Liste nicht mit doppelten Elementen befüllt wird
		reservierteIDs.clear();
		alleAutoIDs.clear();

		// hier werden die von und bis daten herausgeschrieben

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

		// hier wird mit einer for Schlaufe durch die importierte Reservationsliste
		// iteriert
		for (int i = 0; i < emptyReservationsListe.size(); i++) {
			// wenn reservationsID = Combobox Zahl ist wird fortgefahren
			if (emptyReservationsListe.get(i).getReservationsID() == Integer.parseInt(reservationsID.getValue())) {
				// lokale Variable für entsprechendes reservationsVon der ausgewählten
				// reservation
				GregorianCalendar kalenderVon = emptyReservationsListe.get(i).getReservationVon();
				// hier wird das von Datum in eine Liste geschrieben

				// lokale Variable für entsprechendes reservationsVon der ausgewählten
				// reservation
				GregorianCalendar kalenderBis = emptyReservationsListe.get(i).getReservationBis();
				// hier wird das bis Datum in eine Liste geschrieben

				List<Reservation> emptyReservationsListe2 = new ArrayList<Reservation>();

				// hier startet der Import der bestehenden Kundenliste
				List<Reservation> importReservationsListe2 = new ArrayList<Reservation>();
				try {
					FileInputStream fis = new FileInputStream("Reservationsliste.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					// write object to file
					importReservationsListe2 = (ArrayList) ois.readObject();
					// closing resources
					ois.close();
					fis.close();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				// hier werden die kunden der bestehenden Liste als Objekte herausgefiltert und
				// der leeren Reservationsliste angefügt
				for (Reservation existingReservation : importReservationsListe2) {
					emptyReservationsListe2.add(existingReservation);
				}

				// hier werden diejenigen Autos herausgesucht, welche zum angegebenen Zeitpunkt
				// NICHT verfügbar sind
				for (int ii = 0; ii < emptyReservationsListe2.size(); ii++) {

					// dieser Vergleich sollte die belegten Autos der Liste im angegebenen Zeitaum
					// ausgeben
					if (kalenderVon.before(emptyReservationsListe2.get(ii).getReservationBis())
							&& kalenderBis.after(emptyReservationsListe2.get(ii).getReservationVon())) {

						// hier wird die entsprechende AutoID, welche reserviert ist der ArrayListe
						// reservierteID geadded
						reservierteIDs.add(emptyReservationsListe2.get(ii).getAutoID());

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

				// hier werden alle AutoIDs der alleAutoIDs Arrayliste hinzugefüt
				for (int iii = 0; iii < emptyAutoListe.size(); iii++) {
					alleAutoIDs.add(emptyAutoListe.get(iii).getId());
				}

				// hier werden alle deaktivierten AutoIDs in die deaktivierteAutoIDs Arrayliste
				// geladen
				for (int iiii = 0; iiii < emptyAutoListe.size(); iiii++) {
					// if, damit deaktivierte und in reparatur autos nicht angezeigt werden
					if (emptyAutoListe.get(iiii).isDeaktiviert() == true) {
						alleDeaktiviertenAutoIDs.add(emptyAutoListe.get(iiii).getId());
					}
				}
				// hier werden alle blockierten AutoIDs in die blockiertealleAutoIDs Arrayliste
				// geladen
				for (int iiiii = 0; iiiii < emptyAutoListe.size(); iiiii++) {
					// if, damit deaktivierte und in reparatur autos nicht angezeigt werden
					if (emptyAutoListe.get(iiiii).isBlockiert() == true) {
						alleBlockiertenAutoIDs.add(emptyAutoListe.get(iiiii).getId());
					}
				}

				// hier ziehen wir die IDs der reservierten,deaktiverten und blockierten Autos
				// von allen IDs ab
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
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoZuweisungAuswaehlen.fxml"));
				Parent root = fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setOpacity(1);
				stage.setTitle("Auto auswählen");
				stage.setScene(new Scene(root, 900, 700));
				stage.showAndWait();

			}
		}
	}

//	public GregorianCalendar getKalenderVon() {
//		return kalenderVon;
//	}
//
//	public void setKalenderVon(GregorianCalendar kalenderVon) {
//		this.kalenderVon = kalenderVon;
//	}
//
//	public GregorianCalendar getKalenderBis() {
//		return kalenderBis;
//	}
//
//	public void setKalenderBis(GregorianCalendar kalenderBis) {
//		this.kalenderBis = kalenderBis;
//	}
}
