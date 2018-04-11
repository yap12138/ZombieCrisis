/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.Timer;
import java.util.TimerTask;
import DataBase.DataBase;
import entity.Block.Block;
import entity.Zombie.WalkerZombie;
import entity.Zombie.Zombie;
import javafx.application.Platform;
/**
 *
 * @author Yaphets
 */
public class ProduceZombieTask extends TimerTask{
    private Timer waveZombieTimer;
    private int proDirection;
    private DataBase dataBase;
    private Block[][] map;
    WalkerZombie zombie;
    
    /**
     *
     * @param Direction 方向
     * @param data 数据
     */
    public ProduceZombieTask (int Direction, DataBase data) {
        this.dataBase = data;
        this.map = dataBase.getMap();
        this.proDirection = Direction;

    }
    
    /**
     * 生成僵尸的任务
     */
    @Override
    public void run() {
        waveZombieTimer = new Timer("Wave Zombie Thread " + dataBase.getZombieNum()/5, true);
        waveZombieTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!dataBase.gaming)
                    return;
                zombie = new WalkerZombie(proDirection==0?Zombie.Direction.DOWN:Zombie.Direction.UP, dataBase);
                if (map[19][proDirection].canWalk()) {
                    map[19][proDirection].newBlock(zombie, Block.EnumStatus.zombie);
                    zombie.setMapX(19);
                    zombie.setMapY(proDirection);
                }
                else if (map[20][proDirection].canWalk()) {
                    map[20][proDirection].newBlock(zombie, Block.EnumStatus.zombie);
                    zombie.setMapX(20);
                    zombie.setMapY(proDirection);
                }
                //System.out.println("生成僵尸");
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
                
                Thread temp = new Thread(zombie, "Zombie" + dataBase.getZombieNum());
                dataBase.setZombieNum(dataBase.getZombieNum() + 1);
                temp.start();
            }
        }, 10, 3*1000);
        waveZombieTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                waveZombieTimer.cancel();
            }
        }, 16*1000);
        
    }
    
}
