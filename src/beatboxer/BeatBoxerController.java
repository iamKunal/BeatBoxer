/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author kunal
 */
public class BeatBoxerController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private Menu modeMenu;
    @FXML
    private Menu playlistMenu;
    @FXML
    private MenuItem newPlayListMenu;
    @FXML
    private Menu helpMenu;
    @FXML
    private Label trackDetails;
    @FXML
    private Label nowPlaying;
    @FXML
    private TabPane listViewTabPane;
    @FXML
    private ListView<BBSong> nowPlayingListView;
    @FXML
    private ListView<BBSong> allsongsListView;
    @FXML
    private ListView<BBItem> playlistListView;
    @FXML
    private BorderPane player;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label timer;
    @FXML
    private Label totalTimer;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> albumListView;
    @FXML
    private ListView<String> artistListView;
    @FXML
    private ListView<BBSong> songListView;
    @FXML
    private ToggleButton playButton;
    @FXML
    private ToggleButton favouriteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button shuffleButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label volumeLabel;
    @FXML
    private ToggleButton autoplayButton;
    public static ChangeListener currentTimePropertyListener;
    public static ChangeListener totalDurationPropertyListener;
    public static ChangeListener statusPropertyListener;
    public static ChangeListener onEndOfMediaPropertyListener;

    @FXML
    public void playMusic() {
        BeatBoxer.play();
    }

    @FXML
    public void chooseFolders() throws Exception {
        Parent folderChooserRoot = FXMLLoader.load(getClass().getResource("FolderChooser.fxml"));
        Scene folderChooser = new Scene(folderChooserRoot);
        Stage stager = new Stage();
        stager.setScene(folderChooser);
        Directory d = new Directory();
        ArrayList<String> oldF = d.Show();
        stager.showAndWait();
//        if(! d.Show().equals(oldF))
        refresh();

    }

    @FXML
    private void search() {
        String searchString = searchField.getText();
//        System.out.println(searchString);
        searchString = searchString.trim();
        if (searchString.equals("")) {
            songListView.setItems(null);
            albumListView.setItems(null);
            artistListView.setItems(null);
            return;
        }
        try {
            Track track = new Track();
            Album album = new Album();
            Artist artist = new Artist();
            songListView.setItems(track.SearchTrack(searchString));
            albumListView.setItems(album.SearchAlbum(searchString));
            artistListView.setItems(artist.SearchArtist(searchString));
        } catch (Exception e) {
            ;
        }
    }

    @FXML
    private void favourite() {
        try {
            Track track = new Track();
            BBSong song = getCurrentSong();
            song.setFavourite(!song.isFavourite());
            if (!song.isFavourite()) {
                track.unfavourite(song.getId());
            } else {
                track.favourite(song.getId());
            }

            BeatBoxer.nowPlaying.set(BeatBoxer.currentIndex, song);
        } catch (Exception e) {
            ;
        }
    }

    @FXML
    private void editItem() {
        try {
            int tabSelected = listViewTabPane.getSelectionModel().getSelectedIndex();
            if (tabSelected == 0 || tabSelected == 1) {
                BBSong selectedSong;
                if (tabSelected == 0) {
                    if (nowPlayingListView.getSelectionModel().getSelectedIndex() == -1) {
                        return;
                    }
                    selectedSong = nowPlayingListView.getSelectionModel().getSelectedItem();
                } else {
                    if (allsongsListView.getSelectionModel().getSelectedIndex() == -1) {
                        return;
                    }
                    selectedSong = allsongsListView.getSelectionModel().getSelectedItem();
                }
//            System.out.println(selectedSong);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SongEditor.fxml"));
                Parent songEditorRoot = (Parent) loader.load();
                Scene songEditor = new Scene(songEditorRoot);
                SongEditorController control = (SongEditorController) loader.getController();
                control.initData(selectedSong);
                Stage stager = new Stage();
                stager.setScene(songEditor);
                stager.setTitle("Modify Song : " + selectedSong.getName());
                stager.showAndWait();
                if (!BeatBoxer.nowPlaying.contains(BeatBoxer.defaultSong)) {
                    nowPlayingListView.setItems(BeatBoxer.nowPlaying);
                }
                try {
                    Track track = new Track();
                    ObservableList<BBSong> songList = track.ShowAllTracks();
                    allsongsListView.setItems(songList);
                } catch (Exception e) {
                    ;
                }
            }
            if (tabSelected == 2) {
                BBItem selectedPlayList;
                if (playlistListView.getSelectionModel().getSelectedIndex() <= 2) {
                    return;
                }
                selectedPlayList = playlistListView.getSelectionModel().getSelectedItem();
                if (selectedPlayList.getId() <= 0) {
                    return;
                }
//            System.out.println(selectedPlayList);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("PlaylistEditor.fxml"));
                Parent playlistEditorRoot = (Parent) loader.load();
                Scene playlistEditor = new Scene(playlistEditorRoot);
                PlaylistEditorController control = (PlaylistEditorController) loader.getController();
                control.initData(selectedPlayList);
                playlistEditor.getStylesheets().add(getClass().getResource("playlistStylesheet.css").toExternalForm());
                Stage stager = new Stage();
                stager.setScene(playlistEditor);
                stager.setTitle("Modify Playlist : " + selectedPlayList.getName());
                stager.showAndWait();
                playlistListView.setItems(null);
                playlistListView.setItems(new PlayList().ShowAllPlayLists());
            }
        } catch (Exception e) {
            ;
        }
    }

    @FXML
    private void deleteItem() throws Exception {
        int tabSelected = listViewTabPane.getSelectionModel().getSelectedIndex();
        if (tabSelected == 0 || tabSelected == 1) {
            BBSong selectedSong;
            if (tabSelected == 0) {
                if (nowPlayingListView.getSelectionModel().getSelectedIndex() == -1) {
                    return;
                }
                selectedSong = nowPlayingListView.getSelectionModel().getSelectedItem();
            } else {
                if (allsongsListView.getSelectionModel().getSelectedIndex() == -1) {
                    return;
                }
                selectedSong = allsongsListView.getSelectionModel().getSelectedItem();
            }
            boolean rightNow = false;
            if (BeatBoxer.nowPlaying.get(BeatBoxer.currentIndex).equals(selectedSong)) {
                rightNow = true;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemDeleter.fxml"));
            Parent itemDeleterRoot = (Parent) loader.load();
            Scene songEditor = new Scene(itemDeleterRoot);
            ItemDeleterController control = (ItemDeleterController) loader.getController();
            control.initSong(selectedSong);
            Stage stager = new Stage();
            stager.setScene(songEditor);
            stager.setTitle("Delete Song : " + selectedSong.getName());
            stager.showAndWait();
            if (!BeatBoxer.nowPlaying.contains(selectedSong) && rightNow) {
                setVolumeValue(BeatBoxer.nowPlaying.get(BeatBoxer.currentIndex).getGenre());
            }
            if (new Track().ShowAllTracks().size() == 0 || BeatBoxer.nowPlaying.size() == 0) {
                refresh();
            }
            nowPlayingListView.setItems(BeatBoxer.nowPlaying);
            allsongsListView.setItems(new Track().ShowAllTracks());
            try {
                Track track = new Track();
                ObservableList<BBSong> songList = track.ShowAllTracks();
                allsongsListView.setItems(songList);
            } catch (Exception e) {
                ;
            }
        }
        if (tabSelected == 2) {
            BBItem selectedPlayList;
            if (playlistListView.getSelectionModel().getSelectedIndex() <= 2) {
                return;
            }
            selectedPlayList = playlistListView.getSelectionModel().getSelectedItem();
            System.out.println(selectedPlayList);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemDeleter.fxml"));
            Parent itemDeleterRoot = (Parent) loader.load();
            Scene songEditor = new Scene(itemDeleterRoot);
            ItemDeleterController control = (ItemDeleterController) loader.getController();
            control.initPlayList(selectedPlayList);
            Stage stager = new Stage();
            stager.setScene(songEditor);
            stager.setTitle("Delete Playlist : " + selectedPlayList.getName());
            stager.showAndWait();
            playlistListView.setItems(null);
            playlistListView.setItems(new PlayList().ShowAllPlayLists());
        }
    }

    @FXML
    private void createPlaylist() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreatePlaylist.fxml"));
            Parent createPlaylistRoot = (Parent) loader.load();
            Scene createPlaylist = new Scene(createPlaylistRoot);
            Stage stager = new Stage();
            stager.setScene(createPlaylist);
            stager.setTitle("Create a new Playlist");
            stager.showAndWait();
            playlistListView.setItems(new PlayList().ShowAllPlayLists());
        } catch (Exception e) {
            ;
        }
    }

    @FXML
    public void playNext() {
        int size = BeatBoxer.nowPlaying.size();
        BeatBoxer.mediaPlayer.stop();
        BBSong a;
        a = BeatBoxer.nowPlaying.get((BeatBoxer.currentIndex + 1) % size);
        setVolumeValue(a.getGenre());
        BeatBoxer.play(a);
    }

    @FXML
    public void playPrevious() {
        int size = BeatBoxer.nowPlaying.size();
        BeatBoxer.mediaPlayer.stop();
        BBSong a = BeatBoxer.nowPlaying.get((BeatBoxer.currentIndex + size - 1) % size);
        setVolumeValue(a.getGenre());
        BeatBoxer.play(a);
    }

    @FXML
    private void shuffle() {
        ObservableList<BBSong> list = BeatBoxer.nowPlaying;
        BBSong song = getCurrentSong();
        FXCollections.shuffle(list);
        list.remove(song);
        list.add(0, song);
        BeatBoxer.currentIndex = 0;
        nowPlayingListView.setItems(list);
    }

    @FXML
    private void timer() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Timer.fxml"));
        Parent timerRoot = (Parent) loader.load();
        Scene timer = new Scene(timerRoot);
        Stage stager = new Stage();
        stager.setScene(timer);
        stager.setTitle("Timer");
        stager.showAndWait();
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    private void showHelp() {
        ButtonType github = new ButtonType("Visit on GitHub");
        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().setAll(github, ok);
        alert.setTitle("About BeatBoxer");
        alert.setHeaderText(null);
        alert.setContentText("BeatBoxer is made by:\nKunal Gupta, Shivam Tayal & Punit Lakshwani.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == github) {
            String url = "https://goo.gl/fl49zi";
            try {
                new ProcessBuilder("x-www-browser", url).start();   //Linux
            } catch (IOException e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));      //Windows
                } catch (Exception e1) {
                    ;
                }
            }
        }
    }

    public void playAll() {      //play All Songs
        Track track = new Track();
        ObservableList<BBSong> allSongs = track.ShowAllTracks();
        BeatBoxer.nowPlaying.setAll(allSongs);
    }

    public void playAlbum(String album) {
        try {
            Album al = new Album();
            ObservableList<BBSong> songList = al.ShowAllTracksinAlbum(album);
            BeatBoxer.nowPlaying.setAll(songList);
            nowPlayingListView.setDisable(false);
            disablePlayGroup(false);
            editButton.setDisable(false);
            favouriteButton.setDisable(false);
            nowPlayingListView.setItems(BeatBoxer.nowPlaying);
            listViewTabPane.getSelectionModel().select(0);
            setVolumeValue(BeatBoxer.nowPlaying.get(0).getGenre());
            BeatBoxer.play(BeatBoxer.nowPlaying.get(0));
            BeatBoxer.mediaPlayer.stop();
        } catch (Exception e) {
            ;
        }
    }

    public void playArtist(String artist) {
        try {
            Artist at = new Artist();
            ObservableList<BBSong> songList = at.ShowAllTracksByArtists(artist);
            BeatBoxer.nowPlaying.setAll(songList);
            nowPlayingListView.setDisable(false);
            disablePlayGroup(false);
            editButton.setDisable(false);
            favouriteButton.setDisable(false);
            nowPlayingListView.setItems(BeatBoxer.nowPlaying);
            listViewTabPane.getSelectionModel().select(0);
            setVolumeValue(BeatBoxer.nowPlaying.get(0).getGenre());
            BeatBoxer.play(BeatBoxer.nowPlaying.get(0));
            BeatBoxer.mediaPlayer.stop();
        } catch (Exception e) {
            ;
        }
    }

    public void disablePlayGroup(boolean value) {
        playButton.setDisable(value);
        previousButton.setDisable(value);
        nextButton.setDisable(value);
        shuffleButton.setDisable(value);
        autoplayButton.setDisable(value);
    }

    private String getTrackDetails() {
        if (BeatBoxer.nowPlaying.size() <= BeatBoxer.currentIndex) {
            return "No track Playing.";
        }
        return BeatBoxer.nowPlaying.get((BeatBoxer.currentIndex)).stringified();
    }

    private BBSong getCurrentSong() {
        return BeatBoxer.nowPlaying.get(BeatBoxer.currentIndex);
    }

    private int getListViewIndex(ListView<BBSong> l, BBSong song) {
        return BBGenerator.find(l.getItems(), song);
    }

    private void refresh() {
        try {
            volumeSlider.setValue(100);
            nowPlayingListView.setDisable(true);
            nowPlayingListView.setItems(null);
            trackDetails.setText("\n\nNo track Playing.");
            disablePlayGroup(true);
//            editButton.setDisable(true);
            favouriteButton.setDisable(true);
            allsongsListView.setDisable(false);
            playlistListView.setDisable(false);
            ObservableList<BBItem> playLists = FXCollections.observableArrayList();
//            playLists.add(new BBItem(0, "All Songs"));
//            playLists.add(new BBItem(-1, "Favourites"));
            playLists.addAll(new PlayList().ShowAllPlayLists());
            Track track = new Track();
            ObservableList<BBSong> songList = track.ShowAllTracks();
            if (songList.isEmpty()) {
                allsongsListView.setDisable(true);
                playlistListView.setDisable(true);
            }
            allsongsListView.setItems(songList);
            playlistListView.setItems(playLists);
            BeatBoxer.mediaPlayer.stop();
            timer.setText("-- : --");
            totalTimer.setText("-- : --");
        } catch (Exception e) {
            ;
        }
    }

    public void playPlaylist(BBItem playlist) {
        switch (playlist.getId()) {
            case 0:
                playAll();
                break;
            case -1:
                try {
                    Track track = new Track();
                    nowPlayingListView.setItems(track.ShowAllFavourites());
                    BeatBoxer.nowPlaying = track.ShowAllFavourites();
                } catch (Exception e) {
                    ;
                }
                break;
            case -2:
                try {
                    Track track = new Track();
                    nowPlayingListView.setItems(track.ShowRecentlyAdded());
                    BeatBoxer.nowPlaying = track.ShowRecentlyAdded();
                } catch (Exception e) {
                    ;
                }
                break;
            default:
                try {
                    PlayList pl = new PlayList();
                    ObservableList<BBSong> l = pl.ShowAllTracksinPlayList(playlist.getId());
                    nowPlayingListView.setItems(l);
                    BeatBoxer.nowPlaying = l;
                } catch (Exception e) {
                    ;
                }
                break;
        }
        nowPlayingListView.setDisable(false);
        disablePlayGroup(false);
        editButton.setDisable(false);
        favouriteButton.setDisable(false);
        nowPlayingListView.setItems(BeatBoxer.nowPlaying);
        listViewTabPane.getSelectionModel().select(0);
        if (BeatBoxer.nowPlaying.size() == 0) {
            disablePlayGroup(true);
            favouriteButton.setDisable(true);
        } else {
            setVolumeValue(BeatBoxer.nowPlaying.get(0).getGenre());
            BeatBoxer.play(BeatBoxer.nowPlaying.get(0));
        }
        BeatBoxer.mediaPlayer.stop();
    }

    private double setVolumeValue(String genre) {
        double volume = 100;
        genre = genre.toLowerCase();
        if (genre.contains("dance") || genre.contains("party") || genre.contains("rock") || genre.contains("metal") || genre.contains("deep house") || genre.contains("progressive house")) {
            volume = 100;
        } else if (genre.contains("punk") || genre.contains("funk")) {
            volume = 65;
        } else if (genre.contains("pop") || genre.contains("electro") || genre.contains("techno") || genre.contains("edm") || genre.contains("hip") || genre.contains("hop") || genre.contains("trap")) {
            volume = 70;
        } else if (genre.contains("romantic") || genre.contains("soothing") || genre.contains("soul") || genre.contains("sad") || genre.contains("piano") || genre.contains("tropical house") || genre.contains("Ambient")) {
            volume = 30;
        } else if (genre.contains("bollywood") || genre.contains("bhajan")) {
            volume = 85;
        } else {
            volume = 80;
        }
        volumeSlider.setValue(volume);
        return BeatBoxer.volume;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newPlayListMenu.setAccelerator(new KeyCodeCombination(KeyCode.N));

        autoplayButton.selectedProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                BeatBoxer.autoPlay = observable.getValue().toString().equalsIgnoreCase("true");
            }

        });

        for (MenuItem m : modeMenu.getItems()) {
            m.setOnAction(e -> {
                Track track = new Track();
                ObservableList<BBSong> list = track.ShowByMode(m.getText());
                BeatBoxer.nowPlaying.clear();
                BeatBoxer.nowPlaying.setAll(list);
                nowPlayingListView.setDisable(false);
                disablePlayGroup(false);
                editButton.setDisable(false);
                favouriteButton.setDisable(false);
                listViewTabPane.getSelectionModel().select(0);
                nowPlayingListView.setItems(BeatBoxer.nowPlaying);
                if (BeatBoxer.nowPlaying.isEmpty()) {
                    disablePlayGroup(true);
                    favouriteButton.setDisable(true);
                } else {
                    BeatBoxer.play(BeatBoxer.nowPlaying.get(0));
                }
                BeatBoxer.mediaPlayer.stop();
            });
        }
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                volumeLabel.setText(Integer.toString(newValue.intValue()));
                if (newValue.intValue() >= 1) {
                    BeatBoxer.volume = 1 - (0.5 * Math.log10(100 - newValue.intValue()));
                    if (Double.isInfinite(BeatBoxer.volume)) {
                        BeatBoxer.volume = 1.00;
                    }
                    BeatBoxer.mediaPlayer.setVolume(BeatBoxer.volume);
                } else {
                    BeatBoxer.volume = 0;
                    BeatBoxer.mediaPlayer.setVolume(0);
                }
            }

        });
        /*-------------Slider and Time Listeners----------------------------*/

        currentTimePropertyListener = new ChangeListener<Duration>() {
            @Override
            public void changed(
                    ObservableValue<? extends Duration> observableValue,
                    Duration duration,
                    Duration current) {
                if (!timeSlider.isValueChanging()) {
                    timeSlider.setValue(current.toSeconds());
                    timer.setText(String.format("%02.0f:%02.0f", Math.floor(current.toSeconds() / 60), Math.floor(current.toSeconds() % 60 + 0.5)));
                    try {
                        double total = BeatBoxer.mediaPlayer.getTotalDuration().toSeconds();
                        totalTimer.setText(String.format("%02.0f:%02.0f", Math.floor(total / 60), Math.floor(total % 60)));
                        trackDetails.setText(getTrackDetails());
//                            nowPlayingListView.getSelectionModel().clearAndSelect(BeatBoxer.currentIndex);
                        if (Math.abs(current.toSeconds() - total) < 0.5) {
                            playButton.setSelected(false);
                            BeatBoxer.state.setValue("EOF");
                            //                            bb.mediaPlayer.dispose();
//                                if(BeatBoxer.autoPlay){
                            BeatBoxer.state.setValue("autoPlayNext");
                            if (BeatBoxer.currentIndex == BeatBoxer.nowPlaying.size() - 1) {
                                BBSong song = (BeatBoxer.nowPlaying.get(0));
                                setVolumeValue(song.getGenre());
                            } else {
                                BBSong song = (BeatBoxer.nowPlaying.get(BeatBoxer.currentIndex + 1));
                                setVolumeValue(song.getGenre());
                            }
                            //                                BeatBoxer.play();
//                                }
//                                else
//                                    BeatBoxer.state.setValue("playNext");
                        }
                    } catch (NullPointerException e) {
                        ;
                    }
                }
            }
        };
        totalDurationPropertyListener = new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> obs, Duration oldD, Duration newD) {
                timeSlider.setMax(newD.toSeconds());
            }
        };
        /*--------------For Play-Pause button Sync--------------------------------*/
        statusPropertyListener = new ChangeListener<MediaPlayer.Status>() {
            @Override
            public void changed(ObservableValue<? extends MediaPlayer.Status> observable, MediaPlayer.Status oldValue, MediaPlayer.Status newValue) {
                if (newValue.equals(MediaPlayer.Status.PLAYING)) {
                    playButton.setSelected(true);
                    nowPlaying.setText("Now Playing...");
                    disablePlayGroup(false);
                    editButton.setDisable(false);
                    favouriteButton.setDisable(false);
                    favouriteButton.setSelected(getCurrentSong().isFavourite());
                } else {
                    playButton.setSelected(false);
                    if (newValue.equals(MediaPlayer.Status.PAUSED)) {
                        nowPlaying.setText("Now Paused...");
                        disablePlayGroup(false);
                        editButton.setDisable(false);
                        favouriteButton.setDisable(false);
                        favouriteButton.setSelected(getCurrentSong().isFavourite());
                    } else {
                        nowPlaying.setText("Not Playing...");
                        trackDetails.setText("\n\nNo track Playing.");
                        disablePlayGroup(true);
//                        editButton.setDisable(true);
                        favouriteButton.setDisable(true);
                    }
                }
            }
        };
        BeatBoxer.initMediaPlayer();       //Adds all the required Listeners
        timeSlider.valueChangingProperty().addListener((obs, wasCh, isCh) -> {
            if (!isCh) {
                BeatBoxer.mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
            }
        });
        timeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!timeSlider.isValueChanging()) {
                double current = BeatBoxer.mediaPlayer.getCurrentTime().toSeconds();
                if (Math.abs(current - newVal.doubleValue()) > 0.5) {
                    BeatBoxer.mediaPlayer.seek(Duration.seconds(newVal.doubleValue()));
                }
            }
        });

        nowPlaying.setText("Not Playing");
        trackDetails.setWrapText(true);
        refresh();
