/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.File;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class HelpUI {
    FXMLLoader loader3=new FXMLLoader(getClass().getResource("HelpUIML.fxml"));
    Parent help=loader3.load();
    Button backBtn=(Button)help.lookup("#backBtn");
    Scene helpscene=new Scene(help,1280,768);
    BackgroundImage backgroundImage;
    File P_background;
    
    /**
     *
     * @throws Exception 异常
     */
    public HelpUI() throws Exception{
        P_background = new File("Resource/image/background.jpg");
        backgroundImage = new BackgroundImage(
                new Image(P_background.toURI().toURL().toExternalForm(), 1280, 768, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        AnchorPane pane = (AnchorPane) help;
        pane.setBackground(new Background(backgroundImage));
    }

    /**
     *
     * @param stage 舞台
     * @param menuscene 场景
     */
    public void InitButton(Stage stage,Scene menuscene) {
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent even) {
                stage.setScene(menuscene);
            }
        });
    }

    /**
     *
     * @return 获取帮助页面的场景
     */
    public Scene getHelpscene() {
        return helpscene;
    }
    
    
    
}
