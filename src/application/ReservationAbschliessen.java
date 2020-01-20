package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Auto;
import model.Einzelkunde;
import model.Kunde;
import model.Reparaturen;
import model.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ReservationAbschliessen implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	// Listen für ComboBoxen
	ObservableList<String> IdList = FXCollections.observableArrayList();

	@FXML
	private ComboBox<String> reservationsIDBox;

	@FXML
	private TextField reparaturkosten;

	@FXML
	private Button reservationabschliessen;

	// initialize für combobox
	public void initialize() {
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
			if (emptyReservationsListe.get(i).isIstGereinigt() == false) {
				strings.add(Integer.toString(emptyReservationsListe.get(i).getReservationsID()));

			}
		}

		reservationsIDBox.setItems(FXCollections.observableArrayList(strings));
	}

	private MainAdmin parent;

	public ReservationAbschliessen() {

	}

	@FXML
	public void handleReservationAbschliessenButton(ActionEvent event) {

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

			// wenn username, email und alter korrekt sind, wird das pw ausgegeben
			if (emptyReservationsListe.get(i).getReservationsID() == Integer.parseInt(reservationsIDBox.getValue())) {

				// hier wird der boolean needsReparatur in der Klasse Reservation nach der
				// Reparatur wieder auf false geändert
				emptyReservationsListe.get(i).setInReparatus(false);
				System.out.println("Das Auto der " + emptyReservationsListe.get(i) + " ist aus der Reparatur zurück");

				// hier wird der Status der Reinigung wieder auf true gesetzt
				emptyReservationsListe.get(i).setIstGereinigt(true);
				System.out.println("Das Auto der " + emptyReservationsListe.get(i)
						+ " ist gereinigt und zur Weitervermietung bereit");

				// hier werden die entsprechenden Reparaturkosten dem Attribut hinzugefügt und
				// die Kostenberechung für die Reparatur findet statt
				emptyReservationsListe.get(i).setReparaturKosten(Double.parseDouble(reparaturkosten.getText()));

				// hier wird eine lokale Variable für die Reparaturkosten bestimmt
				double restKosten = emptyReservationsListe.get(i).getSicherheitsLeistung()
						- emptyReservationsListe.get(i).getReparaturKosten();
				if (restKosten >= 0) {
					System.out.println("Dem Kunden muss CHF " + restKosten + " zurückbezahlt werden.");
				} else {
					System.out
							.println("Dem Kunden muss eine Rechnung über CHF " + restKosten * -1 + " gestellt werden");
				}

				// hier wird eine lokal Variable für die entsprechende AutoID der Reservation
				// vergeben
				int autoID = emptyReservationsListe.get(i).getAutoID();

				// hier wird wieder die komplette Autoliste reingeladen, um den boolean
				// blockiert auf dem Auto mit der entsprechenden ID zu setzen

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
				for (int ii = 0; ii < emptyAutoListe.size(); ii++) {
					// hier wird der boolean blockiert in der Klasse Auto nach der Reparatur wieder
					// auf false gesetzt
					// wenn die Reparatur abgeschlossen ist, muss man das wieder ändern (Reservation
					// beendet Button)
					if (emptyAutoListe.get(ii).getId() == autoID) {
						emptyAutoListe.get(ii).setBlockiert(false);
						System.out.println("Das Auto :" + emptyAutoListe.get(ii) + " ist wieder frei");
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
	}
}
