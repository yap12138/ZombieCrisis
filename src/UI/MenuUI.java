/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DataBase.DataBase;
import Service.Service;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
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
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class MenuUI {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuUIML.fxml"));
    Parent menu = loader.load();
    
    Button rankBtn = (Button) menu.lookup("#rankBtn");
    Button setBtn = (Button) menu.lookup("#setBtn");
    Button helpBtn = (Button) menu.lookup("#helpBtn");
    Button startBtn = (Button) menu.lookup("#startBtn");
    Button quitBtn = (Button) menu.lookup("#quitBtn");
    Button soundBtn = (Button) menu.lookup("#soundBtn");
    
    File P_rankBtn;
    File P_setBtn;
    File P_helpBtn;
    File P_startBtn;
    File P_quitBtn;
    File P_background;

    
    BackgroundImage backgroundImage;
    BackgroundImage rankBtnBackgroundImage;
    BackgroundImage helpBtnBackgroundImage;
    BackgroundImage startBtnBackgroundImage;
    BackgroundImage quitBtnBackgroundImage;
    BackgroundImage setBtnBackgroundImage;
    
    AnchorPane menuPane = (AnchorPane) menu;
    Scene menuscene = new Scene(menu, 1280, 768);
    
    RankingListUI rankingListUI;
    
    DataBase dataBase;

    /**
     *
     * @param rankingListUI 排行榜UI
     * @param dataBase      数据
     * @throws java.io.IOException 异常
     *
     */
    public MenuUI(RankingListUI rankingListUI,DataBase dataBase) throws IOException {
        this.rankingListUI = rankingListUI;
        this.dataBase = dataBase;
        InitImageIcon();
        InitBackground();
        this.dataBase.getMenuBgmMediaPlayer().play();
    }

    /**
     *
     * @return 主菜单
     */
    public Scene getMenuscene() {
        return menuscene;
    }
    
    private void InitBackground() throws MalformedURLException {
        backgroundImage = new BackgroundImage(
                new Image(P_background.toURI().toURL().toExternalForm(), 1280, 768, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        menuPane.setBackground(new Background(backgroundImage));
        rankBtnBackgroundImage = new BackgroundImage(
                new Image(P_rankBtn.toURI().toURL().toExternalForm(),
                        rankBtn.getPrefWidth(), rankBtn.getPrefHeight(),
                        false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        rankBtn.setBackground(new Background(rankBtnBackgroundImage));
        helpBtnBackgroundImage = new BackgroundImage(
                new Image(P_helpBtn.toURI().toURL().toExternalForm(),
                        helpBtn.getPrefWidth(), helpBtn.getPrefHeight(),
                        false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        helpBtn.setBackground(new Background(helpBtnBackgroundImage));
        startBtnBackgroundImage = new BackgroundImage(
                new Image(P_startBtn.toURI().toURL().toExternalForm(),
                        startBtn.getPrefWidth(), startBtn.getPrefHeight(),
                        false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        startBtn.setBackground(new Background(startBtnBackgroundImage));
        quitBtnBackgroundImage = new BackgroundImage(
                new Image(P_quitBtn.toURI().toURL().toExternalForm(),
                        quitBtn.getPrefWidth(), quitBtn.getPrefHeight(),
                        false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        quitBtn.setBackground(new Background(quitBtnBackgroundImage));
        setBtnBackgroundImage = new BackgroundImage(
                new Image(P_setBtn.toURI().toURL().toExternalForm(),
                        setBtn.getPrefWidth(), setBtn.getPrefHeight(),
                        false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBtn.setBackground(new Background(setBtnBackgroundImage));
        
    }
    
    private void InitImageIcon() {
        P_rankBtn = new File("Resource/image/ranklist.png");
        P_setBtn = new File("Resource/image/setting.png");
        P_helpBtn = new File("Resource/image/help.png");
        P_quitBtn = new File("Resource/image/quit.png");
        P_startBtn = new File("Resource/image/start.png");
        P_background = new File("Resource/image/background.jpg");
    }
    
    /**
     *
     * @param stage      舞台
     * @param setScene   设置场景
     * @param helpScene  帮助页面
     * @param gameScene  游戏页面
     * @param rankScene  排行榜
     * @param service    逻辑层
     */
    public void InitButton(Stage stage, Scene setScene, Scene helpScene, Scene gameScene, Scene rankScene ,Service service ) {
        setBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setScene(setScene);
            }
        });
        helpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setScene(helpScene);
            }
        });
        startBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dataBase.getMenuBgmMediaPlayer().pause();
                dataBase.getBattleBgmMediaPlayer().play();
                dataBase.gameUI.ScoreLabel.setText("Score: "+dataBase.getScore());
                //dataBase.gameUI.pane.requestFocus();
                dataBase.gameUI.pane.requestFocus();
                service.startGame();
                stage.setScene(gameScene);
            }
        });
        rankBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rankingListUI.setRecord(rankingListUI.dataBase.getDataTransfer().loadData());
                stage.setScene(rankScene);
            }
        });
        soundBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dataBase.getMenuBgmMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED) {
                    dataBase.getMenuBgmMediaPlayer().play();
                } else {
                    dataBase.getMenuBgmMediaPlayer().pause();
                }
            }
        });
        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
    }
}
