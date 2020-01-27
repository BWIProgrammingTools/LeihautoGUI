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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Auto;

public class AutoAktivieren implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	// Listen f�r ComboBoxen
	ObservableList<String> IdList = FXCollections.observableArrayList();

	// Felder auf GUI
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
	private Button handleAutoLoeschenButton;

	/**
	 * Initialize f�r die aktuelle Szene mit den AutoIDs der deaktiverten Autos in
	 * der Combobox
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		/* hier findet die berechnung der Strings f�r die Combobox statt */

		// Liste f�rs Dropdown
		List<String> strings = new ArrayList<>();

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
			/*
			 * hier werden die IDs der ComboBox hinzugef�gt, wo der Boolean deaktiviert true
			 * ist, da wir nur deaktivierte Autos angezeigt haben wollen
			 */
			if (emptyAutoListe.get(i).isDeaktiviert()) {
				strings.add(Integer.toString(emptyAutoListe.get(i).getId()));

			}
		}
		// hier wird die Liste der entsprechenden IDs der Combobox hinzugef�gt
		autoIDBox.setItems(FXCollections.observableArrayList(strings));
	}
	

	/**
	 * Hier werden die Textfelder der Szene anhand der gew�hlten AutoID gesetzt
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
	 * Durch das Dr�cken des Auto aktiveren Buttons passiert in dieser Methode
	 * folgendes: 1. Die Liste aller Autos wird importiert 2. Der Boolean deaktivert
	 * des entsprechend ausgew�hlten Autos (Combobox) wird auf false gesetzt 3. Eine
	 * MessageBox wird angezeigt 4. Die aktualisierte AutoListe wird wieder
	 * exportiert 5. Die Szene wird geschlossen
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void handleAutoAktivierenButton(ActionEvent event) {
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
			// hier wird der boolean deaktiviert des entsprechenden Autos auf true gesetzt
			if (emptyAutoListe.get(i).getId() == Integer.parseInt(autoIDBox.getValue())) {
				emptyAutoListe.get(i).setDeaktiviert(false);
				JOptionPane.showMessageDialog(null,
						"Das Auto mit der ID " + emptyAutoListe.get(i).getId() + " wurde aktiviert");
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
		/*
		 * dieses Event sorgt daf�r, dass nach dem Dr�cken des Buttons das Gui
		 * geschlossen wird
		 */
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
	}
}
