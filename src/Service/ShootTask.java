/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.DataBase;
import entity.Block.Block;
import entity.People.People;
import entity.Weapon.Oil;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author lenovo
 */

/*new 2 bed 4.28 17:23*/
public class ShootTask extends TimerTask {

    private int x;
    private int y;
    int h = 0;
    int v = 0;
    private DataBase dataBase;
    private People people;
    private Block[][] map;
    ImageView image;
    private Service service;

    /**
     *
     * @param service service
     * @param dataBase dataBase
     */
    public ShootTask(Service service, DataBase dataBase) {
        this.service = service;
        this.dataBase = dataBase;
        this.people = dataBase.getPeople();
        this.map = dataBase.getMap();
    }

    /**
     * 攻击的时候，对应当前武器进行相应攻击
     */
    /*new 1 bed 4.30 18:22*/
    @Override
    public void run() {
        AnchorPane pane = this.dataBase.gameUI.pane;
        int i = 0;
        //0:gun 1:uzi 2:grenade 3:oil
        if (people.getWeapon()[people.getNowWeapon()].getNowAMMO() > 0) {
            people.getWeapon()[people.getNowWeapon()].setNowAMMO(people.getWeapon()[people.getNowWeapon()].getNowAMMO() - 1);
            
            switch (people.getNowWeapon()) {
                case 0:
                case 1:
                    if (null != people.getDirection()) {

                             MediaPlayer gunBgmMediaPlayer = new MediaPlayer(dataBase.gunMedia);
                             gunBgmMediaPlayer.play();
                        switch (people.getDirection()) {
                            case north:
                                for (i = 1; i <= people.getWeapon()[people.getNowWeapon()].getDistance(); i++) {
                                    if (people.getY() - i <= 0) {
                                        break;
                                    }
                                    if (map[people.getX()][people.getY() - i].getEnumStatus() != Block.EnumStatus.empty) {
                                        break;
                                    }
                                }
                                if (map[people.getX()][people.getY() - i].getEnumStatus() == Block.EnumStatus.zombie) {
                                    service.zombieGetHurt(people.getX(), people.getY() - i);
                                }
                                if (map[people.getX()][people.getY() - i].getEnumStatus() == Block.EnumStatus.oil) {
                                    service.boom(people.getX(), people.getY() - i);
                                }
                                if ((i - 1) != 0) {
                                    image = new ImageView(this.dataBase.gameUI.upShoot);
                                    image.setFitHeight(32 * (i - 1));
                                    this.x = people.getX();
                                    this.y = people.getY() - (i - 1);
                                }
                                break;
                            case south:
                                for (i = 1; i <= people.getWeapon()[people.getNowWeapon()].getDistance(); i++) {
                                    if (people.getY() + i >= 23) {
                                        break;
                                    }
                                    if (map[people.getX()][people.getY() + i].getEnumStatus() != Block.EnumStatus.empty) {
                                        break;
                                    }
                                }
                                if (map[people.getX()][people.getY() + i].getEnumStatus() == Block.EnumStatus.zombie) {
                                    service.zombieGetHurt(people.getX(), people.getY() + i);
                                }
                                if (map[people.getX()][people.getY() + i].getEnumStatus() == Block.EnumStatus.oil) {
                                    service.boom(people.getX(), people.getY() + i);
                                }
                                if ((i - 1) != 0) {
                                    image = new ImageView(this.dataBase.gameUI.downShoot);
                                    image.setFitHeight(32 * (i - 1));
                                    this.x = people.getX();
                                    this.y = people.getY() + 1;
                                }
                                break;
                            case west:
                                for (i = 1; i <= people.getWeapon()[people.getNowWeapon()].getDistance(); i++) {
                                    if (people.getX() - i <= 0) {
                                        break;
                                    }
                                    if (map[people.getX() - i][people.getY()].getEnumStatus() != Block.EnumStatus.empty) {
                                        break;
                                    }
                                }
                                if (map[people.getX() - i][people.getY()].getEnumStatus() == Block.EnumStatus.zombie) {
                                    service.zombieGetHurt(people.getX() - i, people.getY());
                                }
                                if (map[people.getX() - i][people.getY()].getEnumStatus() == Block.EnumStatus.oil) {
                                    service.boom(people.getX() - i, people.getY());
                                }
                                if ((i - 1) != 0) {
                                    image = new ImageView(this.dataBase.gameUI.leftShoot);
                                    image.setFitWidth(32 * (i - 1));
                                    this.x = people.getX() - (i - 1);
                                    this.y = people.getY();
                                }
                                break;
                            case east:
                                for (i = 1; i <= people.getWeapon()[people.getNowWeapon()].getDistance(); i++) {
                                    if (people.getX() + i >= 39) {
                                        break;
                                    }
                                    if (map[people.getX() + i][people.getY()].getEnumStatus() != Block.EnumStatus.empty) {
                                        break;
                                    }
                                }
                                if (map[people.getX() + i][people.getY()].getEnumStatus() == Block.EnumStatus.zombie) {
                                    service.zombieGetHurt(people.getX() + i, people.getY());
                                }
                                if (map[people.getX() + i][people.getY()].getEnumStatus() == Block.EnumStatus.oil) {
                                    service.boom(people.getX() + i, people.getY());
                                }
                                if ((i - 1) != 0) {
                                    image = new ImageView(this.dataBase.gameUI.rightShoot);
                                    image.setFitWidth(32 * (i - 1));
                                    this.x = people.getX() + 1;
                                    this.y = people.getY();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (image != null) {
                                pane.getChildren().add(image);
                                image.setLayoutX(x * 32);
                                image.setLayoutY(y * 32);
                                image.setVisible(true);
                                
                            }
                        }
                    });
                    Timer t = new Timer(true);
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (image != null) {
                                        image.setVisible(false);
                                        image = null;
                                    }
                                    t.cancel();

                                }
                            });
                        }
                    }, 50);
                    break;
                case 2:
                    h = 0;
                    v = 0;
                    switch (people.getDirection()) {
                        case north:
                            v = -1;
                            break;
                        case south:
                            v = 1;
                            break;
                        case east:
                            h = 1;
                            break;
                        case west:
                            h = -1;
                            break;
                    }
                    x = people.getX();
                    y = people.getY();
                    if (x + people.getWeapon()[people.getNowWeapon()].getDistance() * h <= 0) {         //判断手榴弹是否越界
                        x = 1;
                    } else if (x + people.getWeapon()[people.getNowWeapon()].getDistance() * h >= 39) {
                        x = 38;
                    } else {
                        x = x + people.getWeapon()[people.getNowWeapon()].getDistance() * h;
                    }

                    if (y + people.getWeapon()[people.getNowWeapon()].getDistance() * v >= 23) {
                        y = 22;
                    } else if (y + people.getWeapon()[people.getNowWeapon()].getDistance() * v <= 0) {
                        y = 1;
                    } else {
                        y = y + people.getWeapon()[people.getNowWeapon()].getDistance() * v;
                    }
                    /*change*/
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (map[x][y].getEnumStatus() != Block.EnumStatus.box) {

                                ImageView grenade = new ImageView(dataBase.gameUI.P_grenade);
                                dataBase.gameUI.pane.getChildren().add(grenade);
                                PathTransition temp = new PathTransition();
                                Line tLine = new Line();
                                tLine.setStartX(people.getX() * 32);
                                tLine.setStartY(people.getY() * 32);
                                tLine.setEndX(x * 32);
                                tLine.setEndY(y * 32);
                                temp.setPath(tLine);
                                temp.setDuration(new Duration(300));
                                temp.setNode(grenade);
                                temp.play();

                                TimerTask t1 = new TimerTask() {
                                    @Override
                                    public void run() {
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                dataBase.gameUI.pane.getChildren().remove(grenade);
                                                if (dataBase.getMap()[x][y].getEnumStatus() != Block.EnumStatus.oil) {
                                                    dataBase.gameUI.getImageMap()[x][y].setImage(dataBase.gameUI.P_boom);
                                                    dataBase.gameUI.getImageMap()[x][y].setVisible(true);
                                                    MediaPlayer grenadeMediaPlayer = new MediaPlayer(dataBase.grenadeMedia);
                                                    grenadeMediaPlayer.play();
                                                    TimerTask t = new TimerTask() {
                                                        @Override
                                                        public void run() {
                                                            Platform.runLater(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    dataBase.gameUI.getImageMap()[x][y].setVisible(false);
                                                                    //dataBase.gameUI.pane.getChildren().remove(dataBase.gameUI.getImageMap()[x][y]);
                                                                }
                                                            });
                                                        }
                                                    };
                                                    service.shootTimer.schedule(t, 200);
                                                }
                                            }
                                        });

                                    }
                                };
                                service.shootTimer.schedule(t1, 200);
                                
