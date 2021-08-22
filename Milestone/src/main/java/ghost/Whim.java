package ghost;
import processing.core.PApplet;
import processing.core.PImage;

public class Whim extends Ghost{

    private Ghost chaser;
    
    /**
     * Store Whim intial position, Whim x/y pixels, all image of Whim in different mode, Whim current image,Ambusher x/y coordinate 
     * frameRate, Whim previous mode, duration scatter mode, duration chaser mode, Whim speed, duration frighten mode,
     * direction of Whim, Whim target x/y ,indicate to draw line ,Chaser ghost.
     *
     * @param type Whim.
     * @param image Image of Whim in normal mode.
     * @param waka Waka object.
     * @param fImage Image of Whim on frighten mode.
     * @param targetX Initial target of Whim (Corner of map (Xcoordinate))
     * @param targetY Initial target of Whim (Corner of map (Ycoordinate))
     * @param sImage Image of whim on Soda mode.
     */
    public Whim(String type,PImage image, Waka waka, int targetX, int targetY, PImage fImage,PImage sImage){
        super(type,image,waka,targetX,targetY,fImage,sImage);
        this.chaser = null;
    }

    public Ghost getChaser(){
        return this.chaser;
    }

    /**
     * Set chaser for the whim. 
     * 
     * @param chaser Chaser Object.
     */
    public void setChaser(Ghost chaser){
        this.chaser = chaser;
    }

    /**
     * If Whim in chase mode, check for chaser existence, If chaser exist take 
     * chaser Xcoordinate and waka Xcoordinate then add/subtract waka Xcoordinate or waka Ycoordinate by 2 and this depend on the direction waka.
     * then use this formula to calculate TargetX/Y: waka coordinate * 2 - chaser coordinate.
     * If chaser doesn't exist use waka Xcoordinate as target X and waka Ycoordinate as target Y.
     * <p>
     * If Whim in scatter mode, set ghost targetX to 27 and set ghost targetY to 35.
     * 
     */
    public void checkTarget(){
        int chaserX = this.chaser.getX();
        int chaserY = this.chaser.getY();
        int wakaX = this.waka.getX();
        int wakaY = this.waka.getY();
        if(this.mode.equals("Chase")){
            if(!this.noChaser){
                if(this.waka.getDirection().equals("Left")){
                    this.targetX = this.checkBoundX(((wakaX - 2) * 2) - chaserX);
                    this.targetY = this.checkBoundY((wakaY * 2) - chaserY);
                }
                else if(this.waka.getDirection().equals("Right")){
                    this.targetX = this.checkBoundX(((wakaX + 2) * 2) - chaserX);
                    this.targetY = this.checkBoundY((wakaY * 2) - chaserY);
                }
                else if(this.waka.getDirection().equals("Up")){
                    this.targetX = this.checkBoundX((wakaX * 2) - chaserX);
                    this.targetY = this.checkBoundY(((wakaY - 2) * 2) - chaserY);
                }
                else{
                    this.targetX = this.checkBoundX((wakaX * 2) - chaserX);
                    this.targetY = this.checkBoundY(((wakaY + 2) * 2) - chaserY);
                }
            }else{
                this.targetX = wakaX;
                this.targetY = wakaY;
            }
        }else{
            this.targetY = 35;
            this.targetX = 27;
        }
    }
}