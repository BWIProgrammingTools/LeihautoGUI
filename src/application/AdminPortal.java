package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;

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

	@SuppressWarnings("unused")
	private MainAdmin parent;


	
	// Konstruktor
	public AdminPortal() {

	}

	// neues Fenster (AutoErfassen) �ffnen
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

	// neues Fenster (Auto deaktivieren) �ffnen
	@FXML
	public void handleAutoDeaktivieren() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoDeaktivieren.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Auto deaktivieren");
		stage.setScene(new Scene(root, 600, 500));
		stage.showAndWait();

	}

	// neues Fenster (Auto aktivieren) �ffnen
	@FXML
	public void handleAutoAktivieren() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AutoAktivieren.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Auto aktivieren");
		stage.setScene(new Scene(root, 600, 500));
		stage.showAndWait();

	}

	// neues Fenster (Auto neu zuweisen) �ffnen
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

	// neues Fenster (Reparatur anmelden) �ffnen
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

	// neues Fenster (Reservation abschliessen) �ffnen
	@FXML
	public void handleReservationAbschliessen() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservationAbschliessen.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Reservation abschliessen");
		stage.setScene(new Scene(root, 950, 800));
		stage.showAndWait();

	}

	// neues Fenster (Kunde sperren sperren) �ffnen
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

	// neues Fenster (Kunde entsperren sperren) �ffnen
	@FXML
	public void handleKundeEntsperren() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KundeEntsperren.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Kunde entsperren");
		stage.setScene(new Scene(root, 500, 600));
		stage.showAndWait();

	}

	public void setParent(MainAdmin main) {
		this.parent = main;
	}
}
