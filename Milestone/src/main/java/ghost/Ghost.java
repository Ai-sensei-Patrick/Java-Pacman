package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.*;
import org.json.simple.JSONArray;
import java.lang.*;

public abstract class Ghost implements Character{
    /**
     * Type of the ghost.
     */
    protected String type;
    /**
     * Ghost image in normal mode.
     */
    protected PImage image;
    /**
     * Ghost image in soda mode.
     */
    protected PImage sImage;
    /**
     * Ghost image in frighten mode.
     */
    protected PImage fImage;
    /**
     * Ghost current image.
     */
    protected PImage currentImage;
    /**
     * Ghost Xcoordinate in grid.
     */
    protected int xVariable;
    /**
     * Ghost Ycoordinate in grid.
     */
    protected int yVariable;
    /**
     * Ghost Xpixels.
     */
    protected int xPixels;
    /**
     * Ghost Ypixels.
     */
    protected int yPixels;
    /**
     * Ghost speed.
     */
    protected long speed;
    /**
     * Framerate for ghost.
     */
    protected int frameRate;
    /**
     * Ghost current mode.
     */
    protected String mode;
    /**
     * Duration of ghost for chaser mode.
     */
    protected long durationChase;
    /**
     * Duration of ghost in scatter mode.
     */
    protected long durationScatter;
    /**
     * Duration of ghost in frighten mode.
     */
    protected long durationFrighten;
    /**
     * Duration of ghost in soda mode.
     */
    protected long durationSoda;
    /**
     * Ghost target Xcoordinate.
     */
    protected int targetX;
    /**
     * Ghost target Ycoordinate.
     */
    protected int targetY;
    /**
     * Waka Object.
     */
    protected Waka waka;
    /**
     * Ghost current direction.
     */
    protected String direction;
    /**
     * Boolean to indicate draw DEBUG line.
     */
    protected boolean drawLine;
    /**
     * Boolean to indicate collision between ghost and waka.
     */
    protected boolean collide;
    /**
     * Ghost previous mode.
     */
    protected String pMode;
    /**
     * Ghost starting Xcoordinate.
     */
    protected int startX;
    /**
     * Ghost starting Ycoordinate.
     */
    protected int startY;
    /**
     * Boolean for chaser existence.
     */
    protected boolean noChaser;


    /**
     * Store ghost intial position, ghost x/y pixels, all image of ghost in different mode, ghost current image,ghost x/y coordinate 
     * frameRate, ghost previous mode, duration scatter mode, duration chaser mode, ghost speed, duration frighten mode,
     * direction of ghost, ghost target x/y and indicate to draw line.
     *
     * @param type Type of ghost.
     * @param image Image of ghost in normal mode.
     * @param waka Waka object.
     * @param fImage Image of ghost on frighten mode.
     * @param sImage Image of ghost on soda mode.
     * @param targetX Initial target of ghost (Corner of map (Xcoordinate)).
     * @param targetY Initial target of ghost (Corner of map (Ycoordinate)).
     */
    public Ghost(String type,PImage image, Waka waka,int targetX,int targetY,PImage fImage,PImage sImage){
        this.type = type;
        this.waka = waka;
        this.image = image;
        this.currentImage = image;
        this.fImage = fImage;
        this.sImage = sImage;
        this.mode = "Scatter";
        this.pMode = "Scatter";
        this.xVariable = 0;
        this.yVariable = 0;
        this.yPixels = 0;
        this.xPixels = 0;
        this.speed = 0;
        this.durationSoda = 0;
        this.durationScatter = 0;
        this.durationChase = 0;
        this.durationFrighten = 0;
        this.frameRate = 0;
        this.targetX = targetX;
        this.targetY = targetY;
        this.direction = "Left";
        this.drawLine = false;
        this.startX = 0;
        this.startY = 0;
        this.noChaser = false;
    }

    /**
     * Return ghost TargetX.
     * 
     * @return ghost TargetX.
     */
    public int getTargetX(){
        return this.targetX;
    }

    /**
     * Return ghost TargetY.
     * 
     * @return ghost TargetY.
     */
    public int getTargetY(){
        return this.targetY;
    }

