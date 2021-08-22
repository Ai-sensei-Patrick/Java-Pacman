package ghost;
import processing.core.PApplet;
import processing.core.PImage;


public class Entities{
    /**
     * Xcoordinate of the Entities Object.
     */
    protected int x;
    /**
     * Ycoordinate of the Entities Object.
     */
    protected int y;
    /**
     * Image of the Entities Object.
     */
    protected PImage image;
    /**
     * Boolean to indicate is the Entities Object a solid.
     */
    protected boolean solid;
    /**
     * Boolean to indicate is the Entities Object a SuperFruit.
     */
    protected boolean isSuper;
    /**
     * Boolean to indicate is the Entities Object a Soda.
     */
    protected boolean isSoda;
    /**
     * Boolean to indicate is the Entities Object a Fruit.
     */
    protected boolean isFruit;

    /**
     * Store Entities Object Xcoordinate, Ycoordinate, image of the Entities Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each Entities Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the Entities Object.
     * @param y Ycoordinate of the Entities Object.
     */
    public Entities(PImage image, int x, int y, boolean solid){
        this.x = x;
        this.y = y;
        this.image = image;
        this.solid = solid;
        this.isSuper = false;
        this.isFruit = false;
        this.isSoda = false;
    }

    /**
     * Return xCoordinate of the Entities Object.
     * 
     * @return xCoordinate of the Entities Object.
     */
    public int getX(){
        return this.x;
    }

    /**
     * Return yCoordinate of the Entities Object.
     * 
     * @return yCoordinate of the Entities Object.
     */
    public int getY(){
        return this.y;
    }

    /**
     * Return image of the Entities Object.
     * 
     * @return image of the Entities Object.
     */
    public PImage getImage(){
        return this.image;
    }

    /**
     * Return true if the Entities Object is a solid , otherwise false.
     * 
     * @return Return true if the Entities Object is a solid , otherwise false.
     */
    public boolean isSolid(){
        return this.solid;
    }

    /**
     * Return true if the Entities Object is a superFruit , otherwise false.
     * 
     * @return true if the Entities Object is a superFruit , otherwise false.
     */
    public boolean isSuper(){
        return this.isSuper;
    }

    /**
     * Return true if the Entities Object is a fruit , otherwise false.
     * 
     * @return true if the Entities Object is a fruit , otherwise false.
     */
    public boolean isFruit(){
        return this.isFruit;
    }

    /**
     * Return true if the Entities Object is a soda , otherwise false.
     * 
     * @return true if the Entities Object is a soda , otherwise false.
     */
    public boolean isSoda(){
        return this.isSoda;
    }
}

class Empty extends Entities{
    /**
     * Store Empty Object Xcoordinate, Ycoordinate, image of the Empty Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each Empty Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the Empty Object.
     * @param y Ycoordinate of the Empty Object.
     */
    public Empty(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
    }
}

class DownLeft extends Entities{
    /**
     * Store DownLeft Object Xcoordinate, Ycoordinate, image of the DownLeft Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each DownLeft Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the DownLeft Object.
     * @param y Ycoordinate of the DownLeft Object.
     */
    public DownLeft(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
    }
}

class DownRight extends Entities{
    /**
     * Store DownRight Object Xcoordinate, Ycoordinate, image of the DownRight Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each DownRight Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the DownRight Object.
     * @param y Ycoordinate of the DownRight Object.
     */
    public DownRight(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
    }
} 

class Fruit extends Entities{
    private boolean isSuper;
    /**
     * Store Fruit Object Xcoordinate, Ycoordinate, image of the Fruit Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each Fruit Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the Fruit Object.
     * @param y Ycoordinate of the Fruit Object.
     */
    public Fruit(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
        this.isFruit = true;
    }
}

class SuperFruit extends Entities{
    /**
     * Store SuperFruit Object Xcoordinate, Ycoordinate, image of the SuperFruit Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each SuperFruit Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the SuperFruit Object.
     * @param y Ycoordinate of the SuperFruit Object.
     */
    public SuperFruit(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
        this.isSuper = true;
        this.isFruit = true;
    }
}

class Horizontal extends Entities{
    /**
     * Store Horizontal Object Xcoordinate, Ycoordinate, image of the Horizontal Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each Horizontal Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the Horizontal Object.
     * @param y Ycoordinate of the Horizontal Object.
     */
    public Horizontal(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
    }
}

class UpLeft extends Entities{
    /**
     * Store UpLeft Object Xcoordinate, Ycoordinate, image of the UpLeft Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each UpLeft Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the UpLeft Object.
     * @param y Ycoordinate of the UpLeft Object.
     */
    public UpLeft(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
    }
}

class UpRight extends Entities{
    /**
     * Store UpRight Object Xcoordinate, Ycoordinate, image of the UpRight Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each UpRight Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the UpRight Object.
     * @param y Ycoordinate of the UpRight Object.
     */
    public UpRight(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
    }
}

class Vertical extends Entities{
    /**
     * Store Vertical Object Xcoordinate, Ycoordinate, image of the Vertical Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each Vertical Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the Vertical Object.
     * @param y Ycoordinate of the Vertical Object.
     */
    public Vertical(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
    }
}

class Soda extends Entities{
    /**
     * Store Soda Object Xcoordinate, Ycoordinate, image of the Soda Object, boolean indicate solid,
     * boolean indicate superFruit, boolean indicate soda and boolean indicate fruit.
     *
     * @param image Image of each Soda Object.
     * @param solid Boolean that indicate a solid.
     * @param x Xcoordinate of the Soda Object.
     * @param y Ycoordinate of the Soda Object.
     */
    public Soda(PImage image, int x, int y, boolean solid){
        super(image,x,y,solid);
        this.isSoda = true;
        this.isFruit = true;
    }
}