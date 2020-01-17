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
	private Button reg;
	@FXML
	private Button forgot;

	private Main parent;
	private Stage outputStage;

	public Login() {

	}

	@FXML
	public void handleLogin() {
		// System.out.println(username.getText());
		// System.out.println(password.getText());
		Kunde kunde = new Kunde(username.getText(), "1234"); // hier muss vergleich mit liste rein

		kunde.login(password.getText());

	}

	@FXML
	//neues Fenster (Registration) öffnen
	public void handleRegistrationButton() throws SQLException, IOException { 

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registration.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Kundenregistration");
		stage.setScene(new Scene(root, 700, 700));
		stage.showAndWait();
	}

	@FXML
	//neues Fenster (pw vergessen) öffnen
	public void handlePasswordForgotButton() throws SQLException, IOException { 

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PasswordForget.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("Passwort vergessen");
		stage.setScene(new Scene(root, 450, 450));
		stage.showAndWait();
	}

	public void setParent(Main main) {
		this.parent = main;
	}
}
