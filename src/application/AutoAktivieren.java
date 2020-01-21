package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
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

public class AutoAktivieren implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	// Listen für ComboBoxen
	ObservableList<String> IdList = FXCollections.observableArrayList();

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

	// initialize für combobox
	public void initialize() {
		//hier findet die berechnung der Strings für die Combobox statt
		//Liste für Dropdown
		List<String> strings = new ArrayList<>();
		
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
			// hier werden die IDs der ComboBox hinzugefügt, wo der Boolean deaktiviert true ist
			if (emptyAutoListe.get(i).isDeaktiviert() == true) {
				strings.add(Integer.toString(emptyAutoListe.get(i).getId()));
				
			}
		}

		autoIDBox.setItems(FXCollections.observableArrayList(strings));
	}

	private MainAdmin parent;

	public AutoAktivieren() {

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
			//hier werden die entsprechenden Felder beschrieben
			if (emptyAutoListe.get(i).getId() == Integer.parseInt(autoIDBox.getValue())) {
				autoMarkeField.setText(emptyAutoListe.get(i).getMarke());
				autoFarbeField.setText(emptyAutoListe.get(i).getFarbe());
				autoGetriebeField.setText(emptyAutoListe.get(i).getGetriebe());
				autoTreibstoffField.setText(emptyAutoListe.get(i).getTreibstoff());

			}
		}

	}
	
	@FXML
	public void handleAutoAktivierenButton(ActionEvent event) {
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
			// hier wird der boolean deaktiviert des entsprechenden Autos auf true gesetzt
			if (emptyAutoListe.get(i).getId() == Integer.parseInt(autoIDBox.getValue())) {
				emptyAutoListe.get(i).setDeaktiviert(false);
				System.out.println("Das Auto :" + emptyAutoListe.get(i) + " wurde aktiviert");
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
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
}
