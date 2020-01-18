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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AutoErfassen {

	@FXML
	private TextField marke;
	@FXML
	private TextField farbe;
	@FXML
	private TextField getriebe;
	@FXML
	private TextField treibstoff;
	@FXML
	private ComboBox getriebeCombo;
	@FXML
	private ComboBox treibstoffCombo;
	@FXML
	private Button autoerfassen;

	private MainAdmin parent;

	public AutoErfassen() {

	}
	
	public void handleAutoErfassen() {
		
	}

}
