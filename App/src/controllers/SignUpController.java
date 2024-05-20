package controllers;

import application.OopsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import service.UserService;

import java.io.IOException;

public class SignUpController extends Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameRegistrationField;

    @FXML
    private PasswordField passwordField;

    public void signUpButton(ActionEvent event) {
        UserService userService = new UserService();
        try {
            userService.addUser(usernameRegistrationField.getText(), passwordField.getText(),
                    firstNameField.getText(), lastNameField.getText());
            System.out.println("User registered successfully!");
            informationAlert(event);
        } catch (OopsException e) {
            String errorMessage = e.getMessage();
            System.out.println("Error: " + errorMessage);
            warningAlert(event, errorMessage);
        }
    }

    public void warningAlert(ActionEvent event, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText(errorMessage);
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.setHeaderText("Warning alert");
        alert.getDialogPane().setPrefSize(480, 320);
        alert.showAndWait();
    }

    @FXML
    public void informationAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Yayy! User " + usernameRegistrationField.getText() + " registered successfully!");
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.setHeaderText("Information alert");
        alert.getDialogPane().setPrefSize(480, 320);
        alert.showAndWait();
    }

    public void goBack(ActionEvent event) {
        mediaPlayer.stop();
        try {
            root = FXMLLoader.load(getClass().getResource("/resources_view/LoginScreen.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInformation(MediaPlayer mediaPlayer) {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }

        this.mediaPlayer = mediaPlayer;

        mediaPlayer.setOnEndOfMedia(() -> {
            nextMedia(); // Play the next song when the current song ends
            mediaPlayer.setOnEndOfMedia(null); // Reset the event handler
        });

        playMedia();
    }

}
