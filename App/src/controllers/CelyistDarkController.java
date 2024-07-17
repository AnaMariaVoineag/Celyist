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

public class CelyistDarkController extends Controller implements Initializable {
	@FXML
	private Label nameLabel;

	@FXML
    private Button lightTheme;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources_view/Celyist.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        CelyistController celyistController = loader.getController();
		celyistController.greetUser(Controller.username);
        stage.setTitle("Celyist | Light Mode");
        stage.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        recentlyPlayed = new ArrayList<>(getRecentlyPlayed());
        favorites = new ArrayList<>(getFavorites());

        try {
            for (Song song : recentlyPlayed) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/resources_view/SongDark.fxml"));

                VBox vBox = loader.load();
                SongController songController = loader.getController();
                songController.setData(song);

                recentlyPlayedSongs.getChildren().add(vBox);
            }
            
            for (Song song : favorites) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/resources_view/SongDark.fxml"));

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
		song.setName("Anti Hero");
		song.setArtist("Taylor Swift");
		song.setCover("/images/albums_bad/anti_hero.jpg");
		songList.add(song);

		song = new Song();
		song.setName("Steal Your Heart");
		song.setArtist("Austin Moon");
		song.setCover("/images/albums_bad/steal_your_heart.jpg");
		songList.add(song);

		song = new Song();
		song.setName("Freak The Freak Out");
		song.setArtist("Victoria Justice");
		song.setCover("/images/albums_bad/vj.jpg");
		songList.add(song);

		song = new Song();
		song.setName("She's On Fire");
		song.setArtist("Steven Stern");
		song.setCover("/images/albums_bad/she_is_on_fire.jpg");
		song.setFilePath("/songs/She's_On_Fire.mp3");
		songList.add(song);

		song = new Song();
		song.setName("Se Termino");
		song.setArtist("Benjamin Rojas");
		song.setCover("/images/albums_bad/se_termino.jpg");
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
	
	public List<Song> getFavorites(){
		List<Song> songList = new ArrayList<>();

		Song song = new Song();
		song.setName("Mr. Brightside");
		song.setArtist("The Killers");
		song.setCover("/images/albums_bad/mr.jpg");
		song.setFilePath("/songs/Mr._Brightside.mp3");
		songList.add(song);
		
		song = new Song();
		song.setName("Determinate");
		song.setArtist("Lemonade Mouth");
		song.setCover("/images/albums_bad/determinate.jpg");
		songList.add(song);
		
		song = new Song();
		song.setName("Rain Over Me");
		song.setArtist("Pitbull ft. Marc Anthony");
		song.setCover("/images/albums_bad/rain_over_me.jpg");
		song.setFilePath("/songs/Rain_Over_Me.mp3");
		songList.add(song);
		
		song = new Song();
		song.setName("International Love");
		song.setArtist("Pitbull ft. Chris Brown");
		song.setCover("/images/albums_bad/international_love.gif");
		song.setFilePath("/songs/International_Love.mp3");
		songList.add(song);
		
		song = new Song();
		song.setName("Even If It Kills Me");
		song.setArtist("Caitlyn Taylor Love");
		song.setCover("/images/albums_bad/caitlyn.jpg");
		song.setFilePath("/songs/Even_If_It_Kills Me.mp3");
		songList.add(song);
		
		song = new Song();
		song.setName("Redwall");
		song.setArtist("from Redwall Soundtrack");
		song.setCover("/images/albums_bad/redwall.jpg");
		//song.setFilePath("/songs/Redwall.mp3");
		songList.add(song);
		return songList;
	}

}