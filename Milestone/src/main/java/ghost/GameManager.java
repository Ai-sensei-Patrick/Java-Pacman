package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.util.*;

public class GameManager{

    private Waka waka;
    private Map map;
    private ArrayList<Ghost> ghosts;
    private ArrayList<Ghost> removedGhosts;
    private JsonParser jParse;
    private boolean bar; 
    private int frameRate;
    private String fileName;
    private boolean losegame;
    private boolean wingame;
    private PFont font;
    private int chaserIndex;
    private int ghostCollidedIndex;

    /**
     * Store waka object, map object, ghost object in an ArrayList, removed ghost object in an ArrayList, boolean for spacebar, 
     * framerate of game, filename, boolean if the game lose, ghost index when ghost colide with waka, chaser index for whim, 
     * boolean if the game win, and Font for the text. 
     */
    public GameManager(){
        this.waka = null;
        this.map = null;
        this.ghosts = new ArrayList<Ghost>();
        this.removedGhosts = new ArrayList<Ghost>();
        this.bar = true;
        this.jParse = new JsonParser();
        this.frameRate = 0;
        this.fileName = null;
        this.losegame = false;
        this.ghostCollidedIndex = 0;
        this.chaserIndex = 0;
        this.wingame = false;
        this.font = null;
    }

    /**
     * Set wingame true, if the user win the game.
     *
     * @param bol boolean for win the game.
     */
    public void setWin(boolean bol){
        this.wingame = bol;
    }

    /**
     * Set losegame true, if the user lose the game.
     *
     * @param bol boolean for lose the game.
     */
    public void setLose(boolean bol){
        this.losegame = bol;
    }

    /**
     * Return true if user win the game, otherwise false.  
     *
     * @return boolean true if user win the game.
     */
    public boolean getWin(){
        return this.wingame;
    }

    /**
     * Return true if user lose the game, otherwise false.  
     *
     * @return boolean true if user lose the game.
     */
    public boolean getLose(){
        return this.losegame;
    }

    /**
     * Return ghost arraylist (collection of different type of ghost).  
     *
     * @return collection of ghost with different type in an arraylist.
     */
    public ArrayList<Ghost> getRemovedGhost(){
        return this.removedGhosts;
    }

    /**
     * Set framerate .  
     *
     * @param frame frameRate of the game.
     */
    public void setFrameRate(int frame){
        this.frameRate = frame;
    }

    /**
     * Return index of chaser in ghost arraylist.
     *
     * @return chaser index.
     */
    public int getChaserIndex(){
        return this.chaserIndex;
    }

    /**
     * Set map instance variable.
     *
     * @param map Map Object.
     */
    public void setMap(Map map){
        this.map = map;
    }

    /**
     * Set waka instance variable.
     *
     * @param waka Waka Object.
     */
    public void setWaka(Waka waka){
        this.waka = waka;
    }

    /**
     * Set jParse instance variable.
     *
     * @param parse JsonParser Object.
     */
    public void setjParse(JsonParser parse){
        this.jParse = parse;
    }

    /**
     * Add ghost to arraylist of ghost.
     * 
     * @param ghost Ghost Object.
     */
    public void addGhost(Ghost ghost){
        if(ghost.getType().equals("Chaser")){
            this.chaserIndex = this.ghosts.size();
        }
        this.ghosts.add(ghost);
    }
    /**
     * Return arraylist of ghosts
     *
     * @return arraylist of ghosts
     */
    public ArrayList<Ghost> getGhosts(){
        return this.ghosts;
    }

    /**
     * Return waka Object.
     *
     * @return waka Object.
     */
    public Waka getWaka(){
        return this.waka;
    }

    /**
     * Read .CONFIG file and put maps filename.
     *
     * @param Jsonfile Name of .CONFIG file.
     */
    public void readFile(String Jsonfile){
        this.jParse.readJson(Jsonfile);
        this.fileName = Jsonfile;
    }

    /**
     * If waka lives is equal to zero return true, otherwise return false.
     *
     * @return true if waka lives is 0, otherwise false.
     */
    public boolean isGameEnd(){
        Long lives = this.waka.getLives();
        if(lives == 0){
            return true;
        }
        return false;
    }

