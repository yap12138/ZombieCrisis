/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Block;

import entity.Box.Box;


/**
 *
 * @author Cheung
 */
public class Block {

    /**
     *
     */
    public static enum EnumStatus {

        /**
         *
         */
        box,

        /**
         *
         */
        boss,

        /**
         *
         */
        zombie,

        /**
         *
         */
        oil,

        /**
         *
         */
        wall,

        /**
         *
         */
        empty,

        /**
         *
         */
        bullet,

        /**
         *
         */
        people,

        /**
         *
         */
        grenade;
    }

    /**
     *
     */
    public Object status;           //Father是父类，当前格子被什么占用

    /**
     *
     */
    public EnumStatus enumStatus;   //当前格子被什么占用，这个方便用于处理信息

    /**
     *
     */
    public Box box;
    
    /**
     *
     */
    public Block() {
        this.status = null;
        this.box = null;
        this.enumStatus = EnumStatus.empty; 
    }
    
    /**
     *
     * @param status 用于存当前格的对象
     * @param enumStatus 当前对象对应的枚举量
     */
    public void newBlock(Object status, EnumStatus enumStatus)  {
        if (this.enumStatus == EnumStatus.box)
               box = (Box) this.status;
        this.status = status;
        this.enumStatus = enumStatus;
        
    }
    
    /**
     *
     */
    public void newBlock()  {
        this.status = null;
        this.box = null;
        this.enumStatus = EnumStatus.empty; 
    }
    
    /**
     *
     */
    public void ClearWithoutBox(){
        if (this.box != null)
        {
            this.enumStatus = EnumStatus.box;
            this.status = this.box;
        }
        else {
            this.status = null;
            this.enumStatus = EnumStatus.empty; 
        }
    }
    

    /**
     *
     * @return 这个格子是否可以给人,僵尸,子弹等通过.
     */
    public boolean canWalk() {
        return enumStatus == EnumStatus.box     ||
               enumStatus == EnumStatus.empty   ||
               enumStatus == EnumStatus.bullet  ||
               enumStatus == EnumStatus.grenade ;
    }

    /**
     *
     * @param status 赋予对象
     */
    public void setStatus(Object status) {
        this.status = status;
    }

    /**
     *
     * @return 获取对象
     */
    public Object getStatus() {
        return status;
    }

    /**
     *
     * @return 获取对象枚举量
     */
    public EnumStatus getEnumStatus() {
        return enumStatus;
    }

    /**
     *
     * @param enumStatus 设置对象枚举量
     */
    public void setEnumStatus(EnumStatus enumStatus) {
        this.enumStatus = enumStatus;
    }

    //用于判断是否补给包

    /**
     *
     * @return 是否补给包
     */
    public boolean isBox(){
        return enumStatus == EnumStatus.box;
    }
    
}