    /**
     * Return ghost frameRate.
     * 
     * @return ghost frameRate.
     */
    public int getFrameRate(){
        return this.frameRate;
    }

    /**
     * Set ghost frameRate.
     * 
     * @param frameRate ghost frameRate.
     */
    public void setFrameRate(int frameRate){
        this.frameRate = frameRate;
    }

    /**
     * Set ghost xTarget.
     * 
     * @param targetX ghost xTarget.
     */
    public void setTargetX(int targetX){
        this.targetX = targetX;
    }

    /**
     * Set ghost yTarget.
     * 
     * @param targetY ghost yTarget.
     */
    public void setTargetY(int targetY){
        this.targetY = targetY;
    }

    /**
     * Return direction of the ghost.
     * 
     * @return direction of the ghost.
     */
    public String getDirection(){
        return this.direction;
    }

    /**
     * Return boolean that indicate chaser existence.
     * 
     * @return boolean that indicate chaser existence.
     */
    public boolean getNoChaser(){
        return this.noChaser;
    }

    /**
     * Set ghost direction.
     * 
     * @param direction ghost direction.
     */
    public void setDirection(String direction){
        this.direction = direction;
    }

    /**
     * Return ghost boolean that indicate draw line.
     * 
     * @return boolean that indicate draw line.
     */
    public boolean getDrawLine(){
        return this.drawLine;
    }

    /**
     * Set ghost xVariable.
     * 
     * @param xVariable ghost xVariable.
     */
    public void setX (int xVariable){
        this.xVariable = xVariable;
    }

    /**
     * Set ghost yVariable.
     * 
     * @param yVariable ghost yVariable.
     */
    public void setY (int yVariable){
        this.yVariable = yVariable;
    }

    /**
     * Set mode based on its given parameter.
     * 
     * @param mode Mode of the ghost.
     */
    public void setMode(String mode){
        this.mode = mode;
        this.frameRate = 0;
    }

    /**
     * Return type of the ghost.
     * 
     * @return Type of the ghost.
     */
    public String getType(){
        return this.type;
    }

    /**
     * Set boolean, has chaser being removed or not.
     *
     * @param bol Boolean for chaser existence.
     */
    public void setNoChaser(boolean bol){
        this.noChaser = bol;
    }

    /**
     * Set boolean to draw line.
     *
     * @param drawLine Boolean to draw line.
     */
    public void setDrawLine(boolean drawLine){
        this.drawLine = drawLine;
    }

    /**
     * Return current mode of the ghost.
     *
     * @return current mode of ghost.
     */
    public String getMode(){
        return this.mode;
    }

    /**
     * Return Xcoordinate of the ghost.
     *
     * @return Xcoordinate of ghost.
     */
    public int getX(){
        return this.xVariable;
    }

    /**
     * Return Ycoordinate of the ghost.
     *
     * @return Ycoordinate of ghost.
     */
    public int getY(){
        return this.yVariable;
    }

    /**
     * Return Xpixels of the ghost.
     *
     * @return Xpixels of ghost.
     */
    public int getPixelsX(){
        return this.xPixels;
    }

    /**
     * Return Ypixels of the ghost.
     *
     * @return Ypixels of ghost.
     */
    public int getPixelsY(){
        return this.yPixels;
    }

    /**
     * Return true if both yPixels and xPixels is 0, else return false.
     *
     * @return Boolean outcome frome pixel checking.
     */
    public boolean pixelCheck(){
        if(this.xPixels == 0 && this.yPixels == 0){
            return true;
        }
        return false;
    }

