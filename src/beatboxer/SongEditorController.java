/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

/**
 * FXML Controller class
 *
 * @author kunal
 */
public class SongEditorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField songField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField genreField;
    private BBSong song;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;

    @FXML
    private void okExecute() {
        try {
            Track track = new Track();
            String songString = songField.getText().trim(), albumString = albumField.getText().trim(), artistString = artistField.getText().trim(), genreString = genreField.getText().trim();
            track.UpdateTrack(song.getId(), songString, artistString, albumString, genreString);
            song.setName(songString);
            song.setAlbum(albumString);
            song.setArtist(artistString);
            song.setGenre(genreString);
            int index = BBGenerator.find(BeatBoxer.nowPlaying, song);
            if (index != -1) {
                BeatBoxer.nowPlaying.set(index, song);
            }
            cancel();
            try {
                File file = new File(song.getLocation());
                AudioFile a = AudioFileIO.read(file);
                Tag tags = a.getTag();
                tags.setField(FieldKey.TITLE, songString);
                tags.setField(FieldKey.ALBUM, albumString);
                tags.setField(FieldKey.ARTIST, artistString);
                tags.setField(FieldKey.GENRE, genreString);
                a.setTag(tags);
                a.commit();
            } catch (Exception e) {
                
            }
        } catch (Exception e) {
            System.out.println("hey");
        }
    }

    @FXML
    private void cancel() {
        Stage currentStage = (Stage) cancel.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

    }

    public void initData(BBSong song) {
        this.song = song;
        this.songField.setText(song.getName());
        this.albumField.setText(song.getAlbum());
        this.artistField.setText(song.getArtist());
        this.genreField.setText(song.getGenre());
    }

}
