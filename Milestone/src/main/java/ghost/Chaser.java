package ghost;
import processing.core.PImage;
import processing.core.PApplet;
import org.json.simple.JSONArray;
import java.util.*;
import java.lang.*;

public class Chaser extends Ghost{ 
    
    /**
     * Store Chaser intial position, Chaser x/y pixels, all image of Chaser in different mode, Chaser current image,Chaser x/y coordinate
     * frameRate, Chaser previous mode, duration scatter mode, duration chaser mode, Chaser speed, duration frighten mode,
     * direction of Chaser, Chaser target x/y, and indicate to draw line.
     *
     * @param type Chaser.
     * @param image Image of Chaser in normal mode.
     * @param waka Waka object.
     * @param fImage Image of Chaser on frighten mode.
     * @param targetX Initial target of Chaser (Corner of map (Xcoordinate))
     * @param targetY Initial target of Chaser (Corner of map (Ycoordinate))
     * @param sImage Image of Chaser on Soda mode.
     */
    public Chaser(String type,PImage image, Waka waka, int targetX, int targetY,PImage fImage,PImage sImage){
        super(type,image,waka,targetX,targetY,fImage,sImage);
    }
    /**
     * If Chaser in chase mode, take waka Xcoordinate and Ycoordinate and set them as the targetX and targetY.
     * <p>
     * If Chaser in scatter mode, set ghost targetX to 0 and set ghost targetY to 0.
     * 
     */
    public void checkTarget(){
        if(this.mode.equals("Chase")){
            this.targetY = this.waka.getY();
            this.targetX = this.waka.getX();
        }else{
            this.targetY = 0;
            this.targetX = 0;
        }
    }
}