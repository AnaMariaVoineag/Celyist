package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.MusicGreetings;
import application.Song;
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

public class CelyistController extends Controller implements Initializable {
	@FXML
	private Label nameLabel;

	@FXML
	private Button darkTheme;
	private Stage stage;
	private Scene scene;

	@FXML
	private HBox recentlyPlayedSongs;

	@FXML
	private HBox favoriteSongs;

	List<Song> recentlyPlayed;
	List<Song> favorites;

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
	

	public void switchMode(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources_view/CelyistDark.fxml"));
		Parent root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		CelyistDarkController celyistDarkController = loader.getController();
		celyistDarkController.greetUser(Controller.username);
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
				loader.setLocation(getClass().getResource("/resources_view/SongLight.fxml"));

				VBox vBox = loader.load();
				SongController songController = loader.getController();
				songController.setData(song);

				recentlyPlayedSongs.getChildren().add(vBox);
			}

			for (Song song : favorites) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/resources_view/SongLight.fxml"));

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
		List<Song> songList = new ArrayList<>();

		Song song = new Song();
		song.setName("Escape");
		song.setArtist("Enrique Iglesias");
		song.setCover("/images/albums_good/escape.jpg");
		song.setFilePath("/songs/Escape.mp3");
		songList.add(song);

		song = new Song();
        song.setName("Donde Hay Musica");
        song.setArtist("Eros Ramazzotti");
        song.setCover("/images/albums_good/eros.jpg");
        song.setFilePath("/songs/Donde_Hay_Musica.mp3");
		songList.add(song);
		
		song = new Song();
        song.setName("Cloud 9");
        song.setArtist("Dove Cameron ft. Luke Benward");
        song.setCover("/images/albums_good/cloud9.jpeg");
        song.setFilePath("/songs/Cloud9.mp3");
        songList.add(song);

		song = new Song();
		song.setName("When The Rain Falls");
		song.setArtist("From Pixel Perfect Soundtrack");
		song.setCover("/images/albums_good/pp.jpg");
		song.setFilePath("/songs/When_The_Rain_Falls.mp3");
		songList.add(song);

		song = new Song();
		song.setName("Hice Todo Mal");
		song.setArtist("Benjamin Rojas");
		song.setCover("/images/albums_good/hice_todo_mal.jpg");
		song.setFilePath("/songs/Hice_Todo_Mal.mp3");
		songList.add(song);

		song = new Song();
		song.setName("Nobody's Perfect");
		song.setArtist("Hannah Montana");
		song.setCover("/images/albums_bad/nobody.jpg");
		songList.add(song);

		song = new Song();
		song.setName("Die Young");
		song.setArtist("Ke$ha");
		song.setCover("/images/albums_bad/kesha.jpg");
		songList.add(song);

		song = new Song();
		song.setName("Fire");
		song.setArtist("Mdot");
		song.setCover("/images/albums_bad/fire.jpg");
		songList.add(song);

		return songList;
	}

	public List<Song> getFavorites() {
		List<Song> songList = new ArrayList<>();

		Song song = new Song();
		song.setName("Double Take");
		song.setArtist("Austin Moon");
		song.setCover("/images/albums_good/austin_moon.jpg");
		song.setFilePath("/songs/Double_Take.mp3");
		songList.add(song);

		song = new Song();
		song.setName("Todo El Resto No Cuenta");
		song.setArtist("Lodovica Comello");
		song.setCover("/images/albums_good/lodo.jpg");
		song.setFilePath("/songs/Todo_el_resto_no_quenta.mp3");
		songList.add(song);

		song = new Song();
		song.setName("Gut, Dass Ihr Da Seid");
		song.setArtist("Roland Kaiser");
		song.setCover("/images/albums_good/roland_kaiser.jpg");
		song.setFilePath("/songs/Gut,_dass_ihr_da_seid.mp3");
		songList.add(song);

		song = new Song();
		song.setName("Hoy Somos Mas");
		song.setArtist("Martina Stoessel");
		song.setCover("/images/albums_good/tini.jpg");
		songList.add(song);

		song = new Song();
		song.setName("La Differenza Tra Me E Te");
		song.setArtist("Tiziano Ferro");
		song.setCover("/images/albums_good/tiziano.jpg");
		songList.add(song);

		return songList;
	}

}