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
import javafx.scene.input.KeyEvent;
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
public class SettingUI {

    private FXMLLoader loader1 = new FXMLLoader(getClass().getResource("SettingUIML.fxml"));
    private Parent set = loader1.load();
    
    
    private Button up = (Button) set.lookup("#KeyUp");
    private Button back = (Button) set.lookup("#BACK");
    private Button left = (Button) set.lookup("#KeyLeft");
    private Button right = (Button) set.lookup("#KeyRight");
    private Button down = (Button) set.lookup("#KeyDown");
    private Button attack = (Button) set.lookup("#Attack");

    private Button lastweapon = (Button) set.lookup("#LastWeapon");
    private Button nextweapon = (Button) set.lookup("#NextWeapon");
    
    private Scene setscene = new Scene(set, 1280, 768);
    
    
    private DataBase.DataBase dataBase;
    
    
    AnchorPane setPane = (AnchorPane) set;
    File P_background = new File("Resource/image/background.jpg");
    BackgroundImage backgroundImage = new BackgroundImage(new Image(
            P_background.toURI().toURL().toExternalForm(), 1280, 768, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

    /**
     *
     * @param dataBase  数据
     * @throws java.lang.Exception 异常
     * 
     */
    public SettingUI(DataBase.DataBase dataBase) throws Exception {
        this.dataBase = dataBase;
        setPane.setBackground(new Background(backgroundImage));
        initSettingButtonText();
    }
    
    private void initSettingButtonText () {
        up.setText(dataBase.getKeyUP().getName());
        down.setText(dataBase.getKeyDown().getName());
        left.setText(dataBase.getKeyLeft().getName());
        right.setText(dataBase.getKeyRight().getName());
        
        attack.setText(dataBase.getAttackCode().getName());
        lastweapon.setText(dataBase.getLastCode().getName());
        nextweapon.setText(dataBase.getNextCode().getName());
    }

    /**
     *
     * @return 场景
     */
    public Scene getSetscene() {
        return setscene;
    }

    /**
     *
     * @param stage 舞台
     * @param menuscene 场景
     */
    public void InitButton(Stage stage, Scene menuscene) {
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent even) {
                stage.setScene(menuscene);
            }
        });
        left.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                left.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (dataBase.isKeySetted(event.getCode())) {
                            left.setText(dataBase.getKeyLeft().getName());
                        } else {
                            dataBase.setKeyLeft(event.getCode());
                            left.setText(event.getCode().getName());
                        }
                    }
                });
            }
        });
        up.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                up.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (dataBase.isKeySetted(event.getCode())) {
                            up.setText(dataBase.getKeyUP().getName());

                        } else {
                            dataBase.setKeyUP(event.getCode());
                            up.setText(event.getCode().getName());
                        }
                    }
                });
            }
        });
        down.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                down.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (dataBase.isKeySetted(event.getCode())) {
                            down.setText(dataBase.getKeyDown().getName());
                        } else {
                            dataBase.setKeyDown(event.getCode());
                            down.setText(event.getCode().getName());
                        }
                    }
                });
            }
        });
        right.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                right.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (dataBase.isKeySetted(event.getCode())) {
                            right.setText(dataBase.getKeyRight().getName());
                        } else {
                            dataBase.setKeyRight(event.getCode());
                            right.setText(event.getCode().getName());
                        }
                    }
                });
            }
        });
        attack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                attack.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (dataBase.isKeySetted(event.getCode())) {
                            attack.setText(dataBase.getAttackCode().getName());
                        } else {
                            dataBase.setAttackCode(event.getCode());
                            attack.setText(event.getCode().getName());
                        }
                    }
                });
            }
        });
        
        lastweapon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lastweapon.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (dataBase.isKeySetted(event.getCode())) {
                            lastweapon.setText(dataBase.getLastCode().getName());
                        } else {
                            dataBase.setLastCode(event.getCode());
                            lastweapon.setText(event.getCode().getName());
                        }
                    }
                });
            }
        });
        nextweapon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nextweapon.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (dataBase.isKeySetted(event.getCode())) {
                            nextweapon.setText(dataBase.getNextCode().getName());
                        } else {
                            dataBase.setNextCode(event.getCode());
                            nextweapon.setText(event.getCode().getName());
                        }
                    }
                });
            }
        });
    }

}
