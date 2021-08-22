package ghost;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.*;

public class Waka implements Character{
    private long speed;
    private PImage currentImage;
    private PImage leftImage;
    private PImage rightImage;
    private PImage upImage;
    private PImage downImage;
    private PImage closeImage;
    private long lives;
    private int yVariable;
    private int xVariable;
    private int xPixels;
    private int yPixels;
    private String direction;
    private String fDirection;
    private boolean isSuper;
    private boolean isSoda;
    private int frameRate;
    private int fruitEaten;
    private int startX;
    private int startY;

    /**
     * Store waka intial position, waka x/y pixels, all image of waka in different direction, waka current image,
     * waka lives, frameRate, fruit eaten by waka, boolean when superfruit being eaten, waka speed, waka x/y coordinate,
     * direction of waka ,future direction of waka and boolean when soda being drank.
     *
     * @param leftImage Image of waka facing left.
     * @param rightImage Image of waka facing right.
     * @param upImage Image of waka facing up.
     * @param downImage Image of waka facing down.
     * @param closeImage Image of waka close mouth.
     */
    public Waka(PImage leftImage,PImage rightImage,PImage downImage,PImage upImage,PImage closeImage){
        this.leftImage = leftImage;
        this.rightImage = rightImage;
        this.upImage = upImage;
        this.downImage = downImage;
        this.closeImage = closeImage;
        this.currentImage = this.closeImage;
        this.speed = 0;
        this.lives = 0;
        this.yPixels = 0;
        this.xPixels = 0;
        this.xVariable = 0;
        this.yVariable = 0;
        this.direction = "Left";
        this.fDirection = null;
        this.frameRate = 0;
        this.isSoda = false;
        this.isSuper = false;
        this.fruitEaten = 0;
        this.startX = 0;
        this.startY = 0;
    }

    /**
     * Return true if waka drank the soda, otherwise false.
     *
     * @return true for of waka drank soda, otherwise false.
     */
    public boolean getIsSoda(){
        return this.isSoda;
    }

    /**
     * Set number of fruit eaten by waka
     *
     * @param fruits number of fruits.
     */
    public void setFruitEaten(int fruits){
        this.fruitEaten = fruits;
    }

    /**
     * Set waka lives.
     *
     * @param lives number of waka lives.
     */
    public void setLives(int lives){
        this.lives = lives;
    }

    /**
     * Set waka has drank soda.
     *
     * @param bol boolean to indicate waka has drank the soda.
     */
    public void setIsSoda(boolean bol){
        this.isSoda = bol;
    }

    /**
     * Return waka framerate
     *
     * @return waka framerate
     */
    public int getFrameRate(){
        return this.frameRate;
    }

    /**
     * Set waka direction.
     *
     * @param direction set waka direction.
     */
    public void setDirection(String direction){
        this.direction = direction;
    }

    /**
     * Return waka future direction.
     * 
     * @return waka future direction.
     */
    public String getfDirection(){
        return this.fDirection;
    }

    /**
     * Set waka yCoordinate.
     * 
     * @param yVariable yCoordinate of waka.
     */
    public void setY(int yVariable){
        this.yVariable = yVariable;
    }

    /**
     * Set waka yCoordinate.
     * 
     * @param xVariable yCoordinate of waka.
     */
    public void setX(int xVariable){
        this.xVariable = xVariable;
    }

    /**
     * Return direction of waka.
     *
     * @return Waka direction.
     */
    public String getDirection(){
        return this.direction;
    }

    /**
     * Return number of waka Xpixels.
     *
     * @return Number of waka Xpixels.
     */
    public int getPixelsX(){
        return this.xPixels;
    }

    /**
     * Return number of waka Ypixels.
     *
     * @return Number of waka Ypixels.
     */
    public int getPixelsY(){
        return this.yPixels;
    }

    /**
     * Return Xtile/Xcoordinate of waka based on the grid.
     *
     * @return Xtile/Xcoordinate of waka.
     */
    public int getX(){
        return this.xVariable;
    }

    /**
     * Return Ytile/Ycoordinate of waka based on the grid.
     *
     * @return Ytile/Ycoordinate of waka.
     */
    public int getY(){
        return this.yVariable;
    }

