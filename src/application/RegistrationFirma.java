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

	/**
	 * Sofern alle Felder ausgef�llt, der Username nicht bereits vorhanden ist, das
	 * Gr�ndungsjahr nach 1700 ist, wird ein Objekt Firmenkunde erstellt und als
	 * Parameter der Methode registration, welche in der Klasse Kunde definiert ist,
	 * �bergeben
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void handleRegistrationAbschliessenFirmenkundenButton(ActionEvent event) {
		/*
		 * username zuerst auf false setzen, sofern nach dem Dr�cken auf den Button auf
		 * true gesetzt wurde
		 */
		this.usernameVorhanden = false;
		// aktuelle Kundenliste reinladen um Usernamevergleich zu starten

		// hier startet der Import der bestehenden Kundenliste
		List<Kunde> emptyKundenListe = new ArrayList<Kunde>();
		try {
			FileInputStream fis = new FileInputStream("Kundenliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			emptyKundenListe = (ArrayList<Kunde>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
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
				JOptionPane.showMessageDialog(null, "Es m�ssen alle Felder ausgef�llt werden");
			} else if (Integer.parseInt(alter.getText()) > 1700) {
				Kunde varfirmenkunde = new Firmenkunde(strasseUndNummer.getText(), ort.getText(),
						Integer.parseInt(plz.getText()), land.getText(), Integer.parseInt(alter.getText()),
						telefonNummer.getText(), email.getText(), username.getText(), passwort.getText(),
						kkInhaber.getText(), Long.parseLong(kkNummer.getText()), kkAblaufdatum.getText(),
						Integer.parseInt(kkPruefnummer.getText()), firmenname.getText());
				varfirmenkunde.registration(varfirmenkunde);
				((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
			} else {
				JOptionPane.showMessageDialog(null, "Das Gr�ndungsjahr muss korrekt angegeben werden");
			}
		}
	}
}
