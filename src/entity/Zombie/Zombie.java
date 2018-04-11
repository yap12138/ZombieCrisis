/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Zombie;

import DataBase.DataBase;
import entity.Block.Block;
import entity.Block.Block.EnumStatus;
import entity.People.People;
import entity.Weapon.Oil;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Yaphets
 */
public class Zombie implements Runnable {

    private Object lock = new Object();

    private int healthPoint;        //僵尸血量
    private int moveSpeed;          //移动速度
    private int Attack;             //攻击力
    private boolean alive;          //是否存活
    private AttackModel attackModel;//攻击模式
    private int mapX;               //当前坐标
    private int mapY;
    private int lastX;              //上一坐标
    private int lastY;
    private Direction direction;    //僵尸朝向

    /**
     *
     */
    protected DataBase dataBase;
    private People people;
    private Block[][] map;
    private int score;

    /**
     * 僵尸图片
     */
    public ImageView ZombieView;

    /**
     * 僵尸ai
     */
    @Override
    public void run() {
        while (true) {
            if (!alive || !dataBase.gaming) {
                synchronized (dataBase.dataLock) {
                    if (!alive) {
                        dataBase.setScore(dataBase.getScore() + 10);

                    }
                    map[mapX][mapY].newBlock();
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ZombieView.setVisible(false);
                        dataBase.gameUI.pane.getChildren().remove(ZombieView);
                        dataBase.gameUI.ScoreLabel.setText("Score: " + dataBase.getScore());
                        ZombieView = null;
                    }
                });
                break;
            }

            synchronized (DataBase.dataLock) {
                this.move();     //走你

            }
            if ((lastX - mapX) != 0 || (lastY - mapY) != 0) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setZombieViewDirection();
                        /*ZombieView.setLayoutX(getMapX()*32);
                        ZombieView.setLayoutY(getMapY()*32);*/
                        PathTransition temp = new PathTransition();
                        Line tLine = new Line();
                        //dataBase.gameUI.pane.getChildren().add(tLine);

                        tLine.setStartX(lastX * 32);
                        tLine.setStartY(lastY * 32);
                        tLine.setEndX(mapX * 32);
                        tLine.setEndY(mapY * 32);

