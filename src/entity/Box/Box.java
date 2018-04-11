/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Box;

import DataBase.DataBase;
import entity.Block.Block;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cheung
 */
public class Box{

    int x;
    int y;
    int speed;
    int hp;

    /**
     *
     */
    public enum EnumBox{

        /**
         *
         */
        hp,

        /**
         *
         */
        adSpeed,

        /**
         *
         */
        deSpeed,

        /**
         *
         */
        bullet   //血包,加速包,减速包,弹药包
    }

    /**
     *
     */
    public enum GunBullet{

        /**
         *
         */
        uzi,

        /**
         *
         */
        oil,

        /**
         *
         */
        grenade         //uzi弹药包,油桶弹药包,手榴弹弹药包
    }
    int number = 0;         //弹药包的含弹量
    
    /**
     *
     */
    public EnumBox enumBox;        //决定包的类型

    /**
     *
     */
    public GunBullet gunBullet;    //决定什么弹药
    
    /**
     *
     * @param x x
     * @param y y
     * @param hp hp
     * @param speed speed
     */
    public Box(int x, int y, int hp, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.hp = hp;
    }

    /**
     *
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y setY
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     *
     * @return hp
     */
    public int getHp() {
        return hp;
    }

    /**
     *
     * @param hp hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     *
     * @param number number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     *
     * @return enumBox
     */
    public EnumBox getEnumBox() {
        return enumBox;
    }

    /**
     *
     * @param enumBox enumBox
     */
    public void setEnumBox(EnumBox enumBox) {
        this.enumBox = enumBox;
    }

    /**
     *
     * @return gunBullet
     */
    public GunBullet getGunBullet() {
        return gunBullet;
    }

    /**
     *
     * @param gunBullet gunBullet
     */
    public void setGunBullet(GunBullet gunBullet) {
        this.gunBullet = gunBullet;
    }
    
    
}
