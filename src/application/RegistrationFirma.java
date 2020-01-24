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
import model.Firmenkunde;
import model.Kunde;

public class RegistrationFirma {

	// Felder und Buttons vom SceneBuilder FXML mappen
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

	private boolean usernameVorhanden;

	public RegistrationFirma() {

	}

	// Methode für den Registrierungsbutton
	// 1. importiert die bestehende Kundenliste
	// 2. erstellt eine neue ArrayList
	// 3. erstellt ein neues Objekt Kunde
	// 4. added das Objekt ebenfalls der ArrayList
	// 5. exportiert die neue ArrayList wieder als Kundenliste
	@FXML
	public void handleRegistrationAbschliessenFirmenkundenButton(ActionEvent event) {
		/*
		 * username zuerst auf false setzen, sofern nach dem Drücken auf den Button auf
		 * true gesetzt wurde
		 */
		this.usernameVorhanden = false;
		// aktuelle Kundenliste reinladen um Usernamevergleich zu starten

		// hier wird eine leere ArrayList erstellt
		List<Kunde> emptyKundenListe = new ArrayList<Kunde>();

		// hier startet der Import der bestehenden Kundenliste
		List<Kunde> importKundenListe = new ArrayList<Kunde>();
		try {
			FileInputStream fis = new FileInputStream("Kundenliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			importKundenListe = (ArrayList) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// hier werden die kunden der bestehenden Liste als Objekte herausgefiltert und
		// der leeren Kundenliste angefügt
		for (Kunde existingKunde : importKundenListe) {
			emptyKundenListe.add(existingKunde);
		}

		// hier wird mit einer for Schlaufe durch die importierte Kundenliste iteriert
		for (int i = 0; i < emptyKundenListe.size(); i++) {
			// hier passiert der Usernamevergleich
			if (emptyKundenListe.get(i).getUsername().compareTo(username.getText()) == 0) {
				this.usernameVorhanden = true;
			}
		}

		if (this.usernameVorhanden == true) {
			JOptionPane.showMessageDialog(null, "Username ist bereits vergeben");
		} else {

			if (strasseUndNummer.getText().isEmpty() || ort.getText().isEmpty() || plz.getText().isEmpty()
					|| land.getText().isEmpty() || alter.getText().isEmpty() || telefonNummer.getText().isEmpty()
					|| email.getText().isEmpty() || username.getText().isEmpty() || passwort.getText().isEmpty()
					|| kkInhaber.getText().isEmpty() || kkNummer.getText().isEmpty()
					|| kkAblaufdatum.getText().isEmpty() || kkPruefnummer.getText().isEmpty()
					|| firmenname.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Es müssen alle Felder ausgefüllt werden");
			} else if (Integer.parseInt(alter.getText()) > 1700) {
				Kunde varfirmenkunde = new Firmenkunde(strasseUndNummer.getText(), ort.getText(),
						Integer.parseInt(plz.getText()), land.getText(), Integer.parseInt(alter.getText()),
						telefonNummer.getText(), email.getText(), username.getText(), passwort.getText(),
						kkInhaber.getText(), Long.parseLong(kkNummer.getText()), kkAblaufdatum.getText(),
						Integer.parseInt(kkPruefnummer.getText()), firmenname.getText());
				varfirmenkunde.registration(varfirmenkunde);
				((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
			} else {
				JOptionPane.showMessageDialog(null, "Das Gründungsjahr muss korrekt angegeben werden");
			}
		}
	}
}