    /**
     * Find the ghost position based on symbol given and check if the num and count must be the same to assign the ghost position.
     * <p>
     * num and count are used to allowed same ghost type to have different starting coordinates.
     * 
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     * @param num Number to indicate ghost number of that type.
     * @param symbol Symbol representation of ghost in the map.
     */
    public void setGhostPosition(Map map, int num , char symbol){
        int count = 1;
        boolean flag = false;
        char[][] grid = map.getMapLayout();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if (grid[i][j] == symbol){
                    if(num == count){
                        this.xVariable = j;
                        this.yVariable = i;
                        this.startX = j;
                        this.startY = i;
                        flag = true;
                        break;
                    }else{
                        count += 1;
                    }
                }
            }
            if(flag){
                break;
            }
        }
    }

    /**
     * This method reset ghost when they collide with waka
     * <p>
     * Reset ghost by pixels of ghost to 0, Ycoodrinate/tile to starting Y, Xcoordinate/tile to starting X, 
     * direction of ghost to Left, mode of ghost tp scatter, framerate of ghost to 0 and both pixels to 0.
     */

    public void reset(){
        this.xVariable = this.startX;
        this.yVariable = this.startY;
        this.xPixels = 0;
        this.yPixels = 0;
        this.direction = "Left";
        this.frameRate = 0;
        this.mode = "Scatter";
    }

    /**
     * Set ghost graphic/image based on their mode.
     */
    public void setGraphic(){
        if(this.mode.equals("Frighten")){
            this.currentImage = this.fImage;
        }
        else if(this.mode.equals("Soda")){
            this.currentImage = this.sImage;
        }
        else{
            this.currentImage = this.image;
        }
    }

    /**
     * Check collision between waka and ghost by this formula. Return true when the formula form both ghost and waka equal
     * <p>
     * Formula for waka X/Y = floor division((waka pixels + waka coordinate * 16) / 16)
     * Formula for ghost X/Y = floor division((ghost pixels + ghost coordinate * 16) / 16)
     *
     * @return True if waka and ghost has the same formula X/Y.
     */
    public boolean checkWakaCollision(){
        int wakaXpixels = this.waka.getPixelsX();
        int wakaYpixels = this.waka.getPixelsY();
        int wakaX = this.waka.getX();
        int wakaY = this.waka.getY();
        int ghostXpixels = this.xPixels;
        int ghostYpixels = this.yPixels;
        int ghostX = this.xVariable;
        int ghostY = this.yVariable;
        double wakaFormulaX = Math.floor((wakaXpixels + wakaX * 16)/ 16);
        double wakaFormulaY = Math.floor((wakaYpixels + wakaY * 16)/ 16);
        double ghostFormulaX = Math.floor((ghostXpixels + ghostX * 16)/ 16);
        double ghostFormulaY = Math.floor((ghostYpixels + ghostY * 16)/ 16);
        
        if(ghostFormulaX == wakaFormulaX && ghostFormulaY == wakaFormulaY){
            return true;
        }
        
        return false;
    }

    /**
     * Return 27 if Xcoordinate of ghost target more than 27, return 0 if Xcoordinate of ghost target less than 0 and return number if its between 0 and 27.
     *
     * @param num Waka target Xcoordinate.
     * @return Number in range of 0-27. 
     */
    public int checkBoundX(int num){
        if(num > 27){
            return 27;
        }
        else if(num < 0){
            return 0;
        }
        else{
            return num;
        }
    }

    /**
     * Return 35 if Ycoordinate of ghost target more than 35, return 0 if Ycoordinate of ghost target less than 0 and return number if its between 0 and 35.
     *
     * @param num Waka target Ycoordinate.
     * @return Number in range of 0-35. 
     */
    public int checkBoundY(int num){
        if(num > 35){
            return 35;
        }
        else if(num < 0){
            return 0;
        }
        else{
            return num;
        }
    }

    /**
     * Set ghost speed, ghost duration (scatter,frighten,chase) taken from JsonParser object.
     * 
     * @param parse Object of JsonParser.
     * @param i Index of modeLength.
     * @param j Index of modeLength.
     */
    public void setConfig(JsonParser parse,int i,int j){
        this.durationFrighten = parse.getFrightened();
        this.speed = parse.getSpeed();
        this.durationScatter = (long) parse.getModeLengths().get(i);
        this.durationChase = (long) parse.getModeLengths().get(j); 
        this.durationSoda = (long) parse.getSoda();   
    }


    /**
     * Return true if the ghost is in the intersection, return false if ghost is not in the intersection.
     *
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     * @return True if in intersection otherwise false.
     */
    public boolean checkIntersection(Map map){
        Entities[][] grid = map.getMapLayoutObj();
        int count = 0;
        if (this.pixelCheck()){
            if (!grid[this.yVariable][this.xVariable - 1].isSolid()){
                count += 1;
            }
            if (!grid[this.yVariable][this.xVariable + 1].isSolid()){
                count += 1;
            }
            if (!grid[this.yVariable - 1][this.xVariable].isSolid()){
                count += 1;
            }
            if (!grid[this.yVariable + 1][this.xVariable].isSolid()){
                count += 1;
            }
            if (count >= 3){
                return true;
            }
        }
        return false;
    }

    /**
     * Find the difference between ghost target Xcoordinate and ghost Xcoordinate.
     *
     * @return The difference between them.
     */
    public int checkDiffX(){
        return this.targetX - this.xVariable;
    }

    /**
     * Find the difference between ghost target Ycoordinate and ghost Ycoordinate.
     *
     * @return The difference between them.
     */
    public int checkDiffY(){
        return this.targetY - this.yVariable;
    }

    /**
     * Set ghost target Xcoordinate and Ycoordinate based on each ghost behaviour.
     */
    public abstract void checkTarget();
    
    /**
     * Check ghost next move when ghost reach an intersection, by predicting the ghost future move and 
     * Calculate their future position with target position using the euclidean distance formula. 
     * Put the distance into a map with the direction as the key, then take the smallest value from the map,
     * set ghost direction as the key of the smallest value. This allowed only if the next tile is not a solid and the direction
     *
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     */
    public void checkNextMove(Map map){
        Entities[][] grid = map.getMapLayoutObj();
        int diffX = this.checkDiffX();
        int diffY = this.checkDiffY();
        HashMap<String, Double> dic = new HashMap<String, Double>();

        if(this.pixelCheck()){
            if (!grid[this.yVariable][this.xVariable - 1].isSolid() && !this.direction.equals("Right")){
                double formula = Math.sqrt(Math.pow(diffX + 1, 2) + Math.pow(diffY, 2));
                dic.put("Left",formula);
            }
            if (!grid[this.yVariable][this.xVariable + 1].isSolid() && !this.direction.equals("Left")){
                double formula = Math.sqrt(Math.pow(diffX - 1, 2) + Math.pow(diffY, 2));
                dic.put("Right",formula);
            }
            if (!grid[this.yVariable + 1][this.xVariable].isSolid() && !this.direction.equals("Up")){
                double formula = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY - 1, 2));
                dic.put("Down",formula);
            }
            if (!grid[this.yVariable - 1][this.xVariable].isSolid() && !this.direction.equals("Down")){
                double formula = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY + 1, 2));
                dic.put("Up",formula);
            }
            if (this.mode.equals("Frighten") || this.mode.equals("Soda")){
                ArrayList<String> keys = new ArrayList<String>(dic.keySet());
                Random random = new Random();
                this.direction = keys.get(random.nextInt(keys.size()));
                return;
            }

            String direct = null;
            double smallest = 0;
            for (String key : dic.keySet()){
                if(smallest == 0){
                    smallest = dic.get(key);
                    direct = key;
                }
                if(dic.get(key) < smallest){
                    smallest = dic.get(key);
                    direct = key;
                }
            }
            this.direction = direct;
        }
    }

    /**
     * This method change the direction of ghost if its detect turn/corner.
     * This method also check the ghost future tile. 
     *
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     */
    public void turn(Map map){
        Entities[][] grid = map.getMapLayoutObj();
        if (!this.direction.equals("Left") && !grid[this.yVariable][this.xVariable + 1].isSolid()){
            if(this.pixelCheck()){
                this.direction = "Right";
            }
        }
        else if (!this.direction.equals("Right") && !grid[this.yVariable][this.xVariable - 1].isSolid()){
            if(this.pixelCheck()){
                this.direction = "Left";
            }
        }
        else if (!this.direction.equals("Down") && !grid[this.yVariable - 1][this.xVariable].isSolid()){
            if(this.pixelCheck()){
                this.direction = "Up";
            }
        }
        else if (!this.direction.equals("Up") && !grid[this.yVariable + 1][this.xVariable].isSolid()){
            if(this.pixelCheck()){
                this.direction = "Down";
            }
        }
        else{
            if(this.direction.equals("Left")){
                this.direction = "Right";
            }else if(this.direction.equals("Right")){
                this.direction = "Left";
            }else if(this.direction.equals("Up")){
                this.direction = "Down";
            }else if(this.direction.equals("Down")){
                this.direction = "Up";
            }
        }
    } 

    /**
     * Change the mode if the duration of each mode already reached, and store the current mode into previous mode.
     * This method check the next tile, whether if its a solid or not.
     * If its not a solid the ghost is allowed to add/subtract its xPixels/yPixels (based on ghost direction).
     * <p>
     * If xPixels/yPixels have reach 16 ,it reset back the pixels to 0 and add/subtract Xcoordinate/Ycoordinate of ghost by speed of ghost (based on ghost direction).
     * <p>
     * If ghost has reach an intersection ghost check for next move or else ghost try to turn.
     * <p>
     * If ghost not in frighten mode it will check for the ghost target position.
     * 
     * @param map Object where walls and fruit being placed, stored and use as background for the waka.
     */
    public void tick(Map map){
        Entities[][] grid = map.getMapLayoutObj();
        if (this.mode.equals("Scatter") && this.frameRate / (this.durationScatter * 60) == 1){
            this.frameRate = 0;
            this.pMode = "Chase";
            this.mode = "Chase";
        }
        else if (this.mode.equals("Chase") && this.frameRate / (this.durationChase * 60) == 1){
            this.frameRate = 0;
            this.pMode = "Scatter";
            this.mode = "Scatter";
        }
        else if (this.mode.equals("Frighten") && this.frameRate / (this.durationFrighten * 60) == 1){
            this.frameRate = 0;
            this.mode = this.pMode;
        }
        else if (this.mode.equals("Soda") && this.frameRate / (this.durationSoda * 60) == 1){
            this.frameRate = 0;
            this.mode = this.pMode;
        }
        this.setGraphic();
        if (this.checkIntersection(map)){
            this.checkNextMove(map);
        }else{
            this.turn(map);
        }
        if(!(this.mode.equals("Frighten") || this.mode.equals("Soda"))){
          this.checkTarget();  
        }


        if(this.direction.equals("Left")){
            this.xPixels -= this.speed;
            if (this.xPixels % 16 == 0 && this.xPixels != 0){
                this.xPixels = 0;
                this.xVariable -= this.speed;
            }
        }

        else if(this.direction.equals("Right")){
            this.xPixels += this.speed;
            if (this.xPixels % 16 == 0 && this.xPixels != 0){
                this.xPixels = 0;
                this.xVariable += this.speed;
            }
        }

        else if(this.direction.equals("Up")){
            this.yPixels -= this.speed;
            if (this.yPixels % 16 == 0 && this.yPixels != 0){
                this.yPixels = 0;
                this.yVariable -= this.speed;
            }
        }

        else{
            this.yPixels += this.speed;
            if (this.yPixels % 16 == 0 && this.yPixels != 0){
                this.yPixels = 0;
                this.yVariable += this.speed;
            }
        }

        this.frameRate += 1;
    }

    /**
     * Draw Ghost in correct position, for X axis : Xcoordinate * 16 add by their Xpixels add offset, for Y axis : Ycoordinate * 16 add by Ypixels add offset.
     * <p>
     * Draw line if ghost is not in frighten mode 
     * @param app PApplet object.
     */
    public void draw(PApplet app){
        app.image(this.currentImage,(this.xVariable * 16 + this.xPixels) - 6 ,(this.yVariable * 16 + this.yPixels) - 6);
        if(this.drawLine && !(this.mode.equals("Frighten") || this.mode.equals("Soda"))){
            app.line((this.xVariable * 16 + this.xPixels) + 6, (this.yVariable * 16 + this.yPixels) + 6,this.targetX * 16,this.targetY * 16);
        }
    }
}