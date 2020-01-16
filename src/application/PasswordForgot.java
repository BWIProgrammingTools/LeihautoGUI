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

	private Main parent;

	public PasswordForgot() {

	}

	@FXML
	public void handlePasswordForgot() {
		Kunde kunde = new Kunde();
		kunde.passwordVergessen(username.getText(), email.getText(), Integer.parseInt(alter.getText()));
	}
	
//	
//	public void passwordVergessen(String username, String email, int alter) { // kontrolle und wirklich void?
//		if (this.email == email) {
//			if (this.alter == alter) {
//				getPassword();
//			}
//		} else {
//			System.err.println("user nicht gefunden");
//			return;
//		}
//
//	}
	
}
