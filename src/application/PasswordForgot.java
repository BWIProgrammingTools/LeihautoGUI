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

	public PasswordForgot() {

	}

	/**
	 * Bei korrekter Eingabe der Daten wird ein Mail mit dem Passwort an die beim
	 * Kunden hinterlegte E-Mail Adresse versant. Sind die Daten falsch, wird eine
	 * MessageBox angezeigt und der Passwortcounter um 1 verringert. Die Szene wird
	 * in jedem Falle anschliessend beendet.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public boolean handlePasswordForgot(ActionEvent event) {

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
			// wenn username, email und alter korrekt sind, wird das pw ausgegeben
			if (emptyKundenListe.get(i).getUsername().compareTo(username.getText()) == 0
					&& emptyKundenListe.get(i).getEmail().compareTo(email.getText()) == 0
					&& emptyKundenListe.get(i).getAlter() == Integer.parseInt(alter.getText())) {

				// Counter zurücksetzen
				Login.setPasswortCounter(2);

				// messagebox
				JOptionPane.showMessageDialog(null,
						"Ein E-Mail mit dem Passwort wurde an die entsprechende Mailadresse verschickt");

				// hier wird das Mail verschickt
				JavaMail.sendPasswortVergessen(emptyKundenListe.get(i).getEmail(),
						emptyKundenListe.get(i).getPassword());
				// Fenster schliessen
				((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
				return true;
			}

		}
		// hier kommt die Meldung bei Falscheingabe
		if (Login.getPasswortCounter() > 0) {
			JOptionPane.showMessageDialog(null, "User nicht gefunden!\nSie haben noch " + Login.getPasswortCounter()
					+ " bevor die Funktion blockiert wird.");
			Login.setPasswortCounter(Login.getPasswortCounter() - 1);
		} else {
			JOptionPane.showMessageDialog(null,
					"User nicht gefunden!\nSie haben zu viele Falschversuche eingegeben. Diese Funktion wird nun deaktiviert.");
			Login.setPasswortCounter(Login.getPasswortCounter() - 1);
		}

		// Fenster schliessen
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
		return false;
	}
}
