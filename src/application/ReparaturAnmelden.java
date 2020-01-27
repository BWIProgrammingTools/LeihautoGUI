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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Auto;
import model.Einzelkunde;
import model.Firmenkunde;
import model.Kunde;
import model.Reservation;
import javafx.scene.control.ComboBox;

public class ReparaturAnmelden implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	// Listen für ComboBoxen
	ObservableList<String> IdList = FXCollections.observableArrayList();

	@FXML
	private ComboBox<String> reservationsIDBox;

	@FXML
	private TextField kundenNRField;

	@FXML
	private TextField kundenNameField;

	@FXML
	private TextField kundenVornameField;

	@FXML
	private TextField firmenNameField;

	@FXML
	private TextField kundenStrasseField;

	@FXML
	private TextField kundenPLZField;

	@FXML
	private TextField kundenOrtField;

	@FXML
	private TextField autoIDField;

	@FXML
	private TextField autoMarkeField;

	@FXML
	private TextField autoFarbeField;

	@FXML
	private TextField autoGetriebeField;

	@FXML
	private TextField autoTreibstoffField;

	@FXML
	private Button reparaturanmelden;

	/**
	 * Initialize für die aktuelle Szene mit den ReservationsIDs, welche nicht
	 * abgeschlossen oder bereits in Reparatur sind in der Combobox
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		// hier wird eine leere ArrayList erstellt
		// hier findet die berechnung der Strings für die Combobox statt

		// Liste für Dropdown
		List<String> strings = new ArrayList<>();

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
		 * hier wird mit einer for Schlaufe durch die importierte Reservationsliste
		 * iteriert
		 */
		for (int i = 0; i < emptyReservationsListe.size(); i++) {
			// hier werden die IDs der ComboBox hinzugefügt, wo der Boolean inReparatur
			// false ist
			if (emptyReservationsListe.get(i).isInReparatus() == false
					&& emptyReservationsListe.get(i).isIstGereinigt() == false) {
				strings.add(Integer.toString(emptyReservationsListe.get(i).getReservationsID()));

			}
		}

		reservationsIDBox.setItems(FXCollections.observableArrayList(strings));
	}

	/**
	 * Hier werden die Textfelder der Szene anhand der gewählten ReservationsID
	 * gesetzt
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
			if (emptyReservationsListe.get(i).getReservationsID() == Integer.parseInt(reservationsIDBox.getValue())) {

				// hier wird eine lokal Variable für die entsprechende AutoID der Reservation
				// vergeben
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
				for (int ii = 0; ii < emptyAutoListe.size(); ii++) {
					// hier werden die entsprechenden Felder beschrieben
					if (emptyAutoListe.get(ii).getId() == autoID) {
						autoMarkeField.setText(emptyAutoListe.get(ii).getMarke());
						autoFarbeField.setText(emptyAutoListe.get(ii).getFarbe());
						autoGetriebeField.setText(emptyAutoListe.get(ii).getGetriebe());
						autoTreibstoffField.setText(emptyAutoListe.get(ii).getTreibstoff());

					}
				}

				/*
				 * hier wird eine lokal Variable für die entsprechende AutoID der Reservation
				 * vergeben
				 */
				int kundenID = emptyReservationsListe.get(i).getKundenNummer();

				// hier wird wieder die komplette Autoliste reingeladen

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
				for (int iii = 0; iii < emptyKundenListe.size(); iii++) {
					// hier wird der entsprechende Kunde gemäss ID gesperrt
					if (emptyKundenListe.get(iii).getKundenNummer() == kundenID
							&& emptyKundenListe.get(iii) instanceof Einzelkunde) {
						kundenNameField.setText(((Einzelkunde) emptyKundenListe.get(iii)).getNachname());
						kundenVornameField.setText(((Einzelkunde) emptyKundenListe.get(iii)).getVorname());
						firmenNameField.setText("");
						kundenStrasseField.setText(emptyKundenListe.get(iii).getStrasseUndNummer());
						kundenPLZField.setText(String.valueOf(emptyKundenListe.get(iii).getPlz()));
						kundenOrtField.setText(emptyKundenListe.get(iii).getOrt());

					} else if (emptyKundenListe.get(iii).getKundenNummer() == kundenID
							&& emptyKundenListe.get(iii) instanceof Firmenkunde) {
						kundenNameField.setText("");
						kundenVornameField.setText("");
						firmenNameField.setText(((Firmenkunde) emptyKundenListe.get(iii)).getFirmenname());
						kundenStrasseField.setText(emptyKundenListe.get(iii).getStrasseUndNummer());
						kundenPLZField.setText(String.valueOf(emptyKundenListe.get(iii).getPlz()));
						kundenOrtField.setText(emptyKundenListe.get(iii).getOrt());
					}
				}

			}

		}

	}

	/**
	 * Anhand der gewählten ReservationsID wird der Boolean inReparatur auf der
	 * entsprechenden Reservation auf True gesetzt. Die entsprechende AutoID der
	 * Reservation wird als lokale Variable gespeichert. Im nächsten Schritt wird
	 * die lokal gespeicherte AutoID in der AutoListe (vorher importiert) gesucht
	 * und dort wo es eine Übereinstimmung gibt, wird der Boolean blockiert auf true
	 * gesetzt. Am Schluss wird eine MessageBox angezeigt und die Szene geschlossen.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void handleReparaturAnmeldenButton(ActionEvent event) {
		if (!reservationsIDBox.getSelectionModel().isEmpty()) {
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
			 * hier wird mit einer for Schlaufe durch die importierte Reservationsliste
			 * iteriert
			 */
			for (int i = 0; i < emptyReservationsListe.size(); i++) {
				// wenn reservationsID = Combobox Zahl ist wird fortgefahren
				if (emptyReservationsListe.get(i).getReservationsID() == Integer
						.parseInt(reservationsIDBox.getValue())) {
					// hier wird der boolean needsReparatur in der Klasse Reservation geändert
					// wenn die Reparatur abgeschlossen ist, muss man das wieder ändern (Reservation
					// beendet Button)
					emptyReservationsListe.get(i).setInReparatus(true);

					// hier wird eine lokal Variable für die entsprechende AutoID der Reservation
					// vergeben
					int autoID = emptyReservationsListe.get(i).getAutoID();

					/*
					 * hier wird wieder die komplette Autoliste reingeladen, um den boolean
					 * blockiert auf dem Auto mit der entsprechenden ID zu setzen
					 */

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
					for (int ii = 0; ii < emptyAutoListe.size(); ii++) {
						// hier wird der boolean blockiert in der Klasse Auto geändert
						// wenn die Reparatur abgeschlossen ist, muss man das wieder ändern (Reservation
						// beendet Button)
						if (emptyAutoListe.get(ii).getId() == autoID) {
							emptyAutoListe.get(ii).setBlockiert(true);

							// Message vor dem Schliessen
							// Messagebox vor dem Schliessen
							JOptionPane.showMessageDialog(null, "Das Auto mit der ID " + emptyAutoListe.get(ii).getId()
									+ " von der Reservation " + emptyReservationsListe.get(i).getReservationsID()
									+ " wurde für die Reservation angemeldet.\nBitte die entsprechende Garage kontaktieren.");
						}
					}

					// hier wird die aktualisierte Autoliste wieder herausgeschrieben
					try {
						FileOutputStream fos = new FileOutputStream("Autoliste.ser");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						// write object to file
						oos.writeObject(emptyAutoListe);
						// closing resources
						oos.close();
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

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
			((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
		} else {
			JOptionPane.showMessageDialog(null, "Eine ReservationsID muss selektiert werden.");
		}
	}

}
