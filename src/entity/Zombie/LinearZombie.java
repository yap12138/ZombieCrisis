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
public class LinearZombie extends Zombie{

    /**
     *  设想中的僵尸攻击方式
     * @param direction direction
     * @param dataBase dataBase
     */
    public LinearZombie(Direction direction ,DataBase.DataBase dataBase) {
        super(500, 50, 30, AttackModel.linearAttack, direction, dataBase);
        setZombieViewDirection();
    }

    /**
     *
     */
    @Override
    protected void setZombieViewDirection() {
        ZombieView.setImage(dataBase.gameUI.boss);
    }
    
}
