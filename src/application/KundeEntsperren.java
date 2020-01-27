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
import model.Einzelkunde;
import model.Firmenkunde;
import model.Kunde;
import javafx.scene.control.ComboBox;

public class KundeEntsperren implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	// Listen für ComboBoxen
	ObservableList<String> IdList = FXCollections.observableArrayList();

	// Felder im GUI
	@FXML
	private ComboBox<String> kundenIDBox;

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
	private Button kundeentsperren;

	/**
	 * Initialize für die aktuelle Szene mit den KundenIDs der gesperrten Kunden in
	 * der Combobox
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		// hier findet die berechnung der Strings für die Combobox statt
		// Liste für Dropdown
		List<String> strings = new ArrayList<>();

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
			// hier wird der entsprechende Kunde gemäss ID gesperrt
			if (emptyKundenListe.get(i).isGesperrt()) {
				strings.add(Integer.toString(emptyKundenListe.get(i).getKundenNummer()));
			}
		}

		kundenIDBox.setItems(FXCollections.observableArrayList(strings));
	}

	/**
	 * Hier werden die Textfelder der Szene anhand der gewählten KundenID gesetzt
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void zeigeKunde() {
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
			// hier wird der entsprechende Kunde gemäss ID gesperrt
			if (emptyKundenListe.get(i).getKundenNummer() == Integer.parseInt(kundenIDBox.getValue())
					&& emptyKundenListe.get(i) instanceof Einzelkunde) {
				kundenNameField.setText(((Einzelkunde) emptyKundenListe.get(i)).getNachname());
				kundenVornameField.setText(((Einzelkunde) emptyKundenListe.get(i)).getVorname());
				firmenNameField.setText("");
				kundenStrasseField.setText(emptyKundenListe.get(i).getStrasseUndNummer());
				kundenPLZField.setText(String.valueOf(emptyKundenListe.get(i).getPlz()));
				kundenOrtField.setText(emptyKundenListe.get(i).getOrt());

			} else if (emptyKundenListe.get(i).getKundenNummer() == Integer.parseInt(kundenIDBox.getValue())
					&& emptyKundenListe.get(i) instanceof Firmenkunde) {
				kundenNameField.setText("");
				kundenVornameField.setText("");
				firmenNameField.setText(((Firmenkunde) emptyKundenListe.get(i)).getFirmenname());
				kundenStrasseField.setText(emptyKundenListe.get(i).getStrasseUndNummer());
				kundenPLZField.setText(String.valueOf(emptyKundenListe.get(i).getPlz()));
				kundenOrtField.setText(emptyKundenListe.get(i).getOrt());
			}
		}

	}

	/**
	 * Durch das Drücken des Kunde entsperren Buttons passiert in dieser Methode
	 * folgendes: 1. Die Liste aller Kunden wird importiert 2. Der Boolean blockiert
	 * des entsprechend ausgewählten Autos (Combobox) wird auf false gesetzt 3. Eine
	 * MessageBox wird angezeigt 4. Die aktualisierte Kundenliste wird wieder
	 * exportiert 5. Die Szene wird geschlossen
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void handleKundeEntsperrenButton(ActionEvent event) {
		if (!kundenIDBox.getSelectionModel().isEmpty()) {
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
				// hier wird der entsprechende Kunde gemäss ID entsperrt
				if (emptyKundenListe.get(i).getKundenNummer() == Integer.parseInt(kundenIDBox.getValue())) {
					emptyKundenListe.get(i).unlockKunde();

					// Messagebox vor dem Schliessen
					JOptionPane.showMessageDialog(null,
							"Der Kunde mit der ID " + emptyKundenListe.get(i).getKundenNummer() + " wurde entsperrt.");
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
			((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
		} else {
			JOptionPane.showMessageDialog(null, "Eine KundenID muss selektiert werden.");
		}
	}
}
