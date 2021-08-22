package ghost;
import processing.core.PImage;
import processing.core.PApplet;
import org.json.simple.JSONArray;

public class Ignorant extends Ghost{

    /**
     * Store Ignorant intial position, Ignorant x/y pixels, all image of Ignorant in different mode, Ignorant current image,Ignorant x/y coordinate 
     * frameRate, Ignorant previous mode, duration scatter mode, duration chaser mode, Ignorant speed, duration frighten mode,
     * direction of Ignorant, Ignorant target x/y and indicate to draw line.
     * @param type Ignorant.
     * @param image Image of Ignorant in normal mode.
     * @param waka Waka object.
     * @param fImage Image of Ignorant on frighten mode.
     * @param targetX Initial target of Ignorant (Corner of map (Xcoordinate))
     * @param targetY Initial target of Ignorant (Corner of map (Ycoordinate))
     * @param sImage Image of Ignorant on Soda mode.
     */
    public Ignorant(String type,PImage image, Waka waka, int targetX, int targetY, PImage fImage,PImage sImage){
        super(type,image,waka,targetX,targetY,fImage,sImage);
    }

    /**
     * If Ignorant in chase mode, find the distance difference between them using distance formula. 
     * If the distance formula output more than 8 set waka coordinate as targetX and targetY, otherwise set targetX and targetY as in scatter mode.
     * Check for their bound also, than set that results to ghost targetX and targetY position.
     * <p>
     * If Ambusher in scatter mode, set ghost targetX to 35 and set ghost targetY to 0.
     * 
     */
    public void checkTarget(){
        if(this.mode.equals("Chase")){
            this.targetY = this.waka.getY();
            this.targetX = this.waka.getX();
            int diffX = this.checkDiffX();
            int diffY = this.checkDiffY();
            double formula = Math.sqrt(Math.pow(diffX , 2) + Math.pow(diffY, 2));
            if(formula > 8){
                this.targetY = this.waka.getY();
                this.targetX = this.waka.getX();
            }
            else{
                this.targetY = 35;
                this.targetX = 0;
            }
        }else{
            this.targetY = 35;
            this.targetX = 0;
        }
    }
}