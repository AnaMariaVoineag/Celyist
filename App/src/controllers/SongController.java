package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
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
        img.setOnMouseClicked((MouseEvent event) -> playSong(song.getFilePath()));
    }

    private void playSong(String resourcePath) {
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
    }
}
