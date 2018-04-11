/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /* Yaphets 26晚改 
    人物方向枚举去掉，引用people类里的枚举
 */
package DataBase;

import Service.DataTransfer;
import UI.GameUI;
import entity.Block.Block;
import entity.People.People;
import entity.People.People.EnumDirection;
import entity.Weapon.Wall;
import entity.Weapon.Oil;
import java.io.File;
import java.util.Timer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Yaphets 创建boxTask的线程Timer 新增GameUI成员 zombie num
 */

/**
 *
 * @author lenovo
 */
public class DataBase {

    /**
     *  
     */
    public Timer boxCreate;

    /**
     *
     */
    public static Object dataLock = new Object();

    /**
     *
     */
    public Timer produceZomibe;

    /**
     *
     */
    public GameUI gameUI;
    //dataIO
    private DataTransfer dataTransfer;
    //music
    private File battBgmFile = new File("Resource/music/bgm01.mp3");
    private File ico = new File("Resource/image/ico.png");
    public Image icoImage = new Image(ico.toURI().toURL().toExternalForm());
    /**
     *
     */
    private Media battleBgmMedia = new Media(battBgmFile.toURI().toURL().toExternalForm());
    private MediaPlayer battleBgmMediaPlayer = new MediaPlayer(battleBgmMedia);

    private File menubgmFile = new File("Resource/music/menuBgm.mp3");
    private Media bgmMedia = new Media(menubgmFile.toURI().toURL().toExternalForm());
    MediaPlayer menuBgmMediaPlayer = new MediaPlayer(bgmMedia);

    
    /**
     *
     */
    public File gunbgmFile = new File("Resource/music/gun.mp3");

    /**
     *
     */
    public Media gunMedia = new Media(gunbgmFile.toURI().toURL().toExternalForm());
    private MediaPlayer gunBgmMediaPlayer = new MediaPlayer(gunMedia);
    
    /**
     *
     */
    public File grenadebgmFile = new File("Resource/music/grenade.mp3");

    /**
     *
     */
    public Media grenadeMedia = new Media(grenadebgmFile.toURI().toURL().toExternalForm());

    /**
     *
     */
    public File peopleHurtbgmFile = new File("Resource/music/playerHurt.mp3");

    /**
     *
     */
    public Media peopleHurtMedia = new Media(peopleHurtbgmFile.toURI().toURL().toExternalForm());
    
    /**
     *
     */
    public File oilbgmFile = new File("Resource/music/oil.mp3");

    /**
     *
     */
    public Media oilMedia = new Media(oilbgmFile.toURI().toURL().toExternalForm());
    
    /**
     *
     */
    public File peopleDiebgmFile = new File("Resource/music/playerDie.mp3");

    /**
     *
     */
    public Media peopleDieMedia = new Media(peopleDiebgmFile.toURI().toURL().toExternalForm());
    
    /**
     *
     */
    public File zombieDiebgmFile = new File("Resource/music/zombieDie.mp3");

    /**
     *
     */
    public Media zombieDieMedia = new Media(peopleDiebgmFile.toURI().toURL().toExternalForm());

    /**
     *
     * @return gunBgmMediaPlayer
     */
    public MediaPlayer getGunBgmMediaPlayer() {
        return gunBgmMediaPlayer;
    }
    
    /**
     *
     * @return battleBgmMediaPlayer
     */
    public MediaPlayer getBattleBgmMediaPlayer() {
        return battleBgmMediaPlayer;
    }

    /**
     *
     * @return menuBgmMediaPlayer
     */
    public MediaPlayer getMenuBgmMediaPlayer() {
        return menuBgmMediaPlayer;
    }
    /**
     * 键位设置
     * 
     */
    private KeyCode KeyUP;
    private KeyCode KeyDown;
    private KeyCode KeyLeft;
    private KeyCode KeyRight;
    private KeyCode attackCode;
    private KeyCode nextCode;
    private KeyCode lastCode;
    private KeyCode pause;

    private KeyCode keyCode;
    
    private KeyCode[] keySetting = new KeyCode[8];
    
    /**
     *
     */
    public KeyCode attackPress = null;

    private void InitKeycodeList() {
        KeyUP = KeyCode.UP;
        KeyDown = KeyCode.DOWN;
        KeyLeft = KeyCode.LEFT;
        KeyRight = KeyCode.RIGHT;
        attackCode = KeyCode.SPACE;
        pause = KeyCode.P;
        lastCode = KeyCode.Q;
        nextCode = KeyCode.E;
        keySetting[0] = KeyUP;
        keySetting[1] = KeyDown;
        keySetting[2] = KeyLeft;
        keySetting[3] = KeyRight;
        keySetting[4] = attackCode;
        keySetting[5] = nextCode;
        keySetting[6] = lastCode;
        keySetting[7] = pause;
    }
    
    /**
     *
     * @param keyCode keyCode
     * @return isKeySetted
     */
    public boolean isKeySetted(KeyCode keyCode) {
        for (KeyCode elem : keySetting) {
            if (elem == keyCode) {
                return true;
            }
        }
        return false;
    }
    
    //1 bed

    /**
     *
     */
    public boolean running;

    /**
     *
     */
    public boolean isExist;

    /**
     *
     */
    public KeyCode[] keyPress = new KeyCode[4];

    /**
     *
     * @return keyCode
     */
    public KeyCode getKeyCode() {
        return keyCode;
    }

    /**
     *
     * @param keyCode keyCode
     */
    public void setKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    //关卡等级
    private int level;
    //玩家
    private People people;

    private volatile Block[][] map = new Block[40][24];    //地图
    private int zombieNum;

    private int score;
    
    /**
     *
     */
    public boolean gaming;

    EnumDirection direction;//人物朝向

