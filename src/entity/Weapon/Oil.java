/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Weapon;

import DataBase.DataBase;
import entity.Block.Block;
import entity.People.People;
import entity.Zombie.Zombie;

/**
 * 
 * @author Yaphets
 * 更改攻击判定，防止越界
 */

/**
 *
 * @author Cheung
 */
public class Oil extends Weapon{

    private int x;
    private int y;
    private int healthPoint;
    private DataBase dataBase;
    private Block[][] map;

    /**
     *
     * @param x x
     * @param y y
     * @param attack attack
     * @param healthPoint healthPoint
     * @param dataBase dataBase
     */
    public Oil(int x, int y, int attack, int healthPoint, DataBase dataBase) {
        super(attack, 1, 0);
        this.x = x;
        this.y = y;
        this.healthPoint = healthPoint;
        this.dataBase = dataBase;
        this.map = this.dataBase.getMap();
    }
    
    /**
     *
     * @param attack attack
     * @param oilnum oilnum
     * @param speed speed
     */
    public Oil(int attack, int oilnum, int speed) {
        super(attack, oilnum, 0);
        this.speed = speed;
    }

    /**
     *
     * @param dataBase dataBase
     */
    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    /**
     *
     * @param healthPoint healthPoint
     */
    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
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
     * @return x
     */
    public int getX() {
        return x;
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
     * @param y y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return healthPoint
     */
    public int getHealthPoint() {
        return healthPoint;
    }

   /* @Override
    public void run() {
        while (true) {
            if (this.healthPoint <= 0) {         //当油桶被伤害,boom!
                if (canAttackNext(x - 1, y - 1)) {
                    hurt(x-1, y-1);
                }
                if (canAttackNext(x - 1, y)) {
                    hurt(x-1, y);
                }
                if (canAttackNext(x - 1, y + 1)) {
                    hurt(x-1, y+1);
                }
                if (canAttackNext(x, y - 1)) {
                    hurt(x, y-1);
                }
                if (canAttackNext(x, y + 1)) {
                    hurt(x, y+1);
                }
                if (canAttackNext(x + 1, y - 1)) {
                    hurt(x+1, y-1);
                }
                if (canAttackNext(x + 1, y)) {
                    hurt(x+1, y);
                }
                if (canAttackNext(x + 1, y - 1)) {
                    hurt(x+1, y-1);
                }
                map[x][y].ClearWithoutBox();    //清除油桶
                break;
            }
        }
    }

    public boolean canAttackNext(int x, int y) {
        if (!(x<0 || x>39 || y<0 || y>23)) {
            return false;
        }
        return map[x][y].getEnumStatus() == Block.EnumStatus.zombie || map[x][y].getEnumStatus() == Block.EnumStatus.oil;
    }
    
    public void hurt(int mapX, int mapY) {
        if (map[mapX][mapY].status instanceof People) {
            People temp = (People) map[mapX][mapY].status;
            temp.setHp(temp.getHp() - attack);
        }
        else if (map[mapX][mapY].status instanceof Zombie) {
            Zombie temp = (Zombie) map[mapX][mapY].status;
            temp.defend(attack);
        }
    }
*/
}
