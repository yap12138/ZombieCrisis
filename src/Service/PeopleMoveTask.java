/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.DataBase;
import entity.Block.Block;
import entity.People.People;
import java.util.TimerTask;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Yaphets
 */
public class PeopleMoveTask extends TimerTask {

    private DataBase dataBase;
    private Block[][] map;
    private People people;
    private Service service;
    private boolean flag;

    /**
     *
     * @param data 数据
     * @param service   逻辑
     */
    public PeopleMoveTask(DataBase data, Service service) {
        this.dataBase = data;
        this.service = service;
        map = dataBase.getMap();
        people = dataBase.getPeople();
    }

    /**
     * 人物行走的任务
     */
    @Override
    public void run() {
        if ((people.getY() + people.v) >= 0 && people.getY() + people.v <= 23) {
            synchronized (DataBase.dataLock) {
                if (map[people.getX() + people.h][people.getY() + people.v].canWalk()) {
                    flag = true;
                    if (map[people.getX() + people.h][people.getY() + people.v].isBox()) {
                        service.getBox(people.getX() + people.h, people.getY() + people.v);   //如果有补给箱的话就吃掉
                    }
                    //map[people.getX()][people.getY() - 1] = map[people.getX()][people.getY()];//往上走一步
                    map[people.getX() + people.h][people.getY() + people.v].newBlock(people, Block.EnumStatus.people);
                    map[people.getX()][people.getY()].newBlock();
                    people.setY(people.getY() + people.v);
                    people.setX(people.getX() + people.h);

                }
                else
                    flag = false;
            }
            if (flag) {
                Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    dataBase.gameUI.setPlayerDirection();
                    PathTransition temp = new PathTransition();
                    Line tLine = new Line();
                    if ((people.x-people.h) * 32 - people.x * 32 ==0 && (people.y-people.v) * 32-people.y * 32 == 0) {
                        return;
                    }
                    tLine.setStartX((people.x-people.h) * 32 -16);
                    tLine.setStartY((people.y-people.v) * 32 -16*3);
                    tLine.setEndX(people.x * 32 - 16);
                    tLine.setEndY(people.y * 32 - 16*3);
                    temp.setPath(tLine);
                    Duration tDuration = new Duration(5000/people.getSpeed());
                    temp.setDuration(tDuration);
                    temp.setNode(dataBase.gameUI.playerImage);
                    temp.play();
                }
            });
            } 
        }
    }

}
