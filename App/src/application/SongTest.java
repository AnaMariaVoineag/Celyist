package application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SongTest {
    private Song song;

    @Before
    public void setup() {
        song = new Song();
    }

    @Test
    public void testGetCover() {
        // Set cover
        song.setCover("album_cover.jpg");

        // Assert cover value
        Assert.assertEquals("album_cover.jpg", song.getCover());
    }

    @Test
    public void testGetName() {
        // Set name
        song.setName("My Song");

        // Assert name value
        Assert.assertEquals("My Song", song.getName());
    }

    @Test
    public void testGetArtist() {
        // Set artist
        song.setArtist("John Doe");

        // Assert artist value
        Assert.assertEquals("John Doe", song.getArtist());
    }

    @Test
    public void testSetCover() {
        // Set cover
        song.setCover("new_cover.jpg");

        // Assert cover value
        Assert.assertEquals("new_cover.jpg", song.getCover());
    }

    @Test
    public void testSetName() {
        // Set name
        song.setName("New Song");

        // Assert name value
        Assert.assertEquals("New Song", song.getName());
    }
}
