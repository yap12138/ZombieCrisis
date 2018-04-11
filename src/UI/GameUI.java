/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DataBase.DataBase;
import DataBase.PlayerData;
import entity.Block.Block;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
/**
 * 
 * @author Yaphets
 * 
 * 优化人物移动方法
 */


/**
 *
 * @author lenovo
 */
public class GameUI {
    
    private FXMLLoader gameLoader;

    private Parent root;

    /**
     *
     */
    public AnchorPane pane;
    private Scene scene;

    /**
     *
     */
    public DataBase dataBase;

    /**
     *
     */
    public Pane userPane;

    /**
     *
     */
    public ImageView NowWeaponImage;

    /**
     *
     */
    public ImageView[][] imageMap = new ImageView[40][24];

    /**
     *
     */
    public ImageView playerImage;

    /**
     *
     */
    public ImageView mapImageView;

    /**
     *
     */
    public Block[][] map;

    private Button musicBtn;
    private ProgressBar progressBar ;
    
    private Label UPText;
    private Label DOWNText;

    /**
     *
     */
    public Label ScoreLabel;

    /**
     *
     */
    public Label AMMOText;
    
    private Button confirmBtn;
    private TextField nameTextField;
    
    /**
     *
     */
    public Image P_oilbomb;

    /**
     *
     */
    public Image P_ammo;

    /**
     *
     */
    public Image P_box;

    /**
     *
     */
    public Image P_boom;

    /**
     *
     */
    public Image P_grenade;

    /**
     *
     */
    public Image downShoot;

    /**
     *
     */
    public Image leftShoot;

    /**
     *
     */
    public Image rightShoot;

    /**
     *
     */
    public Image upShoot;

    /**
     *
     */
    public Image[] weaponImage;

    /**
     *
     */
    public Image mapImage;
    
    private Image[] Player;

    /**
     *
     */
    public Image[] Zombie;

    /**
     *
     */
    public Image boss;
    
    /**
     *
     * @param dataBase 数据
     * @throws IOException 抛出异常
     */
    public GameUI(DataBase dataBase) throws IOException {
        InitImageIcon();
        this.dataBase = dataBase;
        gameLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        root = gameLoader.load();
        scene = new Scene(root,1280,768);
        pane = (AnchorPane) root;
        map = dataBase.getMap();
        musicBtn = (Button) root.lookup("#MusicBtn");
        musicBtn.setOpacity(0.5);
        userPane = (Pane) root.lookup("#userPane");
        userPane.setStyle("-fx-background-color:#ffffff");
        confirmBtn = (Button) root.lookup("#confirmBtn");
        NowWeaponImage = (ImageView) root.lookup("#WeaponImage");
        NowWeaponImage.setImage(weaponImage[0]);
        nameTextField = (TextField) root.lookup("#nameTextField");
        mapImageView = (ImageView) root.lookup("#map");
        mapImageView.setImage(mapImage);
        UPText = (Label)root.lookup("#UPText");
        DOWNText = (Label)root.lookup("#DOWNText");
        ScoreLabel = (Label)root.lookup("#ScoreLabel");
        AMMOText = (Label) root.lookup("#AMMOText");
        //creatMap();
        
        progressBar =  (ProgressBar) root.lookup("#HPBar");
    }
    
    /**
     *
     */
    public void bindHpBar() {
        progressBar.progressProperty().bind(this.dataBase.getPeople().getHpProperty().divide(100));
    }
    
    private void InitImageIcon() throws MalformedURLException {
        P_oilbomb = new Image(new File("Resource/image/oilbomb.png").toURI().toURL().toExternalForm());
        P_ammo = new Image(new File("Resource/image/ammo.png").toURI().toURL().toExternalForm());
        P_box = new Image(new File("Resource/image/box.png").toURI().toURL().toExternalForm());
        P_boom = new Image(new File("Resource/image/boom.png").toURI().toURL().toExternalForm());
        P_grenade = new Image(new File("Resource/image/grenade.png").toURI().toURL().toExternalForm());
        upShoot = new Image(new File("Resource/image/upShoot.png").toURI().toURL().toExternalForm());
        downShoot = new Image(new File("Resource/image/downShoot.png").toURI().toURL().toExternalForm());
        leftShoot = new Image(new File("Resource/image/leftShoot.png").toURI().toURL().toExternalForm());
        rightShoot = new Image(new File("Resource/image/rightShoot.png").toURI().toURL().toExternalForm());
        mapImage = new Image(new File("Resource/image/map.png").toURI().toURL().toExternalForm());
        
        weaponImage = new Image[4];
        for (int i = 0; i < 4; i++) {
            String temp3 = "Resource/image/weapon_" + i + ".png";
            weaponImage[i] = new Image(new File(temp3).toURI().toURL().toExternalForm());
        }
        Player = new Image[4];
        for (int i = 0; i < 4; i++) {
            String temp = "Resource/image/Player_" + i + ".png";
            Player[i] = new Image(new File(temp).toURI().toURL().toExternalForm());
        }
        Zombie = new Image[8];
        for (int i = 0; i < 8; i++) {
            String temp1 = "Resource/image/Zombie_" + i + ".png";
            Zombie[i] = new Image(new File(temp1).toURI().toURL().toExternalForm());
        }
        boss = new Image(new File("Resource/image/boss.png").toURI().toURL().toExternalForm());
    }
    
