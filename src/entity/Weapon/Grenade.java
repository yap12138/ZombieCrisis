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

/*new 2 bed 4.28 17:23*/
public class Grenade extends Gun{
    private  int damageRange;

    /**
     *
     * @param ATK ATK
     * @param AMMO AMMO
     * @param shootSpeed shootSpeed
     * @param distance distance
     */
    public Grenade(int ATK, int AMMO, int shootSpeed, int distance) {
        super(ATK, AMMO, shootSpeed, 4);
        this.distance = distance;
    }

    /**
     *
     * @return damageRange
     */
    public int getDamageRange() {
        return damageRange;
    }

    /**
     *
     * @param damageRange damageRange
     */
    public void setDamageRange(int damageRange) {
        this.damageRange = damageRange;
    }    
}
