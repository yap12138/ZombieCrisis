/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Zombie;

/**
 *
 * @author Yaphets
 */
public class CircleZombie extends Zombie{
    
    /**
     *  设想中的僵尸攻击方式
     * @param direction direction 
     * @param dataBase dataBase
     */
    public CircleZombie(Direction direction ,DataBase.DataBase dataBase) {
        super(2000, 50, 300, AttackModel.circleAttack, direction, dataBase);
    }
    
}
