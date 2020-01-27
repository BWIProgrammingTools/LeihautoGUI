package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainKunde extends Application {

	private Stage primaryStage;
	private AnchorPane layout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Login");

		try {
		FXMLLoader loader = new FXMLLoader(
					MainKunde.class.getResource("Login.fxml"));
			layout = (AnchorPane) loader.load();
			
			Scene scene = new Scene(layout);
			//Klasse uebergeben
			Login login = loader.getController();
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.DECORATED);
			//parent uebergeben
			login.setParent(this);
			primaryStage.show();
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	public Stage getStage() {
		return primaryStage;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
