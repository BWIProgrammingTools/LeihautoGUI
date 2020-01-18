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
import model.Kunde;

public class PasswordForgot {

	@FXML
	private TextField username;
	@FXML
	private TextField email;
	@FXML
	private TextField alter;
	@FXML
	private Button forgot;

	private MainKunde parent;

	public PasswordForgot() {

	}

	@FXML
	public boolean handlePasswordForgot() {

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
			// wenn username, email und alter korrekt sind, wird das pw ausgegeben
			if (emptyKundenListe.get(i).getUsername().compareTo(username.getText()) == 0
					&& emptyKundenListe.get(i).getEmail().compareTo(email.getText()) == 0
					&& emptyKundenListe.get(i).getAlter() == Integer.parseInt(alter.getText())) {

				JOptionPane.showMessageDialog(null, emptyKundenListe.get(i).getPassword());
				return true;
				// wenn username und passwort zwar stimmen aber der Kunde blockiert ist, kommt
				// hier eine Fehlermeldung
			}

		}
		// wenn username und passwort nicht auffindbar sind, kommt hier ein fehler
		JOptionPane.showMessageDialog(null, "User nicht gefunden!");
		return false;
	}
}
