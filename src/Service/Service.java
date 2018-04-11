/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /* Yaphets 26晚改 添加boxTask的线程任务 */
package Service;

import DataBase.DataBase;
import entity.Block.Block;
import entity.Box.Box;
import entity.People.People;
import entity.Weapon.Weapon;
import entity.Zombie.Zombie;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Yaphets 添加线程处理盒子创建 0表示上出怪，23下
 */

/**
 *
 * @author lenovo
 */
public class Service {

    // 1 bed

    /**
     *  
     */
    public Timer timerBoom = new Timer("Boom Thread", true);
    private ShootTask pt;

    /**
     *  各个线程任务
     */
    public Timer peopleMoveTimer = new Timer("People Move Thread", true);
    public Timer shootTimer = new Timer("People shoot Thread", true);
    private ProduceZombieTask proUpZombieTask;
    private ProduceZombieTask proDownZombieTask;
    private ProduceBossTask produceBossTask;
    private PeopleMoveTask movePeopleTask;
    private BoxTask createBoxTask;
    
    /**
     * 人物的键位
     */
    private KeyCode keyUP;
    private KeyCode keyDown;
    private KeyCode KeyLeft;
    private KeyCode KeyRight;
    private KeyCode attackCode;
    private KeyCode nextCode;
    private KeyCode lastCode;
    private KeyCode stop;

    private DataBase dataBase;
    private People people;
    private Block[][] map;
    private Weapon[] weapon;
    private boolean isMove = false;

    /**
     *
     * @param isMove 是否移动
     */
    public void setIsMove(boolean isMove) {
        this.isMove = isMove;
    }

    /**
     *
     * @param dataBase dataBase
     */
    public Service(DataBase dataBase) {
        this.dataBase = dataBase;
        map = dataBase.getMap();
    }

