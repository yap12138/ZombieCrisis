/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.People;

import entity.Weapon.Grenade;
import entity.Weapon.Gun;
import entity.Weapon.Oil;
import entity.Weapon.Weapon;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author lenovo
 */
public class People {

    /**
     *
     */
    public int x;

    /**
     *
     */
    public int y;

    /**
     *
     */
    public int h;

    /**
     *
     */
    public int v;
    //血量
    //private int hp;
    private SimpleDoubleProperty hpProperty;
    //移动速度
    private int speed;
    public static Object speedLock = new Object();
    //当前武器
    private Weapon[] weapon = new Weapon[4];    //武器循环数组
    
    int lastWeapon = 3;   //上一武器指针
   
    int nowWeapon = 0;    //现在的武器
    
    int nextWeapon = 1;   //下一武器指针
   
    //武器数目
    private int weaponNum = 1;

    /**
     *
     */
    public enum EnumDirection {

        /**
         *
         */
        east, 

        /**
         *
         */
        south, 

        /**
         *
         */
        west, 

        /**
         *
         */
        north    //人物朝向
    }
    
    private EnumDirection direction;    //人物朝向
    //构造器

    /**
     *
     * @param x x
     * @param y y
     */
    public People(int x,int y) {
        this.hpProperty = new SimpleDoubleProperty(100);
        this.speed = 50;
        this.x = x;
        this.y = y;
        this.direction = EnumDirection.north;
        /*4.30*/
        weapon[0] = new Gun(50, 100000, 10, 4);
        weapon[0].setNowAMMO(100000);
        weapon[1] = new Gun(30, 1000, 100, 8);
        weapon[1].setNowAMMO(100);      //test
        weapon[2] = new Grenade(10, 10, 20, 4);
        weapon[2].setNowAMMO(10);
        weapon[3] = new Oil(100, 10, 20);
        weapon[3].setNowAMMO(10);
    }

    /**
     *
     * @return 获取速度
     */
    public int getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed 速度
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     *
     * @return X
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x setX
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return Y
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
     * @return  Hp
     */
    public int getHp() {
        return hpProperty.intValue();
    }

    /**
     *
     * @param hp    setHp
     */
    public void setHp(int hp) {
        if (hp < 0)
            this.hpProperty.setValue(0);
        else if(hp > 100)
            this.hpProperty.setValue(100);
        else
            this.hpProperty.setValue(hp);
    }
    
    /**
     *
     * @return  Hp
     */
    public SimpleDoubleProperty getHpProperty() {
        return hpProperty;
    }

    /**
     *
     * @return  Weapon[]
     */
    public Weapon[] getWeapon() {
        return weapon;
    }

    /**
     *
     * @param weapon    setWeapon[]
     */
    public void setWeapon(Weapon weapon) {
        this.weapon[this.weaponNum] = weapon;
        this.weaponNum++;
    }

    /**
     *
     * @return num of weapon
     */
    public int getWeaponNum() {
        return weaponNum;
    }

    /**
     *
     * @param weaponNum set weaponNum
     */
    public void setWeaponNum(int weaponNum) {
        this.weaponNum = weaponNum;
    }

    /**
     *
     * @return  People direction
     */
    public EnumDirection getDirection() {
        return direction;
    }

    /**
     *
     * @param direction set People Direction
     */
    public void setDirection(EnumDirection direction) {
        this.direction = direction;
    }

    /**
     *
     * @return LastWeapon
     */
    public int getLastWeapon() {
        return lastWeapon;
    }

    /**
     *
     * @param lastWeapon    set LastWeapon
     */
    public void setLastWeapon(int lastWeapon) {
        this.lastWeapon = lastWeapon;
    }

    /**
     *
     * @return  nowWeapon
     */
    public int getNowWeapon() {
        return nowWeapon;
    }

    /**
     *
     * @param nowWeapon setNowWeapon
     */
    public void setNowWeapon(int nowWeapon) {
        this.nowWeapon = nowWeapon;
    }

    /**
     *
     * @return nextWeapon
     */
    public int getNextWeapon() {
        return nextWeapon;
    }

    /**
     *
     * @param nextWeapon setNextWeapon
     */
    public void setNextWeapon(int nextWeapon) {
        this.nextWeapon = nextWeapon;
    }
    
    
}
