/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class PlayerData implements Serializable,Comparable<PlayerData>{
    private int score;
    private String name;

    /**
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param score score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(PlayerData otherPlayerData) {
        return otherPlayerData.score-this.score;
    }

    /**
     *
     * @param name name
     * @param score score
     */
    public PlayerData(String name,int score) {
        this.score = score;
        this.name = name;
    }
    
    @Override
    public String toString()
    {
    return name+"\t"+score;
    }
    
    
}
