package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import service.UserService;
import javafx.scene.Node;

public class Controller implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private PasswordField enterPasswordField;
	
	@FXML
	private TextField firstNameLabel;

	@FXML
	private TextField lastNameLabel;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField usernameRegistrationLabel;
	
	
	public void switchToScene1(ActionEvent event) throws IOException {
		
		 root = FXMLLoader.load(getClass().getResource("/resources_view/LoginScreen.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	public void switchToScene2(ActionEvent event) throws Exception {
		UserService userService = new UserService();
		if (userService.findUser(usernameTextField.getText(), enterPasswordField.getText()) != null){
			 root = FXMLLoader.load(getClass().getResource("/resources_view/Celyist.fxml"));
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show();
		}
	}
	
	public void registerButton(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("/resources_view/Register.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	@FXML
	private Pane pane;
	@FXML
	private Button nextButton;

	private Media media;
	private MediaPlayer mediaPlayer;

	private File directory;
	private File[] files;

	private ArrayList<File> songs;
	private int songNumber;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		songs = new ArrayList<File>();

		directory = new File("music");

		files = directory.listFiles();

		if (files != null) {
			for (File file : files) {
				songs.add(file);
				System.out.println(file);
			}
		}

		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		mediaPlayer.setOnEndOfMedia(() -> {
	        nextMedia();
	    });
		
		playMedia(); // Calling this method here so the music can play when we hit the run button
	}

	public void playMedia() {
		mediaPlayer.play();
	}

	public void nextMedia() {
		if (songNumber < songs.size() - 1) {
			songNumber++; // move to the next song

			mediaPlayer.stop();

			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			// Calling the playMedia() method and the song will play automatically
			playMedia();

		} else {
			songNumber = 0; // move to the first song

			mediaPlayer.stop();

			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			// Calling the playMedia() and the you will return to the first song
			playMedia();
		}
	}
	

}
