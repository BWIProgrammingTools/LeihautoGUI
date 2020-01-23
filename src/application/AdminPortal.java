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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Auto;
import model.Einzelkunde;
import model.Kunde;
import model.Reservation;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminPortal {

	// Felder auf GUI
	@FXML
	private Button autoErfassen;
	@FXML
	private Button autoDeaktivieren;
	@FXML
	private Button autoAktivieren;
	@FXML
	private Button autoNeuzuweisen;
	@FXML
	private Button reparaturAnmelden;
	@FXML
	private Button reservationAbschliessen;
	@FXML
	private Button kundeSperren;
	@FXML
	private Button kundeEntsperren;

	private MainAdmin parent;


	
	// Konstruktor
	public AdminPortal() {

	}

	// neues Fenster (AutoErfassen) öffnen
	@FXML
	public void handleAutoErfassen() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoErfassen.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Auto erfassen");
		stage.setScene(new Scene(root, 700, 700));
		stage.showAndWait();

	}

	// neues Fenster (Auto deaktivieren) öffnen
	@FXML
	public void handleAutoDeaktivieren() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoDeaktivieren.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Auto deaktivieren");
		stage.setScene(new Scene(root, 700, 700));
		stage.showAndWait();

	}

	// neues Fenster (Auto aktivieren) öffnen
	@FXML
	public void handleAutoAktivieren() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoAktivieren.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Auto aktivieren");
		stage.setScene(new Scene(root, 700, 700));
		stage.showAndWait();

	}

	// neues Fenster (Auto neu zuweisen) öffnen
	@FXML
	public void handleAutoNeuzuweisen() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoNeuzuweisung.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Auto neu zuweisen");
		stage.setScene(new Scene(root, 900, 700));
		stage.showAndWait();

	}

	// neues Fenster (Reparatur anmelden) öffnen
	@FXML
	public void handleReparaturAnmelden() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReparaturAnmelden.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Reparatur anmelden");
		stage.setScene(new Scene(root, 850, 700));
		stage.showAndWait();

	}

	// neues Fenster (Reservation abschliessen) öffnen
	@FXML
	public void handleReservationAbschliessen() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservationAbschliessen.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Reservation abschliessen");
		stage.setScene(new Scene(root, 950, 700));
		stage.showAndWait();

	}

	// neues Fenster (Kunde sperren sperren) öffnen
	@FXML
	public void handleKundeSperren() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KundeSperren.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Kunde sperren");
		stage.setScene(new Scene(root, 700, 700));
		stage.showAndWait();

	}

	// neues Fenster (Kunde entsperren sperren) öffnen
	@FXML
	public void handleKundeEntsperren() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KundeEntsperren.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Kunde entsperren");
		stage.setScene(new Scene(root, 700, 700));
		stage.showAndWait();

	}

	public void setParent(MainAdmin main) {
		this.parent = main;
	}
}
