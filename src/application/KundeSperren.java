package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Auto;
import model.Einzelkunde;
import model.Kunde;
import model.Reparaturen;
import model.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class KundeSperren implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	// Listen für ComboBoxen
	ObservableList<String> IdList = FXCollections.observableArrayList();

	@FXML
	private ComboBox<String> kundenIDBox;

	@FXML
	private TextArea sperrgrund;

	@FXML
	private Button kundesperren;

	// initialize für combobox
	public void initialize() {
		// hier findet die berechnung der Strings für die Combobox statt
		// Liste für Dropdown
		List<String> strings = new ArrayList<>();

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
			// hier wird der entsprechende Kunde gemäss ID gesperrt
			if (emptyKundenListe.get(i).isGesperrt() == false) {
				strings.add(Integer.toString(emptyKundenListe.get(i).getKundenNummer()));
			}
		}

		kundenIDBox.setItems(FXCollections.observableArrayList(strings));
	}

	private MainAdmin parent;

	public KundeSperren() {

	}

	@FXML
	public void handleKundeSperrenButton(ActionEvent event) {
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
			// hier wird der entsprechende Kunde gemäss ID gesperrt
			if (emptyKundenListe.get(i).getKundenNummer() == Integer.parseInt(kundenIDBox.getValue())) {
				emptyKundenListe.get(i).lockKunde(sperrgrund.getText().toString());
				System.out.println(
						"Der Kunde :" + emptyKundenListe.get(i) + " wurde mit folgender Begründung deaktiviert:");
				System.out.println(emptyKundenListe.get(i).getLockReason());
			}
		}

		// hier wird die aktualisierte kundenliste wieder herausgeschrieben
		try {
			FileOutputStream fos = new FileOutputStream("Kundenliste.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(emptyKundenListe);
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
	}
}
