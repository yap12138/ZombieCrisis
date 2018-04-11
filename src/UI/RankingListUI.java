/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DataBase.DataBase;
import DataBase.PlayerData;
import java.io.File;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class RankingListUI {

    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("RankingList.fxml"));
    Parent ranklist = loader1.load();
    
    Pane tipPane = (Pane)ranklist.lookup("#tipPane");
    AnchorPane rankPane = (AnchorPane)ranklist;
    File P_background = new File("Resource/image/background.jpg");
    BackgroundImage backgroundImage = new BackgroundImage(new Image(
            P_background.toURI().toURL().toExternalForm(), 1280, 768, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    
    Label record1 = (Label) ranklist.lookup("#Record1");
    Label record2 = (Label) ranklist.lookup("#Record2");
    Label record3 = (Label) ranklist.lookup("#Record3");
    Label record4 = (Label) ranklist.lookup("#Record4");
    Label record5 = (Label) ranklist.lookup("#Record5");
    Label record6 = (Label) ranklist.lookup("#Record6");
    Label record7 = (Label) ranklist.lookup("#Record7");
    Label record8 = (Label) ranklist.lookup("#Record8");
    Label record9 = (Label) ranklist.lookup("#Record9");
    Label record10 = (Label) ranklist.lookup("#Record10");
   
    
    Button back = (Button) ranklist.lookup("#back");
    Button cancelBtn = (Button) ranklist.lookup("#cancelBtn");
    Button confirmBtn = (Button) ranklist.lookup("#confirmBtn");
    Button clearBtn = (Button) ranklist.lookup("#clearBtn");
    
    Scene ranklistScene = new Scene(ranklist, 1280, 768);
    DataBase dataBase;

    /**
     *
     * @param dataBase 数据
     * @throws java.lang.Exception 异常
     * 
     */
    public RankingListUI(DataBase dataBase) throws Exception {
        this.dataBase = dataBase;
        rankPane.setBackground(new Background(backgroundImage));
        //tipPane.setStyle("-fx-background-color:#ffffff");
    }

    /**
     *
     * @param stage     舞台
     * @param menuscene 场景
     * @param dataBase  数据
     */
    public void InitButton(Stage stage, Scene menuscene, DataBase dataBase) {
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setScene(menuscene);
            }
        });
        clearBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                tipPane.setVisible(true);
            }
        });
        confirmBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                dataBase.getDataTransfer().clearData();
                setRecord(dataBase.getDataTransfer().loadData());
                tipPane.setVisible(false);
            }
        });
        cancelBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               tipPane.setVisible(false);
            }

        });
        
    }

    /**
     *
     * @param list 分数
     */
    public void setRecord(ArrayList<PlayerData> list) {
        record1.setText(list.get(0).toString());
        record2.setText(list.get(1).toString());
        record3.setText(list.get(2).toString());
        record4.setText(list.get(3).toString());
        record5.setText(list.get(4).toString());
        record6.setText(list.get(5).toString());
        record7.setText(list.get(6).toString());
        record8.setText(list.get(7).toString());
        record9.setText(list.get(8).toString());
        record10.setText(list.get(9).toString());
    }

    /**
     *
     * @return ranklistScene
     */
    public Scene getScene() {
        return ranklistScene;
    }

}
