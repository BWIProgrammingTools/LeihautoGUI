package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AutoErfassen {

	// Listen für ComboBoxen
	ObservableList<String> markeList = FXCollections.observableArrayList("Audi", "BMW", "Fiat", "Lamborghini",
			"Mercedes", "Peugeot", "Renault");

	ObservableList<String> farbeList = FXCollections.observableArrayList("Schwarz", "Weiss", "Rot", "Blau", "Gelb",
			"Grün");

	ObservableList<String> getriebeList = FXCollections.observableArrayList("Automat", "Schaltgetriebe");

	ObservableList<String> treibstoffList = FXCollections.observableArrayList("Benzin", "Diesel", "Hybrid");

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

	@FXML
	private Button autoerfassen;

	// initialize für combobox
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

	public AutoErfassen() {

	}

	@FXML
	public void handleAutoErfassenButton() {
		Auto varAuto = new Auto(markeBox.getValue(), getriebeBox.getValue(), treibstoffBox.getValue(),
				farbeBox.getValue(), Double.parseDouble(kostenProTag.getText()));
		varAuto.autoErfassen(varAuto);
	}

}