                        /*tLine.setStartX(-16+32);
                        tLine.setStartY(-16);
                        tLine.setEndX((mapX - lastX)*32 +16);
                        tLine.setEndY((mapY - lastY)*32 -16);
                        
                        ZombieView.setLayoutX(ZombieView.getLayoutX() + (mapX - lastX)*32);
                        ZombieView.setLayoutY(ZombieView.getLayoutY()+ (mapY - lastY)*32);*/
                        temp.setPath(tLine);
                        temp.setDuration(new Duration(10000 / moveSpeed));
                        temp.setNode(ZombieView);
                        temp.play();
                    }
                });
            }

            try {
                Thread.currentThread().sleep(10000 / moveSpeed + 5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!alive || !dataBase.gaming) {
                synchronized (dataBase.dataLock) {
                    if (!alive) {
                        dataBase.setScore(dataBase.getScore() + 10);
                    }
                    map[mapX][mapY].newBlock();
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ZombieView.setVisible(false);
                        dataBase.gameUI.pane.getChildren().remove(ZombieView);
                        dataBase.gameUI.ScoreLabel.setText("Score: " + dataBase.getScore());
                        ZombieView = null;
                    }
                });
                break;
            }
        }
    }

    //方向枚举量 
    /**
     *
     */
    public enum Direction {

        /**
         *
         */
        UP,
        /**
         *
         */
        DOWN,
        /**
         *
         */
        LEFT,
        /**
         *
         */
        RIGHT,
        /**
         *
         */
        LeftUp,
        /**
         *
         */
        RightUp,
        /**
         *
         */
        LeftDown,
        /**
         *
         */
        RightDown
    }

    /**
     *
     * @param healthPoint hp
     * @param moveSpeed moveSpeed
     * @param Attack Attack
     * @param attackModel attackModel
     * @param direction direction
     * @param dataBase dataBase
     */
    public Zombie(int healthPoint, int moveSpeed, int Attack, AttackModel attackModel, Direction direction, DataBase dataBase) {
        this.healthPoint = healthPoint;
        this.moveSpeed = moveSpeed;
        this.Attack = Attack;
        this.attackModel = attackModel;
        this.alive = true;
        this.direction = direction;
        this.dataBase = dataBase;
        this.people = dataBase.getPeople();
        this.map = dataBase.getMap();
        this.score = dataBase.getScore();
        this.ZombieView = new ImageView(this.direction == Direction.UP ? dataBase.gameUI.Zombie[0] : dataBase.gameUI.Zombie[4]);

    }

    /**
     *
     * @return hp
     */
    public int getHealthPoint() {
        return healthPoint;
    }

    /**
     *
     * @return moveSpeed
     */
    public int getMoveSpeed() {
        return this.moveSpeed;
    }

    /**
     *
     * @return attack
     */
    public int getAttack() {
        return this.Attack;
    }

    /**
     *
     * @param mapX X
     */
    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    /**
     *
     * @param mapY Y
     */
    public void setMapY(int mapY) {
        this.mapY = mapY;
    }

    /**
     *
     * @return X
     */
    public int getMapX() {
        return mapX;
    }

    /**
     *
     * @return Y
     */
    public int getMapY() {
        return mapY;
    }

    /**
     *
     * @param direction direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     *
     * @return direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     *
     * @param weaponAttack weaponAttack
     * @return weaponAttack
     */
    public boolean defend(int weaponAttack) {
        this.healthPoint -= weaponAttack;
        this.alive = this.healthPoint > 0;
        return alive;
    }

    /**
     *
     * @return AttackModel
     */
    public AttackModel getAttackModel() {
        return this.attackModel;
    }

    /**
     * 走
     */
    private void move() {
        boolean row = false;         //判断上下左右四个方向是否可行
        boolean col = false;
        int h = 0;
        int v = 0;
        if (this.mapX < people.getX()) {
            h = 1;
        }

        if (this.mapX > people.getX()) {
            h = -1;
        }

        if (this.mapY > people.getY()) {
            v = -1;
        }
        if (this.mapY < people.getY()) {
            v = 1;
        }

        if (mapX < 39 || mapX > 0) {
            row = map[mapX + h][mapY].canWalk();
        }

        if (mapY < 23 || mapY > 0) {
            col = map[mapX][mapY + v].canWalk();
        }

        if (!row && !col) {
            if (canAttackNext(mapX + h, mapY)) {
                hurt(mapX + h, mapY);
                switch (h) {
                    case -1:
                        setDirection(Direction.LEFT);
                        break;
                    case 1:
                        setDirection(Direction.RIGHT);
                        break;
                }
                lastX = mapX;
                lastY = mapY;
                return;
            } else if (canAttackNext(mapX, mapY + v)) {
                hurt(mapX, mapY + v);
                switch (v) {
                    case -1:
                        setDirection(Direction.UP);
                        break;
                    case 1:
                        setDirection(Direction.DOWN);
                        break;
                }
                lastX = mapX;
                lastY = mapY;
                return;
            }
        }

        lastX = mapX;
        boolean flag = false;
        if (v == 0 && !row) {
            if (map[mapX][mapY + 1].canWalk()) {
                v = 1;
                col = map[mapX][mapY + v].canWalk();
            } else if (map[mapX][mapY - 1].canWalk()) {
                v = -1;
                col = map[mapX][mapY + v].canWalk();
            }
            if (col) {
                flag = true;
                map[mapX][mapY + v].newBlock(((Zombie) map[mapX][mapY].status), Block.EnumStatus.zombie);
                map[mapX][mapY].ClearWithoutBox();
                lastY = mapY;
                mapY += v;
                switch (v) {
                    case -1:
                        setDirection(Direction.UP);
                        break;
                    case 1:
                        setDirection(Direction.DOWN);
                        break;
                }
            }
        }
        if (mapX < 39 || mapX > 0) {
            row = map[mapX + h][mapY].canWalk();
        }
        if (row) {
            map[mapX + h][mapY].newBlock(((Zombie) map[mapX][mapY].status), Block.EnumStatus.zombie);
            map[mapX][mapY].ClearWithoutBox();

            mapX += h;
            switch (h) {
                case -1:
                    setDirection(Direction.LEFT);
                    break;
                case 1:
                    setDirection(Direction.RIGHT);
                    break;
            }
        }
        if (flag) {
            switch (v) {
                case -1:
                    switch (h) {
                        case -1:
                            setDirection(Direction.LeftUp);
                            break;
                        case 1:
                            setDirection(Direction.RightUp);
                            break;
                        case 0:
                            setDirection(Direction.UP);
                            break;
                    }
                    break;
                case 1:
                    switch (h) {
                        case -1:
                            setDirection(Direction.LeftDown);
                            break;
                        case 1:
                            setDirection(Direction.RightDown);
                            break;
                        case 0:
                            setDirection(Direction.DOWN);
                            break;
                    }
                    break;
            }
            return;
        }

        if (h == 0 && !col) {
            if (map[mapX + 1][mapY].canWalk()) {
                h++;
                row = map[mapX + 1][mapY].canWalk();
            } else if (map[mapX - 1][mapY].canWalk()) {
                h--;
                row = map[mapX - 1][mapY].canWalk();
            }
            if (row) {
                map[mapX + h][mapY].newBlock(((Zombie) map[mapX][mapY].status), Block.EnumStatus.zombie);
                map[mapX][mapY].ClearWithoutBox();
                lastX = mapX;
                mapX += h;
                switch (h) {
                    case -1:
                        setDirection(Direction.LEFT);
                        break;
                    case 1:
                        setDirection(Direction.RIGHT);
                        break;
                }
            }
        }
        col = map[mapX][mapY + v].canWalk();
        lastY = mapY;
        if (col) {
            map[mapX][mapY + v].newBlock(((Zombie) map[mapX][mapY].status), Block.EnumStatus.zombie);
            map[mapX][mapY].ClearWithoutBox();

            mapY += v;
            switch (v) {
                case -1:
                    switch (h) {
                        case -1:
                            setDirection(Direction.LeftUp);
                            break;
                        case 1:
                            setDirection(Direction.RightUp);
                            break;
                        case 0:
                            setDirection(Direction.UP);
                            break;
                    }
                    break;
                case 1:
                    switch (h) {
                        case -1:
                            setDirection(Direction.LeftDown);
                            break;
                        case 1:
                            setDirection(Direction.RightDown);
                            break;
                        case 0:
                            setDirection(Direction.DOWN);
                            break;
                    }
                    break;
            }
        }

    }
    //    public void move() {
    //        boolean row = false;         //判断上下左右四个方向是否可行
    //        boolean col = false;
    //        int h = 0;
    //        int v = 0;
    //        if (this.mapX < people.getX()) {
    //            h = 1;
    //        }
    //        if (this.mapX > people.getX()) {
    //            h = -1;
    //        }
    //        if (this.mapY > people.getY()) {
    //            v = -1;
    //        }
    //        if (this.mapY < people.getY()) {
    //            v = 1;
    //        }
    //        if (mapX < 39 || mapX > 0) {
    //            row = map[mapX + h][mapY].canWalk();
    //        }
    //        if (mapY < 23 || mapY > 0) {
    //            col = map[mapX][mapY + v].canWalk();
    //        }
    //
    //        if (!row && !col) {
    //            if (canAttackNext(mapX + h, mapY)) {
    //                hurt(mapX + h, mapY);
    //            } else if (canAttackNext(mapX, mapY + v)) {
    //                hurt(mapX, mapY + v);
    //            }
    //        }
    //        if (row) {
    //            map[mapX + h][mapY].newBlock(((Zombie) map[mapX][mapY].status), Block.EnumStatus.zombie);
    //            map[mapX][mapY].ClearWithoutBox();
    //            lastX = mapX;
    //            mapX += h;
    //        }
    //        col = map[mapX][mapY + v].canWalk();
    //        if (col) {
    //            map[mapX][mapY + v].newBlock(((Zombie) map[mapX][mapY].status), Block.EnumStatus.zombie);
    //            map[mapX][mapY].ClearWithoutBox();
    //            lastY = mapY;
    //            mapY += v;
    //        }
    //
    //    }

    /**
     *
     * @param mapX mapX
     * @param mapY mapY
     * @return canAttackNext?
     */
    public boolean canAttackNext(int mapX, int mapY) {
        return map[mapX][mapY].getEnumStatus() == EnumStatus.people;
    }

    /**
     *
     * @param mapX X
     * @param mapY Y
     */
    public void hurt(int mapX, int mapY) {
        if (map[mapX][mapY].status instanceof People) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    people.setHp(people.getHp() - Attack);
                    MediaPlayer peopleHurtMediaPlayer = new MediaPlayer(dataBase.peopleHurtMedia);
                    peopleHurtMediaPlayer.play();
                }
            });
        } else if (map[mapX][mapY].status instanceof Oil) {
            /**
             * 油桶自爆，造成伤害。UI处理
             */
        }
    }

    /**
     *
     */
    protected void setZombieViewDirection() {

    }
;
}
