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

public class AutoDeaktivieren implements Serializable {

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
	private Button handleAutoLoeschenButton;

	// initialize f�r die Seite
	@SuppressWarnings("unchecked")
	public void initialize() {
		/* hier findet die berechnung der Strings f�r die Combobox statt */

		// Liste f�r Dropdown
		List<String> strings = new ArrayList<>();
		// String markenString = new String();

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
			 * hier werden die IDs der ComboBox hinzugef�gt, wo der Boolean deaktiviert
			 * false ist, da wir nur aktive Autos angezeigt haben m�chten
			 */
			if (emptyAutoListe.get(i).isDeaktiviert() == false) {
				strings.add(Integer.toString(emptyAutoListe.get(i).getId()));

			}
		}
		// hier werden die aktiven AutoIDs der Combobox hinzugef�gt
		autoIDBox.setItems(FXCollections.observableArrayList(strings));

	}

	// Konstruktor
	public AutoDeaktivieren() {

	}

	// Methode um diverse Felder im GUI mit Infos zu bef�llen
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

	// Methode f�r den Button
	@SuppressWarnings("unchecked")
	@FXML
	public void handleAutoDeaktivierenButton(ActionEvent event) {
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
				emptyAutoListe.get(i).setDeaktiviert(true);
				JOptionPane.showMessageDialog(null,
						"Das Auto mit der ID " + emptyAutoListe.get(i).getId() + " wurde deaktiviert");
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
		 * dieses event sorgt, dass das Fenster nach dem Dr�cken des Buttons geschlossen
		 * wird
		 */
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
	}
}
