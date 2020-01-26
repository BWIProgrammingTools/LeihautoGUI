package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Kunde;

public class Login {

	@FXML
	private Button login;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Button registrationEinzel;
	@FXML
	private Button registrationFirma;
	@FXML
	private Button forgot;

	private static int passwortCounter = 2;

	@SuppressWarnings("unused")
	private MainKunde parent;

	public Login() {

	}

	public static int getPasswortCounter() {
		return passwortCounter;
	}

	public static void setPasswortCounter(int passwortCounter) {
		Login.passwortCounter = passwortCounter;
	}

	@SuppressWarnings("unchecked")
	@FXML
	public boolean handleLogin() throws SQLException, IOException {

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
			// wenn username und passwort zusammen auf der Liste und der Kunde nicht
			// blockiert ist, geht es hier weiter
			if (emptyKundenListe.get(i).getUsername().compareTo(username.getText()) == 0
					&& emptyKundenListe.get(i).getPassword().compareTo(password.getText()) == 0
					&& emptyKundenListe.get(i).isGesperrt() == false) {

				/*
				 * hier wird die ID des aktuell eingeloggten Users (mittels lokaler Variable) in
				 * ein File geschrieben (muss Liste sein, da die weiterverwendung mittels
				 * einfachem Integer nicht zu funktionieren scheint
				 */
				List<Integer> eingeloggterUserIDList = new ArrayList<Integer>();
				eingeloggterUserIDList.add(emptyKundenListe.get(i).getKundenNummer());

				// hier wird das von Datum in eine Liste geschrieben
				try {
					FileOutputStream fos = new FileOutputStream("EingeloggterUserList.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					// write object to file
					oos.writeObject(eingeloggterUserIDList);
					// closing resources
					oos.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				// hier wird das neue Fenster Kundenportal geöffnet
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KundenPortal.fxml"));
				Parent root = fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setOpacity(1);
				stage.setTitle("Kundenportal");
				stage.setScene(new Scene(root, 700, 700));
				stage.showAndWait();
				return true;

			}

			// Fehleingaben abfangen
			if (emptyKundenListe.get(i).getUsername().compareTo(username.getText()) == 0
					&& emptyKundenListe.get(i).getPassword().compareTo(password.getText()) == 0
					&& emptyKundenListe.get(i).isGesperrt()) {
				JOptionPane.showMessageDialog(null,
						"Dein Account wurde gesperrt, wende dich an die Hotline 8008135! \nDer Grund lautet: "
								+ emptyKundenListe.get(i).getLockReason());
				return false;
			}
		}
		// wenn username und passwort nicht auffindbar sind, kommt hier ein fehler
		JOptionPane.showMessageDialog(null, "Benutzer nicht gefunden");
		return false;
	}

	@FXML
	// neues Fenster (Registration) öffnen
	public void handleRegistrationButtonEinzel() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrationEinzel.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Kundenregistration");
		stage.setScene(new Scene(root, 700, 700));
		stage.showAndWait();
	}

	@FXML

	public void handleRegistrationButtonFirma() throws SQLException, IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrationFirma.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Firmenregistration");
		stage.setScene(new Scene(root, 700, 700));
		stage.showAndWait();
	}

	@FXML
	// neues Fenster (pw vergessen) öffnen
	public void handlePasswordForgotButton() throws SQLException, IOException {

		if (passwortCounter >= 0) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PasswordForgot.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOpacity(1);
			stage.setTitle("Passwort vergessen");
			stage.setScene(new Scene(root, 450, 450));
			stage.showAndWait();
		} else {
			JOptionPane.showMessageDialog(null,
					"Sie haben zu viele falsche Versuche eingegeben.\nDiese Funktion ist deaktiviert.");
		}
	}

	public void setParent(MainKunde main) {
		this.parent = main;
	}
}