    /**
     *  
     */
    public void startGame() {
        dataBase.gaming = true;
        dataBase.gameUI.InitLabel();
        dataBase.initialMap();
        people = dataBase.getPeople();
        weapon = people.getWeapon();
        dataBase.gameUI.bindHpBar();
        dataBase.gameUI.creatMap();
        this.people.getHpProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (people.getHpProperty().getValue() == 0) {
                    dataBase.gaming = false;
                    gameOver();
                }
            }
        });
        
        initBoxTask();
        this.produceUpZombie();
        this.produceDownZombie();
        this.produceBossZombie();
        dataBase.gameUI.AMMOText.setText(
                dataBase.getPeople().getWeapon()[dataBase.getPeople().getNowWeapon()].getNowAMMO()
                + " / "
                + dataBase.getPeople().getWeapon()[dataBase.getPeople().getNowWeapon()].getAMMO()
        );
    }

    /**
     *  获取人物的动作
     */
    public void PeopleMove() {
        if (dataBase.getKeyCode() == dataBase.getKeyUP()
                || dataBase.getKeyCode() == dataBase.getKeyDown()
                || dataBase.getKeyCode() == dataBase.getKeyLeft()
                || dataBase.getKeyCode() == dataBase.getKeyRight()) {
            int i;
            dataBase.isExist = false;
            for (i = 0; i < 4; i++) {
                if (dataBase.keyPress[i] == dataBase.getKeyCode()) {
                    dataBase.isExist = true;
                    break;
                }
            }
            if (!dataBase.isExist) {
                for (i = 0; i < 4; i++) {
                    if (dataBase.keyPress[i] == null) {
                        dataBase.keyPress[i] = dataBase.getKeyCode();
                        if (dataBase.getKeyCode() == dataBase.getKeyUP()) {
                            people.setDirection(People.EnumDirection.north); //面朝北
                            dataBase.getPeople().v--;
                        }
                        if (dataBase.getKeyCode() == dataBase.getKeyDown()) {
                            people.setDirection(People.EnumDirection.south); //面朝南
                            dataBase.getPeople().v++;
                        }
                        if (dataBase.getKeyCode() == dataBase.getKeyLeft()) {
                            people.setDirection(People.EnumDirection.west); //面朝西
                            dataBase.getPeople().h--;
                        }
                        if (dataBase.getKeyCode() == dataBase.getKeyRight()) {
                            people.setDirection(People.EnumDirection.east); //面朝东
                            dataBase.getPeople().h++;
                        }
                        break;
                    }
                }
            }
            if (!dataBase.running) {
                setMovePeopleTask();
                peopleMoveTimer.schedule(movePeopleTask, 0, 5000 / dataBase.getPeople().getSpeed());
                dataBase.running = true;
            }
        }
        /*4.30*/
        if (dataBase.getKeyCode() == dataBase.getAttackCode()) {
            if (dataBase.attackPress == null) {
                dataBase.attackPress = dataBase.getAttackCode();
                pt = new ShootTask(this, dataBase);
                shootTimer.schedule(pt, 0, 10000 / (people.getWeapon()[people.getNowWeapon()].getSpeed()));
            }
        }
        /*4.30*/
        if (dataBase.getKeyCode() == dataBase.getLastCode()) {
            //now = last;
            people.setNowWeapon(people.getLastWeapon());
            dataBase.gameUI.NowWeaponImage.setImage(dataBase.gameUI.weaponImage[people.getNowWeapon()]);
            dataBase.gameUI.AMMOText.setText(
                    dataBase.getPeople().getWeapon()[dataBase.getPeople().getNowWeapon()].getNowAMMO()
                    + " / "
                    + dataBase.getPeople().getWeapon()[dataBase.getPeople().getNowWeapon()].getAMMO()
            );
            //last = (last + 3) % 4;
            people.setLastWeapon((people.getLastWeapon() + 3) % 4);
            //next = (next + 3) % 4;
            people.setNextWeapon((people.getNextWeapon() + 3) % 4);
        }
        if (dataBase.getKeyCode() == dataBase.getNextCode()) {
            //now = next;
            people.setNowWeapon(people.getNextWeapon());
            dataBase.gameUI.NowWeaponImage.setImage(dataBase.gameUI.weaponImage[people.getNowWeapon()]);
            dataBase.gameUI.AMMOText.setText(
                    dataBase.getPeople().getWeapon()[dataBase.getPeople().getNowWeapon()].getNowAMMO()
                    + " / "
                    + dataBase.getPeople().getWeapon()[dataBase.getPeople().getNowWeapon()].getAMMO()
            );
            //last = (last + 1) % 4;
            people.setLastWeapon((people.getLastWeapon() + 1) % 4);
            //next = (next + 1) % 4;
            people.setNextWeapon((people.getNextWeapon() + 1) % 4);
        }

    }

    /**
     * 玩家吃补给箱
     *
     * @param x x
     * @param y y
     * 
     */
    public void getBox(int x, int y) {
        Box temp = (Box) (map[x][y].status);
        switch (temp.enumBox) {   //判断补给包类型
            case hp:
                people.setHp(people.getHp() + temp.getHp());      //加血
                break;
            case adSpeed:
                synchronized (People.speedLock) {
                    people.setSpeed(people.getSpeed() + temp.getSpeed());   //加速
                }

                movePeopleTask.cancel();
                setMovePeopleTask();
                peopleMoveTimer.schedule(movePeopleTask, 0, 5000 / people.getSpeed());
                
                Timer timer = new Timer("TimerOut deSpeed", true);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        synchronized (People.speedLock) {
                            people.setSpeed(people.getSpeed() - 50); //时间到了减回来
                        }
                        movePeopleTask.cancel();
                        setMovePeopleTask();
                        peopleMoveTimer.schedule(movePeopleTask, 0, 5000 / people.getSpeed());
                        timer.cancel();
                    }
                }, 5 * 1000);

                break;
            case deSpeed:
                synchronized (People.speedLock) {
                    people.setSpeed(people.getSpeed() - temp.getSpeed());   //减速
                }

                movePeopleTask.cancel();
                setMovePeopleTask();
                peopleMoveTimer.schedule(movePeopleTask, 0, 5000 / people.getSpeed());

                Timer timer2 = new Timer("TimerOut addSpeed", true);
                timer2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        synchronized (People.speedLock) {
                            people.setSpeed(people.getSpeed() + 25); //时间到了加回来
                        }

                        movePeopleTask.cancel();
                        setMovePeopleTask();
                        peopleMoveTimer.schedule(movePeopleTask, 0, 5000 / people.getSpeed());

                        timer2.cancel();
                    }
                }, 5 * 1000);

                break;
            case bullet:
                switch (((Box) (map[x][y].status)).gunBullet) { //判断是什么子弹
                    /*4.30*/
                    case uzi://1
                        weapon[1].setNowAMMO(weapon[1].getNowAMMO() + 50);
                        break;
                    case grenade://2
                        weapon[2].setNowAMMO(weapon[2].getNowAMMO() + 5);
                        break;
                    case oil://3
                        weapon[3].setNowAMMO(weapon[3].getNowAMMO() + 5);
                        break;
                }
        }
        initBoxTask();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataBase.gameUI.imageMap[x][y].setVisible(false);
                dataBase.gameUI.pane.getChildren().remove(dataBase.gameUI.imageMap[x][y]);
                dataBase.gameUI.imageMap[x][y] = new ImageView();
            }
        });
        //System.out.println("吃了个盒子");
        map[x][y].newBlock();
    }

    /**
     *
     */
    public void initBoxTask() {             //Y改
        dataBase.boxCreate.purge();
        createBoxTask = new BoxTask(dataBase);
        dataBase.boxCreate.schedule(createBoxTask, 7 * 1000);

    }

    /**
     *
     */
    public void setMovePeopleTask() {
        movePeopleTask = new PeopleMoveTask(dataBase, this);
    }

    /**
     *
     * 用于多键位响应,当键位松开执行操作
     * 
     */
    public void releasedKey() {
        if (dataBase.getKeyCode() == dataBase.getKeyUP()
                || dataBase.getKeyCode() == dataBase.getKeyDown()
                || dataBase.getKeyCode() == dataBase.getKeyLeft()
                || dataBase.getKeyCode() == dataBase.getKeyRight()) {
            int i;
            for (i = 0; i < 4; i++) {
                if (dataBase.keyPress[i] == dataBase.getKeyCode()) {
                    if (dataBase.getKeyCode() == dataBase.getKeyUP()) {
                        dataBase.getPeople().v++;
                    }
                    if (dataBase.getKeyCode() == dataBase.getKeyDown()) {
                        dataBase.getPeople().v--;
                    }
                    if (dataBase.getKeyCode() == dataBase.getKeyLeft()) {
                        dataBase.getPeople().h++;
                    }
                    if (dataBase.getKeyCode() == dataBase.getKeyRight()) {
                        dataBase.getPeople().h--;
                    }
                    dataBase.keyPress[i] = null;
                    break;
                }
            }
            int count = 0;
            for (i = 0; i < 4; i++) {
                if (dataBase.keyPress[i] != null) {
                    count++;
                }
            }
            if (count == 1) {
                for (i = 0; i < 4; i++) {
                    if (dataBase.keyPress[i] != null) {
                        if (dataBase.keyPress[i] == dataBase.getKeyUP()) {
                            dataBase.getPeople().setDirection(People.EnumDirection.north);
                        } else if (dataBase.keyPress[i] == dataBase.getKeyDown()) {
                            dataBase.getPeople().setDirection(People.EnumDirection.south);
                        } else if (dataBase.keyPress[i] == dataBase.getKeyLeft()) {
                            dataBase.getPeople().setDirection(People.EnumDirection.west);
                        } else if (dataBase.keyPress[i] == dataBase.getKeyRight()) {
                            dataBase.getPeople().setDirection(People.EnumDirection.east);
                        }
                        break;
                    }
                }
            }
            for (i = 0; i < 4; i++) {
                if (dataBase.keyPress[i] != null) {
                    break;
                }
            }
            if (i == 4) {
                movePeopleTask.cancel();
                dataBase.running = false;
            }
        }

        if (dataBase.attackPress != null && dataBase.getKeyCode() == dataBase.attackPress) {
            dataBase.attackPress = null;
            pt.cancel();
        }
    }

    /**
     * 在上出怪口生成僵尸
     */
    private void produceUpZombie() {
        proUpZombieTask = new ProduceZombieTask(0, this.dataBase);
        dataBase.produceZomibe.schedule(this.proUpZombieTask, 3 * 1000, 60 * 1000);
        //dataBase.produceZomibe.schedule(this.proUpZombieTask,15*1000);
    }

    /**
     * 在下出怪口生成僵尸
     */
    private void produceDownZombie() {
        proDownZombieTask = new ProduceZombieTask(23, this.dataBase);
        dataBase.produceZomibe.schedule(this.proDownZombieTask, 10 * 1000, 60 * 1000);
    }

    /**
     * 生成boss僵尸
     */
    private void produceBossZombie() {
        produceBossTask = new ProduceBossTask(0, this.dataBase);
        dataBase.produceZomibe.schedule(produceBossTask, 20 * 1000, 60 * 1000);
    }

    /**
     * 游戏结束
     */
    private void gameOver() {
        try {
            Thread.currentThread().sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        MediaPlayer peopleDieMediaPlayer = new MediaPlayer(dataBase.peopleDieMedia);
        peopleDieMediaPlayer.play();
        dataBase.gameUI.clearUI();
        dataBase.produceZomibe.purge();
        dataBase.boxCreate.purge();

        //peopleMoveTimer.purge();
        proDownZombieTask.cancel();
        proUpZombieTask.cancel();
        produceBossTask.cancel();
        dataBase.gameUI.userPane.setVisible(true);
        dataBase.gameUI.userPane.toFront();
        dataBase.gameUI.userPane.requestFocus();

    }

    /**
     * 僵尸受伤
     * @param x
     * @param y 
     */
    void zombieGetHurt(int x, int y) {
        ((Zombie) map[x][y].status).defend(weapon[people.getNowWeapon()].getATK());
        //System.out.println(((Zombie) map[x][y].status).getHealthPoint());
    }

    /**
     * 油桶爆炸
     * @param x
     * @param y 
     */
    /*new 2 bed 4.28 17:23*/
    void boom(int x, int y) {
        map[x][y].ClearWithoutBox();
        //当油桶被伤害,boom!

        if (canAttackNext(x - 1, y - 1)) {
            hurt(x - 1, y - 1);
        }
        if (canAttackNext(x - 1, y)) {
            hurt(x - 1, y);
        }
        if (canAttackNext(x - 1, y + 1)) {
            hurt(x - 1, y + 1);
        }
        if (canAttackNext(x, y - 1)) {
            hurt(x, y - 1);
        }
        if (canAttackNext(x, y + 1)) {
            hurt(x, y + 1);
        }
        if (canAttackNext(x + 1, y - 1)) {
            hurt(x + 1, y - 1);
        }
        if (canAttackNext(x + 1, y)) {
            hurt(x + 1, y);
        }
        if (canAttackNext(x + 1, y - 1)) {
            hurt(x + 1, y - 1);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataBase.gameUI.getImageMap()[x][y].setImage(dataBase.gameUI.P_boom);
                dataBase.gameUI.getImageMap()[x][y].setVisible(true);
                TimerTask t = new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                MediaPlayer oilMediaPlayer = new MediaPlayer(dataBase.oilMedia);
                                oilMediaPlayer.play();
                                dataBase.gameUI.getImageMap()[x][y].setVisible(false);
                                //dataBase.gameUI.pane.getChildren().remove(dataBase.gameUI.getImageMap()[x][y]);
                            }
                        });
                    }
                };
                timerBoom.schedule(t, 100);
            }
        });

    }

    /**
     * 判断能否对下一步的单位造成伤害
     * 
     * @param x x
     * @param y y
     * @return canAttackNext
     */
    public boolean canAttackNext(int x, int y) {
        if (x < 0 || x > 39 || y < 0 || y > 23) {
            return false;
        }
        return map[x][y].getEnumStatus() == Block.EnumStatus.zombie || map[x][y].getEnumStatus() == Block.EnumStatus.oil
               ;
    }

    /*new 2 bed 4.28 17:23*/

    /**
     *
     * 攻击
     * @param mapX x
     * @param mapY y
     */

    public void hurt(int mapX, int mapY) {
        if (Block.EnumStatus.empty != map[mapX][mapY].getEnumStatus()) {
            switch (map[mapX][mapY].getEnumStatus()) {
                /*case people:
                    Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        people.setHp(people.getHp() - 50);
                        }
                    });
                    break;*/
                case zombie:
                    ((Zombie) map[mapX][mapY].status).defend(100);
                    break;
                case oil:
                    boom(mapX, mapY);
                    break;
                default:
                    break;
            }
        }
    }
}