    /**
     *
     * @param stage 舞台
     * @param menuscene 主菜单的场景
     */
    public void InitButton(Stage stage,Scene menuscene) {
        musicBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dataBase.getBattleBgmMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED) {
                    dataBase.getBattleBgmMediaPlayer().play();
                } else {
                    dataBase.getBattleBgmMediaPlayer().pause();
                }    
            }
        });
        confirmBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                dataBase.getDataTransfer().saveData(new PlayerData(nameTextField.getText(),dataBase.getScore()));
                dataBase.setScore(0);
                userPane.setVisible(false);
                dataBase.getBattleBgmMediaPlayer().pause();
                dataBase.getMenuBgmMediaPlayer().play();
                stage.setScene(menuscene);
            }
        });
    }

    /**
     *
     */
    public void InitLabel(){
    UPText.setText("▲"+"\t"+dataBase.getLastCode());
    DOWNText.setText("▼"+"\t"+dataBase.getNextCode());
    }
    
    /**
     *
     * @return 获取人物图像
     */
    public ImageView getPeopleImage() {
        return playerImage;
    }

    /**
     *
     * @return 获取对应格子的图片
     */
    public ImageView[][] getImageMap() {
        return imageMap;
    }

    /**
     *
     * @return 获取场景
     */
    public Scene getScene() {
        return scene;
    }

    /**
     *
     */
    public void creatMap() {
        
        for (int j = 0; j < 24; j++) {
            for (int i = 0; i < 40; i++) {
                imageMap[i][j] = new ImageView();
                pane.getChildren().add(imageMap[i][j]);
                imageMap[i][j].setLayoutX(i * 32);
                imageMap[i][j].setLayoutY(j * 32);
                imageMap[i][j].setVisible(false);
            }
        }

        playerImage = new ImageView(Player[0]);
        setPlayerDirection();
        //imageMap[dataBase.getPeople().getX()][dataBase.getPeople().getY()] = playerImage;
        pane.getChildren().add(playerImage);
        playerImage.setLayoutX(dataBase.getPeople().getX() * 32);
        playerImage.setLayoutY(dataBase.getPeople().getY() * 32);
        for (int i = 0; i < 3; i++) {
            imageMap[9][6 + i * 5] = new ImageView(P_oilbomb);
            pane.getChildren().add(imageMap[9][6 + i * 5]);
            imageMap[9][6 + i * 5].setLayoutX(9 * 32);
            imageMap[9][6 + i * 5].setLayoutY((6 + i * 5) * 32);

            imageMap[16][6 + i * 5] = new ImageView(P_oilbomb);
            pane.getChildren().add(imageMap[16][6 + i * 5]);
            imageMap[16][6 + i * 5].setLayoutX(16 * 32);
            imageMap[16][6 + i * 5].setLayoutY((6 + i * 5) * 32);

            imageMap[23][6 + i * 5] = new ImageView(P_oilbomb);
            pane.getChildren().add(imageMap[23][6 + i * 5]);
            imageMap[23][6 + i * 5].setLayoutX(23 * 32);
            imageMap[23][6 + i * 5].setLayoutY((6 + i * 5) * 32);

            imageMap[30][6 + i * 5] = new ImageView(P_oilbomb);
            pane.getChildren().add(imageMap[30][6 + i * 5]);
            imageMap[30][6 + i * 5].setLayoutX(30 * 32);
            imageMap[30][6 + i * 5].setLayoutY((6 + i * 5) * 32);
        }
    }

    /**
     *
     */
    public void clearUI(){
        for (int j = 0; j < 24; j++) {
            for (int i = 0; i < 40; i++) {
                if (imageMap[i][j].isVisible())
                    imageMap[i][j].setVisible(false);
                pane.getChildren().remove(imageMap[i][j]);
                imageMap[i][j] = null;
                
            }
        }
        pane.getChildren().remove(playerImage);
        playerImage = null;
    }
    
    /**
     *
     */
    public void setPlayerDirection() {
        switch(dataBase.getPeople().getDirection()){
            case north:
                this.playerImage.setImage(Player[0]);
                break;
            case south:
                this.playerImage.setImage(Player[2]);
                break;
            case west:
                this.playerImage.setImage(Player[3]);
                break;
            case east:
                this.playerImage.setImage(Player[1]);
                break;
            default: break;
        }
    }

    
}
