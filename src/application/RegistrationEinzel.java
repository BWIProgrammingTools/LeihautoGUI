package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Einzelkunde;
import model.Kunde;

public class RegistrationEinzel {

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

	boolean usernameVorhanden;

	public RegistrationEinzel() {

	}

	// Methode f�r den Registrierungsbutton
	// 1. importiert die bestehende Kundenliste
	// 2. erstellt eine neue ArrayList
	// 3. erstellt ein neues Objekt Kunde
	// 4. added das Objekt ebenfalls der ArrayList
	// 5. exportiert die neue ArrayList wieder als Kundenliste
	@FXML
	public void handleRegistrationAbschliessenButton(ActionEvent event) {
		/*
		 * username zuerst auf false setzen, sofern nach dem Dr�cken auf den Button auf
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
		// der leeren Kundenliste angef�gt
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
					|| vorname.getText().isEmpty() || nachname.getText().isEmpty()
					|| fuehrerausweisNummer.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Es m�ssen alle Felder ausgef�llt werden");

			} else if (Integer.parseInt(alter.getText()) > 17) {
				Kunde vareinzelkunde = new Einzelkunde(strasseUndNummer.getText(), ort.getText(),
						Integer.parseInt(plz.getText()), land.getText(), Integer.parseInt(alter.getText()),
						telefonNummer.getText(), email.getText(), username.getText(), passwort.getText(),
						kkInhaber.getText(), Long.parseLong(kkNummer.getText()), kkAblaufdatum.getText(),
						Integer.parseInt(kkPruefnummer.getText()), vorname.getText(), nachname.getText(),
						Long.parseLong(fuehrerausweisNummer.getText()));
				vareinzelkunde.registration(vareinzelkunde);
				((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
			} else {
				JOptionPane.showMessageDialog(null, "Sie m�ssen mindestens 18 Jahre alt sein");
			}
		}
	}

}
