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
public class Gun extends Weapon{
    //给予
    //攻击力   子弹   射速

    /**
     *
     * @param ATK ATK
     * @param AMMO AMMO
     * @param shootSpeed shootSpeed
     * @param distance distance
     */
     public Gun(int ATK,int AMMO,int shootSpeed, int distance) {
    super(ATK, AMMO, distance);
    this.speed=shootSpeed;  
    }

}
