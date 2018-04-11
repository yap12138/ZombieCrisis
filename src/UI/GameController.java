/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class GameController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView map;
    private ImageView people;
    @FXML
    private Button MusicBtn;
    @FXML
    private ProgressBar HPBar;

    /**
     * Initializes the controller class.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void Press(KeyEvent event) {
        switch(event.getCode()){
            case UP:
                people.setImage(new Image("Graphics/people.png"));
                people.setY(people.getY() - 32);
                break;
            case DOWN: 
                people.setImage(new Image("Graphics/down.png"));
                people.setY(people.getY() + 32);
                break;
            case LEFT:
                people.setImage(new Image("Graphics/left.png"));
                people.setX(people.getX() - 32);
                break;
            case RIGHT: 
                people.setImage(new Image("Graphics/right.png"));
                people.setX(people.getX() + 32);
                break;
            default:
                break;
        }
    }
    
}