    /**
     * Set up the whole game object such as Map Object, JsonParser Object, Ghost object and put them inside an arraylist,
     * Waka Object.This method load image for each object, set starting position for each object, 
     * pass through the information from JsonParser Object , save the font for text and set Chaser index.
     *
     * @param app PApplet Object.
     */
    public void setUp(App app){
        //Load images
        //Set up for map
        this.map = new Map(app.loadImage("src/main/resources/downLeft.png"),
        app.loadImage("src/main/resources/downRight.png"),
        app.loadImage("src/main/resources/fruit.png"),
        app.loadImage("src/main/resources/horizontal.png"),
        app.loadImage("src/main/resources/upLeft.png"),
        app.loadImage("src/main/resources/upRight.png"),
        app.loadImage("src/main/resources/vertical.png"),
        app.loadImage("src/main/resources/superfruit.png"),
        app.loadImage("src/main/resources/soda.png"));
        this.map.parseMapToChar(this.jParse);
        this.map.mapCharToObject();

        //Set up for waka
        this.waka = new Waka(app.loadImage("src/main/resources/playerLeft.png"),
        app.loadImage("src/main/resources/playerRight.png"),
        app.loadImage("src/main/resources/playerDown.png"),
        app.loadImage("src/main/resources/playerUp.png"),
        app.loadImage("src/main/resources/playerClosed.png"));
        this.waka.setWakaPosition(this.map);
        this.waka.setConfig(this.jParse);

        //Set up Font
        this.font = app.createFont("src/main/resources/PressStart2P-Regular.ttf",21);
        app.textFont(this.font);

        //Set up for Ambusher
        for(int count = 1; count <= this.map.getNumAmbusher(); count++){
            Ambusher ambusher = new Ambusher("Ambusher",app.loadImage("src/main/resources/ambusher.png"),this.waka,27,0,
            app.loadImage("src/main/resources/frightened.png"),app.loadImage("src/main/resources/sodaGhost.png"));

            ambusher.setGhostPosition(this.map,count,'a');
            ambusher.setConfig(this.jParse,0,1);
            this.ghosts.add(ambusher);
        }

        //Set up for Chaser
        for(int count = 1; count <= this.map.getNumChaser(); count++){
            Chaser chaser = new Chaser("Chaser",app.loadImage("src/main/resources/chaser.png"),this.waka,0,0,
            app.loadImage("src/main/resources/frightened.png"),app.loadImage("src/main/resources/sodaGhost.png"));

            chaser.setGhostPosition(this.map,count,'c');
            chaser.setConfig(this.jParse,2,3);
            this.chaserIndex = this.ghosts.size();
            this.ghosts.add(chaser);
        }

        //Set up for Ignorant
        for(int count = 1; count <= this.map.getNumIgnorant(); count++){
            Ignorant ignorant = new Ignorant("Ignorant",app.loadImage("src/main/resources/ignorant.png"),this.waka,0,35,
            app.loadImage("src/main/resources/frightened.png"),app.loadImage("src/main/resources/sodaGhost.png"));

            ignorant.setGhostPosition(this.map,count,'i');
            ignorant.setConfig(this.jParse,4,5);
            this.ghosts.add(ignorant);
        }

        //Set up for Whim
        for(int count = 1; count <= this.map.getNumWhim(); count++){
            Whim whim = new Whim("Whim",app.loadImage("src/main/resources/whim.png"),this.waka,27,35,
            app.loadImage("src/main/resources/frightened.png"),app.loadImage("src/main/resources/sodaGhost.png"));

            whim.setChaser(this.ghosts.get(this.chaserIndex));
            whim.setGhostPosition(this.map,count,'w');
            whim.setConfig(this.jParse,6,7);
            this.ghosts.add(whim);
        }
    }

