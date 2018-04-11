/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import DataBase.DataBase;
import entity.Block.Block;
import entity.Box.Box;
import entity.Box.Box.EnumBox;
import entity.Box.Box.GunBullet;
import java.net.MalformedURLException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*26号已改*/

/**
 *
 * @author Yaphets
 */
public class BoxTask extends TimerTask{

    private DataBase dataBase;      
    private Block[][] map;
    private int x;                  //坐标
    private int y;
    private int flag = 0;           //用于随机产生包
    private Box produceBox;         
    private static int incHP = 30;  //增加生命值的数值
    private static int speedBuff;   //加减速的速度值
    private EnumBox enumBox;        //决定包的类型
    private GunBullet gunBullet;    //决定什么弹药
    
    /**
     *
     * @param data DATA
     */
    public BoxTask(DataBase data){
        this.dataBase = data;
        this.map = dataBase.getMap();
    }
    
    
    
    @Override
    public void run() {
            //地图数据处理，保护数据同步
            synchronized (DataBase.dataLock) {
                if (!dataBase.gaming)
                    return;
                x = (int)(1 + Math.random() * 38);  //找一个空的地方生成补给包
                y = (int)(1 + Math.random() * 22);
                while (map[x][y].enumStatus != Block.EnumStatus.empty){
                    x = (int)(1 + Math.random() * 38);
                    y = (int)(1 + Math.random() * 22);
                }

                flag = (int)(0 + Math.random() * 4);    //随机补给包
                switch(flag){
                    case 0:
                        enumBox = EnumBox.adSpeed;
                        break;
                    case 1:
                        enumBox = EnumBox.bullet;
                        break;
                    case 2:
                        enumBox = EnumBox.deSpeed;
                        break;
                    case 3:
                        enumBox = EnumBox.hp;
                        break;
                }

                flag = (int)(0 + Math.random() * 3);    //随机弹药包
                switch(flag){
                    case 0:
                        gunBullet = GunBullet.grenade;
                        break;
                    case 1:
                        gunBullet = GunBullet.uzi;
                        break;
                    case 2:
                        gunBullet = GunBullet.oil;
                        break;
                }

                if (enumBox == EnumBox.adSpeed)
                    speedBuff = 50;
                else
                    speedBuff = 25;
                produceBox = new Box(x, y, incHP, speedBuff);
                produceBox.setEnumBox(enumBox);
                produceBox.setGunBullet(gunBullet);
                map[x][y].newBlock(produceBox, Block.EnumStatus.box);//生成!
                System.out.println("( " + x + ", " + y  +") 生成" + produceBox.getEnumBox() +" box");
            }
            //UI显示box
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Image t = enumBox==EnumBox.bullet?dataBase.gameUI.P_ammo:dataBase.gameUI.P_box;
                    /*if (enumBox == EnumBox.bullet) 
                        t = dataBase.gameUI.P_ammo;
                    else
                        t = dataBase.gameUI.P_box;*/
                    ImageView temp = new ImageView(t);
                    dataBase.gameUI.pane.getChildren().add(temp);
                    dataBase.gameUI.imageMap[x][y] = temp;
                    dataBase.gameUI.imageMap[x][y].setLayoutX(x*32);
                    dataBase.gameUI.imageMap[x][y].setLayoutY(y*32);
                    dataBase.gameUI.imageMap[x][y].setVisible(true);
                }
            });
            
            
        }
}
