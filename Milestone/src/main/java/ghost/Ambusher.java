package ghost;
import processing.core.PApplet;
import processing.core.PImage;

public class Ambusher extends Ghost{

    /**
     * Store Ambusher intial position, Ambusher x/y pixels, all image of Ambusher in different mode, Ambusher current image,Ambusher x/y coordinate
     * frameRate, Ambusher previous mode, duration scatter mode, duration chaser mode, Ambusher speed, duration frighten mode,
     * direction of Ambusher, Ambusher target x/y, and indicate to draw line.
     *
     * @param type Ambusher.
     * @param image Image of Ambusher in normal mode.
     * @param waka Waka object.
     * @param fImage Image of Ambusher on frighten mode.
     * @param targetX Initial target of Ambusher (Corner of map (Xcoordinate))
     * @param targetY Initial target of Ambusher (Corner of map (Ycoordinate))
     * @param sImage Image of Ambusher on Soda mode.
     */
    public Ambusher(String type,PImage image, Waka waka, int targetX, int targetY, PImage fImage, PImage sImage){
        super(type,image,waka,targetX,targetY,fImage,sImage);
    }

    /**
     * If Ambusher in chase mode, take waka Xcoordinate add/subtract (depend on waka direction) it by 4 coordinate a head waka direction.
     * Check for their bound also, than set that results to ghost targetX and targetY position.
     * <p>
     * If Ambusher in scatter mode, set ghost targetX to 27 and set ghost targetY to 0.
     * 
     */
    public void checkTarget(){
        if(this.mode.equals("Chase")){
            if (this.waka.getDirection().equals("Up")){
                this.targetY = this.checkBoundY(this.waka.getY() - 4);
            }
            else if (this.waka.getDirection().equals("Down")){
                this.targetY = this.checkBoundY(this.waka.getY() + 4);
            }
            else{
                this.targetY = this.waka.getY();
            }
        }else{
            this.targetY = 0;
        }
        if(this.mode.equals("Chase")){
            if(this.waka.getDirection().equals("Left")){
                this.targetX = this.checkBoundX(this.waka.getX() - 4);
            }
            else if(this.waka.getDirection().equals("Right")){
                this.targetX = this.checkBoundX(this.waka.getX() + 4);
            }
            else{
                this.targetX = this.waka.getX();
            }
        }else{
            this.targetX = 27;
        }
    }
}