/*                         dataBase.gameUI.getImageMap()[x][y].setImage(dataBase.gameUI.P_boom);
                           dataBase.gameUI.getImageMap()[x][y].setVisible(true);
                           TimerTask t = new TimerTask() {
                              @Override
                                public void run() {
                                  Platform.runLater(new Runnable() {
                                      @Override
                                      public void run() {
                                          dataBase.gameUI.getImageMap()[x][y].setVisible(false);
                                        //dataBase.gameUI.pane.getChildren().remove(dataBase.gameUI.getImageMap()[x][y]);
                                     }
                                 });
                               }
                         };
                           service.peopleMoveTimer.schedule(t, 200);*/
                            }
                        }
                    });
                    //落地对可伤害物体进行伤害
                    if (service.canAttackNext(x, y)) {
                        service.hurt(x, y);
                    }
                    if (service.canAttackNext(x - 1, y - 1)) {
                        service.hurt(x - 1, y - 1);
                    }
                    if (service.canAttackNext(x - 1, y)) {
                        service.hurt(x - 1, y);
                    }
                    if (service.canAttackNext(x - 1, y + 1)) {
                        service.hurt(x - 1, y + 1);
                    }
                    if (service.canAttackNext(x, y - 1)) {
                        service.hurt(x, y - 1);
                    }
                    if (service.canAttackNext(x, y + 1)) {
                        service.hurt(x, y + 1);
                    }
                    if (service.canAttackNext(x + 1, y - 1)) {
                        service.hurt(x + 1, y - 1);
                    }
                    if (service.canAttackNext(x + 1, y)) {
                        service.hurt(x + 1, y);
                    }
                    if (service.canAttackNext(x + 1, y - 1)) {
                        service.hurt(x + 1, y - 1);
                    }

                    break;
                case 3:
                    h = 0;
                    v = 0;
                    switch (people.getDirection()) {
                        case north:
                            v = -1;
                            break;
                        case south:
                            v = 1;
                            break;
                        case east:
                            h = 1;
                            break;
                        case west:
                            h = -1;
                            break;
                    }
                    x = people.getX() + h;
                    y = people.getY() + v;
                    if (map[x][y].getEnumStatus() == Block.EnumStatus.empty) {
                        map[x][y].newBlock(new Oil(x, y, 100, 1, dataBase), Block.EnumStatus.oil);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        dataBase.gameUI.getImageMap()[((Oil) map[x][y].status).getX()][((Oil) map[x][y].status).getY()].setImage(dataBase.gameUI.P_oilbomb);
                                        dataBase.gameUI.getImageMap()[((Oil) map[x][y].status).getX()][((Oil) map[x][y].status).getY()].setVisible(true);
                                    }
                                });
                            }
                        });
                    }
                    break;
            }
        Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    dataBase.gameUI.AMMOText.setText(
                                dataBase.getPeople().getWeapon()[dataBase.getPeople().getNowWeapon()].getNowAMMO()
                                +" / "+
                                dataBase.getPeople().getWeapon()[dataBase.getPeople().getNowWeapon()].getAMMO()
                                );
                }
            });
        }
    }
}