    /**
     * Return boolean, whether waka has eaten superfruit.
     *
     * @return Boolean indicating superfruit are eaten.
     */
    public boolean getIsSuper(){
        return this.isSuper;
    }

    /**
     * Return number of fruit eaten by waka.
     *
     * @return Number of fruit eaten.
     */
    public int getfruitEaten(){
        return this.fruitEaten;
    }

    /**
     * Take JsonParser Object which created by reading the .JSON file.
     * From the JsonParser Object get speed of waka and waka lives.
     * 
     * @param parse Object that reads and contain all of the information from the config file
     */
    public void setConfig (JsonParser parse){
        this.speed = parse.getSpeed();
        this.lives = parse.getLives();
    }
    
    /**
     * Set isSuper to true when the waka eaten a superfruit.
     *
     * @param isSuper Boolean to indicate waka eaten a superfruit.
     */
    public void setIsSuper(boolean isSuper){
        this.isSuper = isSuper;
    }

    /**
     * Return waka lives.
     *
     * @return Number of waka lives.
     */
    public long getLives(){
        return this.lives;
    }

    /**
     * Return true if xPixels and yPixels is equal to zero.
     * <p>
     * Return false if xPixels and yPixels is not equal to zero.
     *
     * @return true if equal otherwise false.
     */
    public boolean pixelCheck(){
        if(this.xPixels == 0 && this.yPixels == 0){
            return true;
        }
        return false;
    }

