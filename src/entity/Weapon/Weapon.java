/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Weapon;

/**
 *
 * @author Administrator
 */
public class Weapon {

    /**
     *
     */
    protected int ATK;
    //武器的攻击力

    /**
     *
     */
    protected int AMMO;
    //武器的总弹药数

    /**
     *
     */
    protected int nowAMMO;
    //当前剩余弹药数
    
    /**
     *
     */
    protected int speed;

    /**
     *
     */
    protected int distance;

    /**
     *
     * @return distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     *
     * @param distance distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    /**
     *
     * @return speed;
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
     * @return AMMO
     */
    public int getAMMO() {
        return AMMO;
    }

    /**
     *
     * @return ATK
     */
    public int getATK() {
        return ATK;
    }

    /**
     *
     * @return nowAMMO
     */
    public int getNowAMMO() {
        return nowAMMO;
    }

    /**
     *
     * @param AMMO nowAMMO
     */
    public void setAMMO(int AMMO) {
        this.AMMO = AMMO;
    }

    /**
     *
     * @param ATK ATK
     */
    public void setATK(int ATK) {
        this.ATK = ATK;
    }
    /*4.30*/

    /**
     *
     * @param nowAMMO nowAMMO
     */

    public void setNowAMMO(int nowAMMO) {
        if (nowAMMO < 0) 
            this.nowAMMO = 0;
        else if (nowAMMO > AMMO)
            this.nowAMMO = AMMO;
        else
            this.nowAMMO = nowAMMO;
    }

    /**
     *
     * @param ATK ATK
     * @param AMMO AMMO
     * @param distance distance
     */
    public Weapon(int ATK, int AMMO, int distance) {
        this.ATK = ATK;
        this.AMMO = AMMO;
        /*4.30*/
        this.nowAMMO = 0;
        this.distance = distance;
    }
  
    
    

    
    
    
}
