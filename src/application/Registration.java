package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.INPUT_STREAM;

import javafx.application.Platform;
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
import model.Einzelkunde;
import model.Kunde;

public class Registration {

	// Felder und Buttons vom SceneBuilder FXML mappen
	@FXML
	private TextField vorname;
	@FXML
	private TextField nachname;
	@FXML
	private TextField fuehrerausweisNummer;
	@FXML
	private TextField firmenname;
	@FXML
	private TextField strasseUndNummer;
	@FXML
	private TextField ort;
	@FXML
	private TextField plz;
	@FXML
	private TextField land;
	@FXML
	private TextField alter;
	@FXML
	private TextField telefonNummer;
	@FXML
	private TextField email;
	@FXML
	private TextField username;
	@FXML
	private TextField passwort;
	@FXML
	private TextField kkNummer;
	@FXML
	private TextField kkInhaber;
	@FXML
	private TextField kkAblaufdatum;
	@FXML
	private TextField kkPruefnummer;
	@FXML
	private Button registration;

	private Main parent;

	public Registration() {

	}

	// Methode f�r den Registrierungsbutton
	// 1. importiert die bestehende Kundenliste
	// 2. erstellt eine neue ArrayList
	// 3. erstellt ein neues Objekt Kunde
	// 4. added das Objekt ebenfalls der ArrayList
	// 5. exportiert die neue ArrayList wieder als Kundenliste
	@FXML
	public void handleRegistration() {
		Kunde vareinzelkunde = new Einzelkunde(strasseUndNummer.getText(), ort.getText(), Integer.parseInt(plz.getText()),
				land.getText(), Integer.parseInt(alter.getText()), telefonNummer.getText(), email.getText(),
				username.getText(), passwort.getText(), kkInhaber.getText(), Long.parseLong(kkNummer.getText()),
				kkAblaufdatum.getText(), Integer.parseInt(kkPruefnummer.getText()), vorname.getText(), nachname.getText(), Integer.parseInt(fuehrerausweisNummer.getText()));
		vareinzelkunde.registration(vareinzelkunde);
	}

}
