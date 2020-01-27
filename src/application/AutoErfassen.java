package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Auto;
import javafx.scene.control.ComboBox;

public class AutoErfassen {

	// Listen mit Werden für ComboBoxen
	ObservableList<String> markeList = FXCollections.observableArrayList("Audi", "BMW", "Fiat", "Lamborghini",
			"Mercedes", "Peugeot", "Renault");

	ObservableList<String> farbeList = FXCollections.observableArrayList("Schwarz", "Weiss", "Rot", "Blau", "Gelb",
			"Grün");

	ObservableList<String> getriebeList = FXCollections.observableArrayList("Automat", "Schaltgetriebe");

	ObservableList<String> treibstoffList = FXCollections.observableArrayList("Benzin", "Diesel", "Hybrid");

	// Felder im GUI
	@FXML
	private ComboBox<String> markeBox;
	@FXML
	private ComboBox<String> farbeBox;
	@FXML
	private ComboBox<String> getriebeBox;
	@FXML
	private ComboBox<String> treibstoffBox;
	@FXML
	private TextField kostenProTag;
	// Button
	@FXML
	private Button autoerfassen;

	// initialize für das Fenster
	@FXML
	private void initialize() {
		markeBox.setValue("Audi");
		markeBox.setItems(markeList);
		farbeBox.setValue("Schwarz");
		farbeBox.setItems(farbeList);
		getriebeBox.setValue("Automat");
		getriebeBox.setItems(getriebeList);
		treibstoffBox.setValue("Benzin");
		treibstoffBox.setItems(treibstoffList);
	}

	/**
	 * Durch das Drücken des Auto erfassen Buttons passiert in dieser Methode
	 * folgendes: 1. Ein Objekt Auto wird gemäss Konstruktor des Autos erzeugt 2.
	 * Das Objekt wird in der Methode autoErfassen, welche in der Klasse Auto
	 * definiert ist, als Parameter übergeben
	 */
	@FXML
	public void handleAutoErfassenButton() {
		// Das Auto wird hier erstellt und als Objekt übergeben
		Auto varAuto = new Auto(markeBox.getValue(), getriebeBox.getValue(), treibstoffBox.getValue(),
				farbeBox.getValue(), Double.parseDouble(kostenProTag.getText()));
		varAuto.autoErfassen(varAuto);
	}

}
