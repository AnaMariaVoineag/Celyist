package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.MusicGreetings;
import application.Song;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CelyistController implements Initializable {
    @FXML
    private Label nameLabel;

    @FXML
    private Button darkMode;
    private Stage stage;
    private Scene scene;

    @FXML
    private HBox favoriteSongs;

    @FXML
    private HBox recentlyPlayedSongs;

    private List<Song> recentlyPlayed;
    private List<Song> favorites;

    private MusicGreetings songGreeting() {
        String regex = "^[a-z].*";
        Pattern pattern = Pattern.compile(regex);
        List<MusicGreetings> musicGreetings = new ArrayList<>();

        for (MusicGreetings greeting : MusicGreetings.values()) {
            String value = greeting.getSongTitle();
            Matcher matcher = pattern.matcher(value);
            if (matcher.matches()) {
                musicGreetings.add(greeting);
            }
        }

        Random random = new Random();
        int randomIndex = random.nextInt(musicGreetings.size());
        return musicGreetings.get(randomIndex);
    }

    public void greetUser(String username) {
        MusicGreetings greeting = songGreeting();
        String greetingTitle = capitalizeFirstLetter(greeting.getSongTitle());
        nameLabel.setText(greetingTitle + ", " + username + "!");
    }

    private String capitalizeFirstLetter(String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split(" ");

        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String remainingLetters = word.substring(1).toLowerCase();
                result.append(firstLetter).append(remainingLetters).append(" ");
            }
        }

        return result.toString().trim();
    }

    public void nightMode(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources_view/CelyistDarkMode.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Celyist | Dark Mode");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recentlyPlayed = new ArrayList<>(getRecentlyPlayed());
        favorites = new ArrayList<>(getFavorites());

        try {
            for (Song song : recentlyPlayed) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/resources_view/Song.fxml"));

                VBox vBox = loader.load();
                SongController songController = loader.getController();
                songController.setData(song);

                recentlyPlayedSongs.getChildren().add(vBox);
            }

            for (Song song : favorites) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/resources_view/Song.fxml"));

                VBox vBox = loader.load();
                SongController songController = loader.getController();
                songController.setData(song);

                favoriteSongs.getChildren().add(vBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Song> getRecentlyPlayed() {
        List<Song> celSongs = new ArrayList<>();

        Song song = new Song();
        song.setName("Cloud 9");
        song.setArtist("Dove Cameron ft. Luke Benward");
        song.setCover("/images/albums_good/cloud9.jpeg");
        song.setFilePath("/songs/Cloud9.mp3");
        celSongs.add(song);

        song = new Song();
        song.setName("Donde Hay Musica");
        song.setArtist("Eros Ramazzotti");
        song.setCover("/images/albums_good/eros.jpg");
        song.setFilePath("/songs/Donde_Hay_Musica.mp3");
        celSongs.add(song);

        song = new Song();
        song.setName("Double Take");
        song.setArtist("Austin Moon");
        song.setCover("/images/albums_good/austin_moon.jpg");
        song.setFilePath("/songs/Double_Take.mp3");
        celSongs.add(song);

        song = new Song();
        song.setName("Grita");
        song.setArtist("Cast of Bia");
        song.setCover("/images/albums_good/grita.jpg");
        song.setFilePath("/songs/Grita.mp3");
        celSongs.add(song);

        song = new Song();
        song.setName("I'm Still Good");
        song.setArtist("Hannah Montana");
        song.setCover("/images/albums_good/hm.jpg");
        song.setFilePath("/songs/Im_Still_Good.mp3");
        celSongs.add(song);

        song = new Song();
        song.setName("Vamos al Cielo");
        song.setArtist("Benjamin Rojas");
        song.setCover("/images/albums_good/vamos_al_cielo.jpg");
        song.setFilePath("/songs/Vamos_al_Cielo.mp3");
        celSongs.add(song);

        return celSongs;
    }

    private List<Song> getFavorites() {
        List<Song> favSongs = new ArrayList<>();

        Song song = new Song();
        song.setName("Todo El Resto No Cuenta");
        song.setArtist("Lodovica Comello");
        song.setCover("/images/albums_good/lodo.jpg");
        song.setFilePath("/songs/Todo_el_resto_no_quenta.mp3");
        favSongs.add(song);
        
        song = new Song();
        song.setName("Hice Todo Mal");
        song.setArtist("Benjamin Rojas");
        song.setCover("/images/albums_good/hice_todo_mal.jpg");
        song.setFilePath("/songs/Hice_Todo_Mal.mp3");
        favSongs.add(song);
        
        song = new Song();
        song.setName("When The Rain Falls");
        song.setArtist("From Pixel Perfect Soundtrack");
        song.setCover("/images/albums_good/pp.jpg");
        song.setFilePath("/songs/When_The_Rain_Falls.mp3");
        favSongs.add(song);

        return favSongs;
    }
}
