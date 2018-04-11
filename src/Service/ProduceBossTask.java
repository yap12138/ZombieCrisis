/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.DataBase;
import entity.Block.Block;
import entity.Zombie.LinearZombie;
import entity.Zombie.Zombie;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author Yaphets
 */
public class ProduceBossTask extends TimerTask {

    private int proDirection;
    private DataBase dataBase;
    private Block[][] map;
    LinearZombie zombie;

    /**
     *
     * @param Direction 方向
     * @param data 数据
     */
    public ProduceBossTask(int Direction, DataBase data) {
        this.dataBase = data;
        this.map = dataBase.getMap();
        this.proDirection = Direction;

    }

    /**
     * 生成补给箱的任务
     */
    @Override
    public void run() {
        zombie = new LinearZombie(proDirection == 0 ? Zombie.Direction.DOWN : Zombie.Direction.UP, dataBase);
        if (map[19][proDirection].canWalk()) {
            map[19][proDirection].newBlock(zombie, Block.EnumStatus.zombie);
            zombie.setMapX(19);
            zombie.setMapY(proDirection);
        } else if (map[20][proDirection].canWalk()) {
            map[20][proDirection].newBlock(zombie, Block.EnumStatus.zombie);
            zombie.setMapX(20);
            zombie.setMapY(proDirection);
        }
        //System.out.println("生成BOSS");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataBase.gameUI.pane.getChildren().add(zombie.ZombieView);
                /*zombie.ZombieView.setLayoutX(zombie.getMapX()*32);
                        zombie.ZombieView.setLayoutY(zombie.getMapY()*32);*/
                zombie.ZombieView.setLayoutX(16);
                zombie.ZombieView.setLayoutY(16);
            }
        });

        Thread temp = new Thread(zombie, "BOSS" + dataBase.getZombieNum());
        dataBase.setZombieNum(dataBase.getZombieNum() + 1);
        temp.start();
    }
}
