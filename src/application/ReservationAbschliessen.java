package application;

import java.io.FileInputStream;

import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Auto;
import model.Einzelkunde;
import model.Firmenkunde;
import model.Kunde;
import model.Reservation;
import javafx.scene.control.ComboBox;

public class ReservationAbschliessen implements Serializable {

	private static final long serialVersionUID = -299482035708790407L;

	// Listen für ComboBoxen
	ObservableList<String> IdList = FXCollections.observableArrayList();

	@FXML
	private ComboBox<String> reservationsIDBox;

	@FXML
	private Button reservationabschliessen;

	@FXML
	private TextField reserviertVon;

	@FXML
	private TextField reserviertBis;

	@FXML
	private TextField gesamtKosten;

	@FXML
	private TextField marke;

	@FXML
	private TextField farbe;

	@FXML
	private TextField getriebeart;

	@FXML
	private TextField treibstoffart;

	@FXML
	private TextField tagesSatz;

	@FXML
	private TextField kundenID;

	@FXML
	private TextField nameOderFirmenname;

	@FXML
	private TextField vorname;

	@FXML
	private TextField ort;

	@FXML
	private TextField vornameFahrer;

	@FXML
	private TextField nachnameFahrer;

	@FXML
	private TextField fuehrerausweisFahrer;

	@FXML
	private TextField reparaturKosten;

	@FXML
	private TextField verzugsTage;

	@FXML
	private TextField sonstigeKosten;

	// Variable, da mit lokaler nicht funktioniert
	private String mail;

	/**
	 * Initialize für die aktuelle Szene mit den ReservationsIDs der offenen
	 * Reservationen in der Combobox
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		// hier wird eine leere ArrayList erstellt
		// hier findet die berechnung der Strings für die Combobox statt

		// Liste für Dropdown
		List<String> strings = new ArrayList<>();

		// hier startet der Import der bestehenden Reservationsliste
		List<Reservation> emptyReservationsListe = new ArrayList<Reservation>();
		try {
			FileInputStream fis = new FileInputStream("Reservationsliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			emptyReservationsListe = (ArrayList<Reservation>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * hier wird mit einer for Schlaufe durch die importierte Reservationsliste
		 * iteriert
		 */
		for (int i = 0; i < emptyReservationsListe.size(); i++) {
			/*
			 * hier werden die IDs der ComboBox hinzugefügt, wo der Boolean istGereinigt
			 * false ist
			 */
			if (emptyReservationsListe.get(i).isIstGereinigt() == false) {
				strings.add(Integer.toString(emptyReservationsListe.get(i).getReservationsID()));

			}
		}

