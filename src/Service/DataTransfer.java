/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.PlayerData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class DataTransfer {

    //最大记录
    private static final int MAX_RECORD = 10;
    //存档文件路径
    private String FILE_PATH;

    /**
     *
     * @return PlayerData 
     */
    public ArrayList<PlayerData> loadData() {
        ObjectInputStream ois = null;
        ArrayList<PlayerData> players = new ArrayList<>();

        try {
            File temp = new File(FILE_PATH);
            ois = new ObjectInputStream(new FileInputStream(temp));
                players = (ArrayList<PlayerData>) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return players;
    }

    /**
     *
     * 
     * @throws IOException 异常啊
     */
    public DataTransfer() throws IOException {
        File file = new File("save/record.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        this.FILE_PATH = "save/record.dat";
    }

    /**
     * 用输出对象流，把当前游戏的玩家数据写入到文件中
     *
     * @param playerData 玩家数据
     */
    public void saveData(PlayerData playerData) {
         ArrayList<PlayerData> players = this.loadData();
    //判断记录是否超过MAXRECORD条，如果是，删除最低那一条
        players.add(playerData);
        Collections.sort(players);
        if (players.size()>MAX_RECORD)
        {
        players.remove(players.size()-1);
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            oos.writeObject(players);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * 清除当前本地记录的方法
     *
     * @return PlayerData
     */

    public List<PlayerData> clearData() {
        List<PlayerData> players = this.loadData();
        for (int i = 0; i < 10; i++) {
            players.set(i, new PlayerData("No Data", 0));
        }

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            oos.writeObject(players);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return players;
    }
}