    /**
     * Set waka initial position by finding 'p' symbol in the char[][].
     * <p>
     * This starting position stored in instance variable labelled startX and startY.
     * This allow us to access them, when waka collide with the ghost.
     *
     * @param map Object where walls and fruit being placed, stored and contain initial coordinates of movable object.
     */
    public void setWakaPosition(Map map){
        char[][] grid = map.getMapLayout();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if (grid[i][j] == 'p'){
                    this.xVariable = j;
                    this.yVariable = i;
                    this.startX = j;
                    this.startY = i;
                }
            }
        }
    }

    /**
     * This method set different graphic based on waka direction and waka frame rate.
     * <p>
     * To make the open-close graphic for waka, this method alternate between the open waka and close waka every 8 second.
     */
    public void setGraphic(){
        if (this.direction.equals("Left")){
            if(this.frameRate % 16 == 0){
                this.currentImage = this.closeImage;
            }
            else if(this.frameRate % 8 == 0){
                this.currentImage = this.leftImage;
            }
        }else if(this.direction.equals("Right")){
            if(this.frameRate % 16 == 0){
                this.currentImage = this.closeImage;
            }
            else if(this.frameRate % 8 == 0){
                this.currentImage = this.rightImage;
            }
        }else if(this.direction.equals("Down")){
            if(this.frameRate % 16 == 0){
                this.currentImage = this.closeImage;
            }
            else if(this.frameRate % 8 == 0){
                this.currentImage = this.downImage;
            }
        }else{
            if(this.frameRate % 16 == 0){
                this.currentImage = this.closeImage;
            }
            else if(this.frameRate % 8 == 0){
                this.currentImage = this.upImage;
            }
        }
    }

    /**
     * Reset waka, when waka collide with a normal mode ghost.
     * <p>
     * Reset waka : Xcoordinate/tile to 0, Ycoordinate/tile to 0, waka direction to "Left", framerate to 0,
     * <p>
     * futureDirection to null, xPixel to 0, yPixel to 0, decrease waka live by 1.
     */
    public void reset(){
        this.xVariable = this.startX;
        this.yVariable = this.startY;
        this.xPixels = 0;
        this.yPixels = 0;
        this.direction = "Left";
        this.frameRate = 0;
        this.fDirection = null;
        this.lives -= 1;
    }


    /**
     * This method check the next tile, whether if its a solid or not.
     * If its not a solid the waka is allowed to add/subtract its xPixels/yPixels (based on waka direction).
     * <p>
     * If xPixels/yPixels have reach 16 ,it reset back the pixels to 0 and add/subtract Xcoordinate/Ycoordinate of waka by speed of waka (based on waka direction).
     * <p>
     * If waka has reach 8 or -8 pixels check whether the tile is a fruit or not, if it is a fruit add waka fruiteaten by 1 and change tile to empty object.
     * If the fruit eaten by waka is a superfruit , change isSuper variable to true.
     * If soda drank by waka , change isSoda variable to true.
     * Check if waka have future movement and set waka graphic.
     * 
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     */
    public void tick(Map map){

        Entities[][] grid = map.getMapLayoutObj();
        this.setGraphic();

        if(this.direction.equals("Left")){
            if (!grid[this.yVariable][this.xVariable - 1].isSolid()){
                this.xPixels -= this.speed;
                if (this.xPixels / 8 == -1){
                    if(grid[this.yVariable][this.xVariable - 1].isSuper()){
                        this.isSuper = true;
                    }
                    if(grid[this.yVariable][this.xVariable - 1].isSoda()){
                        this.isSoda = true;
                    }
                    if(grid[this.yVariable][this.xVariable - 1].isFruit()){
                        grid[this.yVariable][this.xVariable - 1] = new Empty(null,this.xVariable,this.yVariable,false);
                        this.fruitEaten += 1;
                    }
                }
                if (this.xPixels % 16 == 0 && this.xPixels != 0){
                    this.xPixels = 0;
                    this.xVariable -= 1;
                }
            }
        }

        else if (this.direction.equals("Right")){
            if (!grid[this.yVariable][this.xVariable + 1].isSolid()){
                this.xPixels += this.speed;
                if (this.xPixels / 8 == 1){
                    if(grid[this.yVariable][this.xVariable + 1].isSuper()){
                        this.isSuper = true;
                    }
                    if(grid[this.yVariable][this.xVariable + 1].isSoda()){
                        this.isSoda = true;
                    }
                    if(grid[this.yVariable][this.xVariable + 1].isFruit()){
                        grid[this.yVariable][this.xVariable + 1] = new Empty(null,this.xVariable,this.yVariable,false);
                        this.fruitEaten += 1;
                    }
                }
                if (this.xPixels % 16 == 0 && this.xPixels != 0){
                    this.xPixels = 0;
                    this.xVariable += 1;
                }
            }
        }

        else if (this.direction.equals("Up")){
            if (!grid[this.yVariable - 1][this.xVariable].isSolid()){
                this.yPixels -= this.speed;
                if (this.yPixels / 8 == -1){
                    if(grid[this.yVariable - 1][this.xVariable].isSuper()){
                        this.isSuper = true;
                    }
                    if(grid[this.yVariable - 1][this.xVariable].isSoda()){
                        this.isSoda = true;
                    }
                    if(grid[this.yVariable - 1][this.xVariable].isFruit()){
                        grid[this.yVariable - 1][this.xVariable] = new Empty(null,this.xVariable,this.yVariable,false);
                        this.fruitEaten += 1;
                    }
                }
                if (this.yPixels % 16 == 0 && this.yPixels != 0){
                    this.yPixels = 0;
                    this.yVariable -= 1;
                }
            }
        }

        else {
            if (!grid[this.yVariable + 1][this.xVariable].isSolid()){
                this.yPixels += this.speed;
                if (this.yPixels / 8 == 1){
                    if(grid[this.yVariable + 1][this.xVariable].isSuper()){
                        this.isSuper = true;
                    }
                    if(grid[this.yVariable + 1][this.xVariable].isSoda()){
                        this.isSoda = true;
                    }
                    if(grid[this.yVariable + 1][this.xVariable].isFruit()){
                        grid[this.yVariable + 1][this.xVariable] = new Empty(null,this.xVariable,this.yVariable,false);
                        this.fruitEaten += 1;
                    }
                }
                if (this.yPixels % 16 == 0 && this.yPixels != 0){
                    this.yPixels = 0;
                    this.yVariable += 1;
                }
            }
        }
        this.checkNextMove(map);
        this.frameRate += 1;
    }

    /**
     * The method check if left tile from waka is solid or not, if left tile is not a solid waka can turn, but this happen only if the both pixels has turn to zero.
     * If the condition required is not fulfilled the move inputed is stored in future direction.
     *
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     */
    public void moveLeft(Map map){
        Entities[][] mapObj = map.getMapLayoutObj();
        if (!mapObj[this.yVariable][this.xVariable - 1].isSolid() && this.pixelCheck()){
            this.direction = "Left";
            this.frameRate = 8;
            this.fDirection = null;
        }else{
            this.fDirection = "Left";
        }
    }

    /**
     * The method check if right tile from waka is solid or not, if right tile is not a solid waka can turn, but this happen only if the both pixels has turn to zero.
     * If the condition required is not fulfilled the move inputed is stored in future direction.
     *
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     */
    public void moveRight(Map map){
        Entities[][] mapObj = map.getMapLayoutObj();
        if (!mapObj[this.yVariable][this.xVariable + 1].isSolid() && this.pixelCheck()){
            this.direction = "Right";
            this.frameRate = 8;
            this.fDirection = null;
        }else{
            this.fDirection = "Right";
        }
    }

    /**
     * The method check if Up tile from waka is solid or not, if Up tile is not a solid waka can turn, but this happen only if the both pixels has turn to zero.
     * If the condition required is not fulfilled the move inputed is stored in future direction.
     *
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     */
    public void moveUp(Map map){
        Entities[][] mapObj = map.getMapLayoutObj();
        if (!mapObj[this.yVariable - 1][this.xVariable].isSolid() && this.pixelCheck()){
            this.direction = "Up";
            this.frameRate = 8;
            this.fDirection = null;
        }else{
            this.fDirection = "Up";
        }
    }

    /**
     * The method check if Down tile from waka is solid or not, if Down tile is not a solid waka can turn, but this happen only if the both pixels has turn to zero.
     * If the condition required is not fulfilled the move inputed is stored in future direction.
     *
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     */
    public void moveDown(Map map){
        Entities[][] mapObj = map.getMapLayoutObj();
        if (!mapObj[this.yVariable + 1][this.xVariable].isSolid() && this.pixelCheck()){
            this.direction = "Down";
            this.frameRate = 8;
            this.fDirection = null;
        }else{
            this.fDirection = "Down";
        }
    }
    
    /**
     * This method check whether future direction can be applied to current direction.
     * Waka only allowed to change direction if the next tile (based on direction) is not a solid and pixels are zero.
     * <p>
     * Reset future direction back to null, if future direction successfully applied.
     * 
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     */
    public void checkNextMove(Map map){
        Entities[][] grid = map.getMapLayoutObj();

        if(!(this.fDirection == null)){
            if (this.fDirection.equals("Left")){
                if (!(grid[this.yVariable][this.xVariable - 1].isSolid()) && this.pixelCheck()){
                    this.direction = "Left";
                    this.fDirection = null;
                }
            }
            
            else if (this.fDirection.equals("Right")){
                if (!(grid[this.yVariable][this.xVariable + 1].isSolid()) && this.pixelCheck()){
                    this.direction = "Right";
                    this.fDirection = null;
                }
            }

            else if (this.fDirection.equals("Up")){
                if (!(grid[this.yVariable - 1][this.xVariable].isSolid()) && this.pixelCheck()){
                    this.direction = "Up";
                    this.fDirection = null;
                }
            }

            else{
                if (!grid[this.yVariable + 1][this.xVariable].isSolid() && this.pixelCheck()){
                    this.direction = "Down";
                    this.fDirection = null;    
                }
            }
        }
    }

    /**
     * Draw waka in correct position, for X axis : Xcoordinate * 16 add by their Xpixels subtract offset, for Y axis : Ycoordinate * 16 add by Ypixels subtract offset.
     * <p>
     * Draw waka  lives at the bottom of the map.
     * @param app PApplet object.
     */
    public void draw(PApplet app){
        app.image(this.currentImage,(this.xVariable * 16 + this.xPixels) - 4,(this.yVariable * 16 + this.yPixels) - 4);
        for (int i = 0; i < this.lives; i++){
            app.image(this.rightImage,((i * 2) * 16) - (i * 8) + 8,34 * 16);
        }
    }
}