		reservationsIDBox.setItems(FXCollections.observableArrayList(strings));
	}

	/**
	 * Hier werden die Textfelder der Szene anhand der gewählten ReservationsID
	 * gesetzt
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void zeigeReservationsAngaben() {
		// hier startet der Import der bestehenden Kundenliste
		List<Reservation> emptyReservationsListe = new ArrayList<Reservation>();
		try {
			FileInputStream fis = new FileInputStream("Reservationsliste.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// write object to file
			emptyReservationsListe = (ArrayList<Reservation>) ois.readObject();
			// closing resources
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * hier wird mit einer for Schlaufe durch die importierte Reservationsliste
		 * iteriert
		 */
		for (int i = 0; i < emptyReservationsListe.size(); i++) {
			// hier wird die entsprechende Reservation gemäss Dropdown ausgewählt
			if (emptyReservationsListe.get(i).getReservationsID() == Integer.parseInt(reservationsIDBox.getValue())) {
				// hier werden die ersten Textfelder beschrieben
				reserviertVon.setText(String.valueOf(emptyReservationsListe.get(i).getReservationVon().getTime()));
				reserviertBis.setText(String.valueOf(emptyReservationsListe.get(i).getReservationBis().getTime()));
				gesamtKosten.setText(String.valueOf(emptyReservationsListe.get(i).getReservationsKosten()));
				nachnameFahrer.setText(emptyReservationsListe.get(i).getFahrerNachname());
				vornameFahrer.setText(emptyReservationsListe.get(i).getFahrerVorname());
				fuehrerausweisFahrer.setText(String.valueOf(emptyReservationsListe.get(i).getFuehrerausweisNummer()));

				/*
				 * hier wird eine lokal Variable für die entsprechende AutoID der Reservation
				 * vergeben
				 */
				int autoID = emptyReservationsListe.get(i).getAutoID();

				// hier wird wieder die komplette Autoliste reingeladen

				// hier startet der Import der bestehenden Autoliste
				List<Auto> emptyAutoListe = new ArrayList<Auto>();
				try {
					FileInputStream fis = new FileInputStream("Autoliste.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					// write object to file
					emptyAutoListe = (ArrayList<Auto>) ois.readObject();
					// closing resources
					ois.close();
					fis.close();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}

				// hier wird mit einer for Schlaufe durch die importierte Autoliste iteriert
				for (int ii = 0; ii < emptyAutoListe.size(); ii++) {
					// hier werden die entsprechenden Felder beschrieben
					if (emptyAutoListe.get(ii).getId() == autoID) {
						marke.setText(emptyAutoListe.get(ii).getMarke());
						farbe.setText(emptyAutoListe.get(ii).getFarbe());
						getriebeart.setText(emptyAutoListe.get(ii).getGetriebe());
						treibstoffart.setText(emptyAutoListe.get(ii).getTreibstoff());
						tagesSatz.setText(String.valueOf(emptyAutoListe.get(ii).getKostenProTag()));

					}
				}

				/*
				 * hier wird eine lokal Variable für die entsprechende AutoID der Reservation
				 * vergeben
				 */
				int tempKundenID = emptyReservationsListe.get(i).getKundenNummer();

				// hier wird wieder die komplette Autoliste reingeladen

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
				for (int iii = 0; iii < emptyKundenListe.size(); iii++) {
					// hier wird der entsprechende Kunde gemäss ID gesperrt
					if (emptyKundenListe.get(iii).getKundenNummer() == tempKundenID
							&& emptyKundenListe.get(iii) instanceof Einzelkunde) {
						kundenID.setText(String.valueOf(emptyKundenListe.get(iii).getKundenNummer()));
						nameOderFirmenname.setText(((Einzelkunde) emptyKundenListe.get(iii)).getNachname());
						vorname.setText(((Einzelkunde) emptyKundenListe.get(iii)).getVorname());
						ort.setText(emptyKundenListe.get(iii).getOrt());

					} else if (emptyKundenListe.get(iii).getKundenNummer() == tempKundenID
							&& emptyKundenListe.get(iii) instanceof Firmenkunde) {
						kundenID.setText(String.valueOf(emptyKundenListe.get(iii).getKundenNummer()));
						nameOderFirmenname.setText(((Firmenkunde) emptyKundenListe.get(iii)).getFirmenname());
						vorname.setText("");
						ort.setText(emptyKundenListe.get(iii).getOrt());
					}
				}
			}
		}
	}

	/**
	 * Sofern alle notwendigen Felder ausgefüllt wurden, wird Reservationsliste
	 * importiert und die entsprechenden Kosten berechnet. Sofern die Reservation
	 * für die Reparatur angemeldet wurde, werden die Reparaturkosten gemäss
	 * TextField-Eingabe berücksichtigt und mit den Verzugskosten (berechnet anhand
	 * der Anzahl Verzugstage*(Pauschale von 100+Reservationskosten pro Tag)) und
	 * weiteren Kosten zusammengezählt. Dieser Betrag wird von der
	 * Sicherheitsleistung über 1000 subtrahiert. Ist der Betrag grösser als 0,
	 * müssen wir dem Kunden eine Rückvergütung vornehmen und falls er unter 0 ist,
	 * wird der Betrag *-1 gerechnet, was dem vom Kunden geschuldeten Betrag
	 * entspricht. Alle Infos werden in den entsprechenden Attributen der
	 * Reservation gesetzt, ein .docx wird erstellt (es werden nur die Kosten
	 * aufgelistet, welche nicht = 0 sind), am vorgegebenen Speicherort abgelegt und
	 * dem Kunden wird eine Abschlussbestätigung per Mail versandt mit dem Dokument
	 * als Anhang. Am Schluss erscheint eine Messagebox und die Szene schliesst
	 * sich.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	public void handleReservationAbschliessenButton(ActionEvent event) {
		if (reparaturKosten.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Der Betrag für die Reparaturkosten muss erfasst oder auf 0 gesetzt werden");
		} else if (verzugsTage.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Die Anzahl Verzugstage muss eingegeben oder auf 0 gesetzt werden");
		} else if (sonstigeKosten.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Der Betrag für die sonstigen Kosten muss erfasst oder auf 0 gesetzt werden");
		} else {

			// hier startet der Import der bestehenden Kundenliste
			List<Reservation> emptyReservationsListe = new ArrayList<Reservation>();
			try {
				FileInputStream fis = new FileInputStream("Reservationsliste.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				// write object to file
				emptyReservationsListe = (ArrayList<Reservation>) ois.readObject();
				// closing resources
				ois.close();
				fis.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			/*
			 * hier wird mit einer for Schlaufe durch die importierte Reservationsliste
			 * iteriert
			 */
			for (int i = 0; i < emptyReservationsListe.size(); i++) {

				// wenn username, email und alter korrekt sind, wird das pw ausgegeben
				if (emptyReservationsListe.get(i).getReservationsID() == Integer
						.parseInt(reservationsIDBox.getValue())) {

					// hier wird der boolean needsReparatur in der Klasse Reservation nach der
					// Reparatur wieder auf false geändert
					if (emptyReservationsListe.get(i).isInReparatus()) {
						emptyReservationsListe.get(i).setInReparatus(false);

						// hier werden die entsprechenden Reparaturkosten dem Attribut hinzugefügt und
						// die Kostenberechung für die Reparatur findet statt
						emptyReservationsListe.get(i).setReparaturKosten(Double.parseDouble(reparaturKosten.getText()));
					}

					// hier wird der Status der Reinigung wieder auf true gesetzt
					emptyReservationsListe.get(i).setIstGereinigt(true);

					/*
					 * hier wird eine lokal Variable für die entsprechende AutoID und die KundenID
					 * der Reservation vergeben
					 */
					int autoID = emptyReservationsListe.get(i).getAutoID();

					/*
					 * hier wird wieder die komplette Autoliste reingeladen, um den boolean
					 * blockiert auf dem Auto mit der entsprechenden ID zu setzen
					 */

					// hier startet der Import der bestehenden Autoliste
					List<Auto> emptyAutoListe = new ArrayList<Auto>();
					try {
						FileInputStream fis = new FileInputStream("Autoliste.ser");
						ObjectInputStream ois = new ObjectInputStream(fis);
						// write object to file
						emptyAutoListe = (ArrayList<Auto>) ois.readObject();
						// closing resources
						ois.close();
						fis.close();
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}

					// hier wird mit einer for Schlaufe durch die importierte Autoliste iteriert
					for (int ii = 0; ii < emptyAutoListe.size(); ii++) {
						/*
						 * hier wird der boolean blockiert in der Klasse Auto nach der Reparatur wieder
						 * auf false gesetzt. Wenn die Reparatur abgeschlossen ist, muss man das wieder
						 * ändern (Reservation beendet Button)
						 */
						if (emptyAutoListe.get(ii).getId() == autoID) {
							emptyAutoListe.get(ii).setBlockiert(false);

							// hier wird eine lokale Variable für die berechnung der verzugskosten erstellt
							double verzugsKosten = (emptyAutoListe.get(ii).getKostenProTag()
									* Integer.parseInt(verzugsTage.getText()))
									+ (100 * Integer.parseInt(verzugsTage.getText()));
							emptyReservationsListe.get(i).setVerzugskosten(verzugsKosten);
							emptyReservationsListe.get(i).setAnzahlVerzugsTage(Integer.parseInt(verzugsTage.getText()));
							emptyReservationsListe.get(i).setSonstigeKosten(Integer.parseInt(sonstigeKosten.getText()));

							/*
							 * hier wird eine lokale Variable für die Restkosten (Reparatur+sonstige Kosten
							 * + Verzugskosten bestimmt
							 */
							double restKosten = emptyReservationsListe.get(i).getSicherheitsLeistung()
									- emptyReservationsListe.get(i).getReparaturKosten()
									- emptyReservationsListe.get(i).getSonstigeKosten()
									- emptyReservationsListe.get(i).getVerzugskosten();
							if (restKosten >= 0) {
								// Close Kommentar
								JOptionPane.showMessageDialog(null, "Die Reservation mit der Nummer "
										+ emptyReservationsListe.get(i).getReservationsID()
										+ " wurde abgeschlossen.\nDas Abschlussdokument wurde lokal abgespeichert und dem Kunden per Mail zugestellt.\nDem Kunden muss CHF "
										+ restKosten + " rückvergütet werden.");
							} else {
								// Close Kommentar
								JOptionPane.showMessageDialog(null, "Die Reservation mit der Nummer "
										+ emptyReservationsListe.get(i).getReservationsID()
										+ " wurde abgeschlossen.\nDas Abschlussdokument wurde lokal abgespeichert und dem Kunden per Mail zugestellt.\nDem Kunden muss eine Rechnung über CHF "
										+ (restKosten * -1) + " gesandt werden.");
							}
							emptyReservationsListe.get(i).setEndkosten(restKosten);

							// .docx Dokument erzeugen
							@SuppressWarnings("resource")
							XWPFDocument document = new XWPFDocument();

							XWPFParagraph paragraph1 = document.createParagraph();
							XWPFRun run1 = paragraph1.createRun();
							run1.setText("Abschlussbestätigung der Reservation ID: "
									+ emptyReservationsListe.get(i).getReservationsID());
							run1.setFontSize(14);
							run1.setBold(true);
							paragraph1.setBorderTop(Borders.BASIC_THIN_LINES);
							paragraph1.setBorderBottom(Borders.BASIC_THIN_LINES);
							paragraph1.setAlignment(ParagraphAlignment.CENTER);

							XWPFParagraph paragraph2 = document.createParagraph();
							XWPFRun run2 = paragraph2.createRun();
							run2.addBreak();
							run2.addBreak();
							run2.addBreak();
							run2.setText(
									"Die Reservation ist abgeschlossen und die Kosten belaufen sich auf nachfolgende Beträge.");

							XWPFParagraph paragraph3 = document.createParagraph();
							XWPFRun run3 = paragraph3.createRun();
							run3.setText("Reservationskosten: CHF " + gesamtKosten.getText());
							run3.addBreak();
							run3.setText("Dieser Betrag wurde bereits beglichen.");
							paragraph3.setNumID(BigInteger.ONE);

							XWPFParagraph paragraph4 = document.createParagraph();
							XWPFRun run4 = paragraph4.createRun();
							run4.setText("Sicherheitsleistung: CHF "
									+ emptyReservationsListe.get(i).getSicherheitsLeistung());
							run4.addBreak();
							run4.setText(
									"Dieser Betrag wurde als Anzahlung für möglich anfallende weitere Kosten hinterlegt.");
							paragraph4.setNumID(BigInteger.ONE);

							if (emptyReservationsListe.get(i).getReparaturKosten() != 0) {
								XWPFParagraph paragraph5 = document.createParagraph();
								XWPFRun run5 = paragraph5.createRun();
								run5.setText(
										"Reparaturkosten: CHF " + emptyReservationsListe.get(i).getReparaturKosten());
								paragraph5.setNumID(BigInteger.ONE);
							}

							if (emptyReservationsListe.get(i).getVerzugskosten() != 0) {
								XWPFParagraph paragraph6 = document.createParagraph();
								XWPFRun run6 = paragraph6.createRun();
								run6.setText("Verzugskosten: CHF " + emptyReservationsListe.get(i).getVerzugskosten());
								run6.addBreak();
								run6.setText(
										"Dieser Betrag berechnet sich anhand des Tagespreises + Aufschlag von CHF 100.00 x Verzugstage");
								paragraph6.setNumID(BigInteger.ONE);
							}

							if (emptyReservationsListe.get(i).getSonstigeKosten() != 0) {
								XWPFParagraph paragraph7 = document.createParagraph();
								XWPFRun run7 = paragraph7.createRun();
								run7.setText(
										"Weitere Kosten: CHF " + emptyReservationsListe.get(i).getSonstigeKosten());
								run7.addBreak();
								run7.setText("Diese Kosten sind durch die Verletzung der AGBs entstanden");
								run7.addBreak();
								paragraph7.setNumID(BigInteger.ONE);
							}

							XWPFParagraph paragraph8 = document.createParagraph();
							XWPFRun run8 = paragraph8.createRun();
							if (restKosten < 0) {
								run8.setText("Ihre Schulden betragen CHF " + restKosten * -1);
								run8.addBreak();
								run8.addBreak();
								run8.setText(
										"Bitte überweisen Sie den ausstehenden Betrag innert 30 Tagen gemäss beiliegendem Einzahlungsschein.");
							} else {
								run8.addBreak();
								run8.setText("Ihr Restguthaben beträgt CHF " + restKosten);
								run8.addBreak();
								run8.addBreak();
								run8.setText("Wir werden Ihnen das Guthaben schnellstmöglich zurückerstatten.");
							}
							run8.addBreak();
							run8.addBreak();
							run8.setText("Besten Dank für Ihre Reservation.");
							run8.addBreak();
							run8.addBreak();
							run8.addBreak();
							run8.setText("Leihauto Team");

							try {
								FileOutputStream output = new FileOutputStream(
										"C:\\Users\\ninos\\OneDrive\\Desktop\\Leihautodocs\\Abschlussdocs\\"
												+ emptyReservationsListe.get(i).getReservationsID()
												+ "_Abschlussdokument.docx");
								document.write(output);
								output.close();

							} catch (Exception e) {
								e.printStackTrace();
							}

							/*
							 * hier wird das entsprechende Mail verschickt (zuerst muss die Kundenliste
							 * reingeladen werden)
							 */

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
							for (int iii = 0; iii < emptyKundenListe.size(); iii++) {
								// hier wird der entsprechende Kunde gemäss ID gesperrt
								if (emptyKundenListe.get(iii).getKundenNummer() == emptyReservationsListe.get(i)
										.getKundenNummer()) {
									mail = emptyKundenListe.get(iii).getEmail();
								}
							}
							// hier wird das Mail versandt
							JavaMail.sendAbschlussbestaetigung(mail, emptyReservationsListe.get(i).getReservationsID());

						}
					}

					// hier wird die aktualisierte Autoliste wieder herausgeschrieben
					try {
						FileOutputStream fos = new FileOutputStream("Autoliste.ser");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						// write object to file
						oos.writeObject(emptyAutoListe);
						// closing resources
						oos.close();
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			}

			// hier wird die aktualisierte Reservationsliste wieder herausgeschrieben
			try {
				FileOutputStream fos = new FileOutputStream("Reservationsliste.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				// write object to file
				oos.writeObject(emptyReservationsListe);
				// closing resources
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Fenster schliessen
			((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
		}
	}
}