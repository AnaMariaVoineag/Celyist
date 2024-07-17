package application;

public enum MusicGreetings {
    HELLO("nobody's Perfect"),
    HALLO("hay Que Saber Perder"),
    HOLA("vamos A Cantar"),
    BONJOUR("valiente"),
    BUNA("rain Over Me"),
    CIAO("e Il Momento Di Ballare"),
    AHOI("hice Todo Mal"),
    ALOHA("la Differenza Tra Me E Te"),
	ARIGATO("make it Shine");

    private final String songTitle;

    MusicGreetings(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }
}
