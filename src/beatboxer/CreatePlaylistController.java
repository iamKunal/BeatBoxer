/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kunal
 */
public class CreatePlaylistController implements Initializable {

    @FXML
    private Label messageLabel;
    @FXML
    private TextField nameField;
    @FXML
    private Label errorLabel;
    @FXML
    private HBox actionParent;
    @FXML
    private Button cancel;
    @FXML
    private HBox okParent;
    @FXML
    private Button ok;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void checkName(KeyEvent event) throws Exception {
        String newPlaylist = nameField.getText();
        newPlaylist = newPlaylist.trim();
        if (newPlaylist.equals("")) {
            ok.setDisable(true);
            errorLabel.setText("");
        } else {
            PlayList pl = new PlayList();
            ObservableList<BBItem> list = pl.ShowAllPlayLists();
            for (BBItem playList : list) {
                if (playList.getName().equalsIgnoreCase(newPlaylist)) {
                    ok.setDisable(true);
                    errorLabel.setText("A playlist with that name already exists. Please enter \na different name.");
                    return;
                }
            }
            if (newPlaylist.equalsIgnoreCase("All Songs") || newPlaylist.equalsIgnoreCase("Favourites") || newPlaylist.equalsIgnoreCase("Recently Added")) {
                ok.setDisable(true);
                errorLabel.setText("A playlist with that name already exists. Please enter \na different name.");
                return;
            }
            errorLabel.setText("");
            ok.setDisable(false);
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage currentStage = (Stage) cancel.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void okExecute(ActionEvent event) {
        try {
            PlayList p = new PlayList();
            p.create(nameField.getText());
            cancel(new ActionEvent());
        } catch (Exception e) {
            ;
        }
    }

}
