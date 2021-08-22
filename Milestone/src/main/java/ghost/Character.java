package ghost;
import processing.core.PApplet;
import processing.core.PImage;


public interface Character{
    /**
     * Move character, while checking the surrounding around them.
     * 
     * @param map Map Object
     */
    public void tick(Map map);
    /**
     * Draw character in correct position, for X axis : Xcoordinate * 16 add by their Xpixels add offset, for Y axis : Ycoordinate * 16 add by Ypixels add offset.
     * for ghost draw line if ghost is not in frighten mode 
     * @param app PApplet object.
     */
    public void draw(PApplet app);

    /**
     * Return true if both yPixels and xPixels is 0, else return false.
     *
     * @return Boolean outcome frome pixel checking.
     */
    public boolean pixelCheck();

    /**
     * Return Object direction.
     *
     * @return Object direction
     */
    public String getDirection();

    /**
     * Return Object xPixels.
     *
     * @return Object xPixels.
     */
    public int getPixelsX();

    /**
     * Return Object yPixels.
     *
     * @return Object yPixels.
     */
    public int getPixelsY();

    /**
     * Check object next move, if they able to change direction, their direction change.
     *
     * @param map Map Object.
     */
    public void checkNextMove(Map map);

    /**
     * Reset the object.
     */
    public void reset();

    /**
     * Set Object graphic.
     */
    public void setGraphic();

    /**
     * Return Object Xcoordinate.
     *
     * @return Xcoordinate.
     */
    public int getX();

    /**
     * Return Object Ycoordinate.
     *
     * @return Ycoordinate.
     */
    public int getY();

    /**
     * Set Object Xcoordinate.
     *
     * @param xVariable set Xcoordinate.
     */
    public void setX(int xVariable);

    /**
     * Set Object Ycoordinate.
     *
     * @param yVariable set Ycoordinate.
     */
    public void setY(int yVariable);

    /**
     * Return Object frameRate in game.
     *
     * @return frameRate.
     */
    public int getFrameRate();

    /**
     * Set Object direction.
     *
     * @param direction set direction for Object.
     */
    public void setDirection(String direction);
}