    /**
     * This method check if there any ghost in ghost array collide with waka.
     *
     * @return True if there is a ghost collide with waka,otherwise false
     */
    public boolean wakaGhostCollide(){
        for(int i = 0; i < this.ghosts.size(); i++){
            Ghost g = this.ghosts.get(i);
            if(g.checkWakaCollision()){
                this.ghostCollidedIndex = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Draw All the ghost in correct position.
     *
     * @param app PApplet Object.
     */
    public void drawAllGhost(App app){
        for(int i = 0; i < this.ghosts.size(); i++){
            Ghost g = this.ghosts.get(i);
            g.draw(app);
        }
    }

    /**
     * Remove Ghost when Ghost and Waka collide in frighten mode.
     * Put the removed ghost in an arraylist to re-use them when the game reset.
     * If Ghost collided is a chaser indicate to whim to target waka at chase mode.
     * If the index of ghost collided is smaller than index of chaser, subtract the index of chaser.
     */
    public void removeGhost(){
        if(this.ghostCollidedIndex == this.chaserIndex){
            for(int i = 0; i < this.ghosts.size(); i++){
                Ghost g = this.ghosts.get(i);
                if(g.getType().equals("Whim")){
                    g.setNoChaser(true);
                }
            }
        }
        if(this.ghostCollidedIndex < this.chaserIndex){
            this.chaserIndex -= 1;
        }
        this.removedGhosts.add(this.ghosts.get(this.ghostCollidedIndex));
        this.ghosts.remove(this.ghostCollidedIndex);
    }

    /**
     * Call all tick method from each ghost in arraylist.
     */
    public void tickAllGhost(){
        for(int i = 0; i < this.ghosts.size(); i++){
            Ghost g = this.ghosts.get(i);
            g.tick(this.map);
        }
    }

    /**
     * Set boolean line in ghost as true if the user pressed the space bar
     *
     * @param bol boolean to indicate if the user pressed the space bar
     */
    public void setBooleanLine(boolean bol){
        for(int i = 0; i < this.ghosts.size(); i++){
            Ghost g = this.ghosts.get(i);
            g.setDrawLine(bol);
        }
    }

    /**
     * This method extend the keyPressed method from app and when user press left key, it will try to change waka direction to left.
     * If user press right key, it will try to change waka direction to right.
     * If user press down key, it will try to change waka direction to down.
     * If user press up key, it will try to change wka direction to up.
     * If the user press space bar, it will activate the debug line.
     * If the user press space bar for the second time, it will deactive debug line.
     *
     * @param app PApplet Object.
     */
    public void keyPressed(App app){
        if(app.key == PApplet.CODED){
            if(app.keyCode == PApplet.UP){
                this.waka.moveUp(this.map);
            }
            if(app.keyCode == PApplet.DOWN){
                this.waka.moveDown(this.map);
            }
            if(app.keyCode == PApplet.RIGHT){
                this.waka.moveRight(this.map);
            }
            if(app.keyCode == PApplet.LEFT){
                this.waka.moveLeft(this.map);
            }
        } 
        if(app.keyCode == ' '){
            if(this.bar){
                this.setBooleanLine(true);
                app.stroke(255,255,255);
                this.bar = false;
            }else{
                this.setBooleanLine(false);
                this.bar = true;
            }
        } 
    }

    /**
     * Set all ghost mode for ghost in the arraylist.
     *
     * @param mode ghost mode
     */
    public void setGhostMode(String mode){
        for(int i = 0; i < this.ghosts.size(); i++){
            Ghost g = this.ghosts.get(i);
            g.setMode(mode);
        }
    }

    /**
     * Check Ghost mode, If ghost in frighten mode return true, otherwise false.
     *
     * @return true if the ghost in frighten mode, otherwise false.
     */
    public boolean checkGhostFrightenMode(){
        for(int i = 0; i < this.ghosts.size(); i++){
            String mode = this.ghosts.get(i).getMode();
            if(mode.equals("Frighten")){
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if waka fruit eaten and map total fruit is the same, otherwise false.
     *
     * @return true if both waka fruit eaten and total fruit in map bot are the same, otherwise false.
     */
    public boolean isGameWin(){
        if(this.waka.getfruitEaten() == this.map.getNumFruit()){
            return true;
        }
        return false;
    }

    /**
     * Reset back the whole game by calling setUp method again and also re-read the config file, if user win or lose their game.
     * Clear all removed ghost arraylist and ghosts arraylist.
     * If waka still got live reset waka and ghosts back to their position without resetting the fruit eaten by waka and the map.
     */
    public void resetGame(){
        if(this.losegame || this.wingame){
            if(this.frameRate == 599){
                this.losegame = false;
                this.wingame = false;
                this.removedGhosts.clear();
                this.ghosts.clear();
                this.readFile(this.fileName);
            }
            return;
        }
        this.ghosts.addAll(this.removedGhosts);
        this.removedGhosts.clear();
        for(int i = 0; i < this.ghosts.size(); i++){
            Ghost g = this.ghosts.get(i);
            if(g.getType().equals("Whim")){
                g.setNoChaser(false);
            }
            g.reset();
        }
        this.waka.reset();
    }

    /**
     * Draw the whole game, otherwise if user lose or win the game display the text.
     * Check for waka and ghost collision and set ghost mode, when waka eaten something.
     * 
     * @param app PApplet Object
     */
    public void draw(App app){
        if(this.losegame || this.wingame){
            app.textAlign(app.CENTER);
            if(this.losegame){
                app.text("Game Over",224,288);
            } 
            if(this.wingame){
                app.text("You Win",224,288);
            }
            this.resetGame();
            this.setUp(app);
            this.frameRate += 1;
            return;
        }
        this.map.draw(app);
        this.tickAllGhost();

        if(this.wakaGhostCollide()){
            if(this.checkGhostFrightenMode()){
                this.removeGhost();
            }
            else{
                if(this.isGameEnd()){
                    this.frameRate = 0;
                    this.losegame = true;
                    return;
                }else{
                    this.resetGame();
                }
            }
        }
        if(this.isGameWin()){
            this.frameRate = 0;
            this.wingame = true;
        }

        if(this.waka.getIsSuper()){
            this.setGhostMode("Frighten");
            this.waka.setIsSuper(false);
        }

        if(this.waka.getIsSoda()){
            this.setGhostMode("Soda");
            this.waka.setIsSoda(false);
        }

        this.waka.tick(this.map);
        this.drawAllGhost(app);
        this.waka.draw(app);

        this.frameRate += 1;
    }
}