    /**
     *
     * @throws Exception Exception 
     */
    public DataBase() throws Exception {
        this.boxCreate = new Timer("Create Box Thread", true);          //Y改
        this.produceZomibe = new Timer("Produce Zombie Thread", true);
        
        //1 bed
        this.keyPress[0] = null;
        this.keyPress[1] = null;
        this.keyPress[2] = null;
        this.keyPress[3] = null;
        this.running = false;
        this.gaming = false;
        
        InitKeycodeList();
        
        dataTransfer = new DataTransfer();
        battleBgmMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        menuBgmMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
    
    /**
     *
     * @return  zombieNum
     */
    public int getZombieNum() {
        return zombieNum;
    }

    /**
     *
     * @param zombieNum zombieNum
     */
    public void setZombieNum(int zombieNum) {
        this.zombieNum = zombieNum;
    }

    /**
     *
     * @return  KeyUP
     */
    public KeyCode getKeyUP() {
        return KeyUP;
    }

    /**
     *
     * @param keyUP keyUP
     */
    public void setKeyUP(KeyCode keyUP) {
        this.KeyUP = keyUP;
        keySetting[0] = keyUP;
    }

    /**
     *
     * @return KeyDown
     */
    public KeyCode getKeyDown() {
        return KeyDown;
    }

    /**
     *
     * @param keyDown keyDown
     */
    public void setKeyDown(KeyCode keyDown) {
        this.KeyDown = keyDown;
        keySetting[1] = keyDown;
    }

    /**
     *
     * @return KeyLeft
     */
    public KeyCode getKeyLeft() {
        return KeyLeft;
    }

    /**
     *
     * @param keyLeft KeyLeft
     */
    public void setKeyLeft(KeyCode keyLeft) {
        this.KeyLeft = keyLeft;
        keySetting[2] = keyLeft;
    }

    /**
     *
     * @return KeyRight
     */
    public KeyCode getKeyRight() {
        return KeyRight;
    }

    /**
     *
     * @param KeyRight KeyRight
     */
    public void setKeyRight(KeyCode KeyRight) {
        this.KeyRight = KeyRight;
        keySetting[3] = KeyRight;
    }

    /**
     *
     * @return attackCode
     */
    public KeyCode getAttackCode() {
        return attackCode;
    }

    /**
     *
     * @param attackCode attackCode
     */
    public void setAttackCode(KeyCode attackCode) {
        this.attackCode = attackCode;
        keySetting[4] = attackCode;
    }

    /**
     *
     * @return nextCode
     */
    public KeyCode getNextCode() {
        return nextCode;
    }

    /**
     *
     * @param nextCode nextCode
     */
    public void setNextCode(KeyCode nextCode) {
        this.nextCode = nextCode;
        keySetting[5] = nextCode;

    }

    /**
     *
     * @return lastCode
     */
    public KeyCode getLastCode() {
        return lastCode;
    }

    /**
     *
     * @param lastCode lastCode
     */
    public void setLastCode(KeyCode lastCode) {
        this.lastCode = lastCode;
        keySetting[6] = lastCode;
    }

    /**
     *
     * @return pause
     */
    public KeyCode getPause() {
        return pause;
    }

    /**
     *
     * @param pause pause
     */
    public void setPause(KeyCode pause) {
        this.pause = pause;
        keySetting[7] = pause;
    }

    /**
     *
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param level level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     *
     * @return people
     */
    public People getPeople() {
        return people;
    }

    /**
     *
     * @param people people
     */
    public void setPeople(People people) {
        this.people = people;
    }

    /**
     *
     * @return map
     */
    public Block[][] getMap() {
        return map;
    }

    /**
     *
     * @param map map
     */
    public void setMap(Block[][] map) {
        this.map = map;
    }

    /**
     *
     * @return score
     */
    public int getScore() {
        return score;
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
     * @return direction
     */
    public EnumDirection getDirection() {
        return direction;
    }

    /**
     *
     * @param direction 方向
     */
    public void setDirection(EnumDirection direction) {
        this.direction = direction;
    }

    /**
     *
     */
    public void initialMap() {
        this.people = new People(1, 2);
        
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 24; j++) {
                map[i][j] = new Block();
            }
        }
        this.map[people.getX()][people.getY()].newBlock(this.people, Block.EnumStatus.people);
        for (int i = 0; i < 24; i++) {
            this.map[0][i].newBlock(new Wall(), Block.EnumStatus.wall);
            this.map[39][i].newBlock(new Wall(), Block.EnumStatus.wall);
        }
        for (int i = 1; i < 19; i++) {
            this.map[i][0].newBlock(new Wall(), Block.EnumStatus.wall);
            this.map[i][23].newBlock(new Wall(), Block.EnumStatus.wall);
            this.map[39 - i][0].newBlock(new Wall(), Block.EnumStatus.wall);
            this.map[39 - i][23].newBlock(new Wall(), Block.EnumStatus.wall);
        }
        for (int i = 0; i < 3; i++) {
            this.map[9][6 + i * 5].newBlock(new Oil(9, 6 + i * 5, 100, 10, this), Block.EnumStatus.oil);
            this.map[16][6 + i * 5].newBlock(new Oil(16, 6 + i * 5, 100, 10, this), Block.EnumStatus.oil);
            this.map[23][6 + i * 5].newBlock(new Oil(23, 6 + i * 5, 100, 10, this), Block.EnumStatus.oil);
            this.map[30][6 + i * 5].newBlock(new Oil(30, 6 + i * 5, 100, 10, this), Block.EnumStatus.oil);
        }

    }

    /**
     *
     * @return dataTransfer
     */
    public DataTransfer getDataTransfer() {
        return dataTransfer;
    }
}
