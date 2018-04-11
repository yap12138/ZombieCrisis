/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
import DataBase.DataBase;
import Service.Service;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


/**
 *
 * @author lenovo
 */
public class Main extends Application {
    
    private DataBase dataBase;
    private Service service;
    
    private MenuUI menuUI;
    private GameUI gameUI;
    private SettingUI setUI;
    private HelpUI helpUI;
    private RankingListUI rankingListUI;
    
    /**
     *
     * @throws Exception 异常
     */
    public Main() throws Exception{
        dataBase = new DataBase();
        service = new Service(dataBase);
        
        setUI = new SettingUI(dataBase);
        helpUI = new HelpUI();
        rankingListUI=new RankingListUI(dataBase); 
        menuUI = new MenuUI(rankingListUI,dataBase);
        gameUI = new GameUI(dataBase);
        dataBase.gameUI = gameUI;
    }
    
    private void gameUIHandler () {
        //gameUI.pane.requestFocus();
        gameUI.pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode()==dataBase.getNextCode()||e.getCode()==dataBase.getLastCode())
                {
                dataBase.gameUI.NowWeaponImage.setImage(dataBase.gameUI.weaponImage[dataBase.getPeople().getNowWeapon()]);
                }
                dataBase.setKeyCode(e.getCode());
                service.PeopleMove();
            }
        });
        gameUI.pane.setOnKeyReleased(e -> {
            this.dataBase.setKeyCode(e.getCode());
            service.releasedKey();
        });
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
         menuUI.InitButton(primaryStage,
                setUI.getSetscene(),
                helpUI.getHelpscene(),
                gameUI.getScene(),
                rankingListUI.getScene(),
                service
        );
        setUI.InitButton(primaryStage, menuUI.getMenuscene());
        helpUI.InitButton(primaryStage, menuUI.getMenuscene());
        rankingListUI.InitButton(primaryStage, menuUI.getMenuscene(),dataBase);
        gameUI.InitButton(primaryStage, menuUI.getMenuscene());
        gameUIHandler();
        primaryStage.setScene(menuUI.getMenuscene());
        primaryStage.getIcons().add(dataBase.icoImage);
        primaryStage.setTitle("ZombieCrisis");
        /*primaryStage.setWidth(1280);
        primaryStage.setHeight(800);*/
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    
    /**
     *
     * 
     * @param args 默认
     */
    public static void main(String[] args) {
       Application.launch(args);
    }
}
