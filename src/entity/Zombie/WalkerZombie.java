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
public class WalkerZombie extends Zombie {

    /**
     *  
     * @param direction direction 
     * @param dataBase dataBase
     */
    public WalkerZombie(Direction direction ,DataBase.DataBase dataBase) {
        super(100, 20, 10, AttackModel.normal,direction, dataBase);
    }

    /**
     *
     */
    @Override
    protected void setZombieViewDirection() {
        switch(getDirection()){
            case UP:
                ZombieView.setImage(dataBase.gameUI.Zombie[0]);
                break;
            case DOWN:
                ZombieView.setImage(dataBase.gameUI.Zombie[4]);
                break;
            case LEFT:
                ZombieView.setImage(dataBase.gameUI.Zombie[6]);
                break;
            case RIGHT:
                ZombieView.setImage(dataBase.gameUI.Zombie[2]);
                break;
            case LeftUp:
                ZombieView.setImage(dataBase.gameUI.Zombie[7]);
                break;
            case RightUp:
                ZombieView.setImage(dataBase.gameUI.Zombie[1]);
                break;
            case LeftDown:
                ZombieView.setImage(dataBase.gameUI.Zombie[5]);
                break;
            case RightDown:
                ZombieView.setImage(dataBase.gameUI.Zombie[3]);
                break;
            default: break;
        }
    }
    
    
}
