package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import application.Song;

public class SongController {
    @FXML
    private ImageView img;

    @FXML
    private Label songName;

    @FXML
    private Label artist;

    private MediaPlayer mediaPlayer;

    private boolean isPlaying = false;

    public void setData(Song song) {
        Image image = new Image(getClass().getResourceAsStream(song.getCover()));
        img.setImage(image);
        songName.setText(song.getName());
        artist.setText(song.getArtist());
        img.setOnMouseClicked((MouseEvent event) -> playSong(song));
    }

    private void playSong(Song song) {
        String resourcePath = song.getFilePath();
        try {
            if (mediaPlayer != null) {
                if (isPlaying) {
                    mediaPlayer.stop();
                    isPlaying = false;
                } else {
                    mediaPlayer.play();
                    isPlaying = true;
                }
            } else {
                Media media = new Media(getClass().getResource(resourcePath).toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                isPlaying = true;
            }
        } catch (MediaException | NullPointerException e) {
            warningAlert(new ActionEvent(), "The song " + song.getName() + " cannot be played.");
        }
    }

    private void warningAlert(ActionEvent event, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText(errorMessage);
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.setHeaderText("Warning alert");
        alert.getDialogPane().setPrefSize(480, 320);
        alert.showAndWait();
    }
}
