/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

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
    private Button cancelButton;
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
    private void checkName(KeyEvent event) throws Exception{
        String newPlaylist = nameField.getText();
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void okExecute(ActionEvent event) {
    }
    
}
