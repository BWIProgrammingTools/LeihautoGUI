package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

	private MainKunde parent;

	private Stage outputStage;

	public Login() {

	}

	@FXML
	public boolean handleLogin() throws SQLException, IOException {

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
			// wenn username und passwort zusammen auf der Liste und der Kunde nicht
			// blockiert ist, geht es hier weiter
			if (emptyKundenListe.get(i).getUsername().compareTo(username.getText()) == 0
					&& emptyKundenListe.get(i).getPassword().compareTo(password.getText()) == 0
					&& emptyKundenListe.get(i).isGesperrt() == false) {

				// hier wird die ID des aktuell eingeloggten Users (mittels lokaler Variable) in
				// ein File geschrieben (muss Liste sein, da die weiterverwendung mittels
				// einfachem Integer nicht zu funktionieren scheint
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

			// die folgenden 3 else ifs funktionieren nicht - berger fragen
			if (emptyKundenListe.get(i).getUsername().compareTo(username.getText()) == 0
					&& emptyKundenListe.get(i).getPassword().compareTo(password.getText()) == 0
					&& emptyKundenListe.get(i).isGesperrt() == true) {
				JOptionPane.showMessageDialog(null, "Dein Account ist gesperrt, wende dich an die Hotlike 8008135!");
				return false;
			} else if (emptyKundenListe.get(i).getUsername().compareTo(username.getText()) == 0
					&& emptyKundenListe.get(i).getPassword().compareTo(password.getText()) != 0
					&& emptyKundenListe.get(i).isGesperrt() == true) {
				JOptionPane.showMessageDialog(null, "Falsches Passwort!");
				return false;
			} else if (emptyKundenListe.get(i).getUsername().compareTo(username.getText()) != 0
					&& emptyKundenListe.get(i).getPassword().compareTo(password.getText()) == 0
					&& emptyKundenListe.get(i).isGesperrt() == true) {
				JOptionPane.showMessageDialog(null, "Benutzername nicht gefunden!");
				return false;
			}
		}
		// wenn username und passwort nicht auffindbar sind, kommt hier ein fehler
		JOptionPane.showMessageDialog(null, "wrong password, biatch!");
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

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PasswordForgot.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Passwort vergessen");
		stage.setScene(new Scene(root, 450, 450));
		stage.showAndWait();
	}

	public void setParent(MainKunde main) {
		this.parent = main;
	}
}