//        playlistView.setMinWidth(Double.MAX_VALUE);
        /*-------------------playListView Single Click Handler-------------------*/
//        playlistListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BBItem>() {
//
//            @Override
//            public void changed(ObservableValue<? extends BBItem> observable, BBItem oldValue, BBItem newValue) {
//                // Your action here
//                System.out.println("Selected item: " + newValue.getId());
//            }
//        });
        /*-------------------nowplayingListView Double Click Handler---------------*/
        nowPlayingListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    if (nowPlayingListView.getSelectionModel().getSelectedItem() == null)
                       ;//pass
                    else {
                        BBSong a = nowPlayingListView.getSelectionModel().getSelectedItem();
                        nowPlayingListView.getSelectionModel().select(-1);
                        setVolumeValue(a.getGenre());
                        BeatBoxer.play(a);
                    }
                }
            }
        });
        /*-------------------allsongsListView Double Click Handler---------------*/
        allsongsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    if (allsongsListView.getSelectionModel().getSelectedItem() == null)
                       ;//pass
                    else {
                        BBSong a = allsongsListView.getSelectionModel().getSelectedItem();
                        allsongsListView.getSelectionModel().select(-1);
//                         System.out.println(a.getId());
                        playPlaylist(new BBItem(0, "All Songs"));
                        setVolumeValue(a.getGenre());
                        BeatBoxer.play(a);
                    }
                }
            }
        });

        /*-------------------playListView Double Click Handler-------------------*/
        playlistListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    if (playlistListView.getSelectionModel().getSelectedItem() == null)
                       ;//pass
                    else {
                        BBItem a = playlistListView.getSelectionModel().getSelectedItem();
                        playlistListView.getSelectionModel().select(-1);
                        BeatBoxer.mediaPlayer.stop();
                        playPlaylist(a);
                    }
                }
            }
        });
        /*-------------------albumListView Double Click Handler---------------*/
        albumListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    if (albumListView.getSelectionModel().getSelectedItem() == null)
                       ;//pass
                    else {
                        String a = albumListView.getSelectionModel().getSelectedItem();
                        albumListView.getSelectionModel().select(-1);
                        playAlbum(a);
                    }
                }
            }
        });
        /*-------------------artistListView Double Click Handler---------------*/
        artistListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    if (artistListView.getSelectionModel().getSelectedItem() == null)
                       ;//pass
                    else {
                        String a = artistListView.getSelectionModel().getSelectedItem();
                        artistListView.getSelectionModel().select(-1);
                        playArtist(a);
                    }
                }
            }
        });
        /*-------------------songListView Double Click Handler---------------*/
        songListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    if (songListView.getSelectionModel().getSelectedItem() == null)
                       ;//pass
                    else {
                        BBSong a = songListView.getSelectionModel().getSelectedItem();
                        songListView.getSelectionModel().select(-1);
//                         System.out.println(a.getId());
                        playPlaylist(new BBItem(0, "All Songs"));
                        setVolumeValue(a.getGenre());
                        BeatBoxer.play(a);
                    }
                }
            }
        });
    }

}
