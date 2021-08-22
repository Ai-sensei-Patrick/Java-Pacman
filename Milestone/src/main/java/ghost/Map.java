package ghost;

import java.io.*;
import java.util.*;
import processing.core.PApplet;
import processing.core.PImage;

public class Map{

    private char[][] mapLayOut;
    private Entities[][] mapLayOutObj;
    private PImage mapDownLeft;
    private PImage mapDownRight;
    private PImage mapFruit;
    private PImage mapHorizontal;
    private PImage mapUpLeft;
    private PImage mapUpRight;
    private PImage mapVertical;
    private PImage mapSuperFruit;
    private PImage mapSoda;
    private int numIgnorant;
    private int numWhim;
    private int numChaser;
    private int numAmbusher;
    private int numFruit;

    /**
     * Store map layout in form of character, map layout in form of Entities object, all image of map object, 
     * all images of every type of fruits and number of ghost of each type.
     * 
     * @param mapDownLeft Image of corner down left of the map.
     * @param mapUpLeft Image of corner up left of the map.
     * @param mapUpRight Image of corner up right of the map.
     * @param mapDownRight Image of corner down right of the map.
     * @param mapVertical Image of vertical map.
     * @param mapHorizontal Image of horizontal map.
     * @param mapFruit Image of fruit.
     * @param mapSuperFruit Image of superFruit.
     * @param mapSoda Image of soda.
     */
    public Map(PImage mapDownLeft,PImage mapDownRight,PImage mapFruit,PImage mapHorizontal,PImage mapUpLeft,PImage mapUpRight,PImage mapVertical,PImage mapSuperFruit,PImage mapSoda){
        this.mapLayOut = null;
        this.mapLayOutObj = null;
        this.mapUpLeft = mapUpLeft;
        this.mapDownLeft = mapDownLeft;
        this.mapUpRight = mapUpRight;
        this.mapDownRight = mapDownRight;
        this.mapVertical = mapVertical;
        this.mapHorizontal = mapHorizontal;
        this.mapFruit = mapFruit;
        this.mapSuperFruit = mapSuperFruit;
        this.mapSoda = mapSoda;
        this.numAmbusher = 0;
        this.numChaser = 0;
        this.numIgnorant = 0;
        this.numWhim = 0;
        this.numFruit = 0;
    }

    /**
     * Return map layout in form of two dimensional char array.
     *
     * @return two dimensional array of type character.
     */
    public char[][] getMapLayout(){
        return this.mapLayOut;
    }

    /**
     * Return map layout in form of two dimensional Entities object array.
     *
     * @return two dimensional array of type Entities object.
     */
    public Entities[][] getMapLayoutObj(){
        return this.mapLayOutObj;
    }

    /**
     * Return map layout in form of two dimensional Entities object array.
     *
     * @return two dimensional array of type Entities object.
     */
    public int getNumAmbusher(){
        return this.numAmbusher;
    }

    /**
     * Return map layout in form of two dimensional Entities object array.
     *
     * @return two dimensional array of type Entities object.
     */
    public int getNumChaser(){
        return this.numChaser;
    }

    /**
     * Return map layout in form of two dimensional Entities object array.
     *
     * @return two dimensional array of type Entities object.
     */
    public int getNumIgnorant(){
        return this.numIgnorant;
    }

    /**
     * Return map layout in form of two dimensional Entities object array.
     *
     * @return two dimensional array of type Entities object.
     */
    public int getNumWhim(){
        return this.numWhim;
    }

    /**
     * Return map layout in form of two dimensional Entities object array.
     *
     * @return two dimensional array of type Entities object.
     */
    public int getNumFruit(){
        return this.numFruit;
    }

    /**
     * Take filename from the config object, than read the file and convert them into a map layout in form of two dimensional character array.
     * Set the two dimensional array of type character to instance variable name mapLayOut.
     *
     * @param parsed JsonParser Object.
     */
    public void parseMapToChar (JsonParser parsed){

        String filename = parsed.getFilename();
        try {
            FileReader myObj = new FileReader(filename);
            Scanner myReader = new Scanner(myObj);
            ArrayList<char[]> arrList = new ArrayList<char[]>();
            while (myReader.hasNextLine()) {
                String row = myReader.nextLine();
                char[] inner = new char[row.length()];
                for(int j = 0; j < row.length(); j++){
                    inner[j] = row.charAt(j);
                }
                arrList.add(inner);
            }
            myReader.close();

            char[][] result = new char[arrList.size()][];
            for (int i = 0; i < arrList.size(); i++){
                result[i] = arrList.get(i);
            }
            this.mapLayOut = result;

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Take two dimensional character array that represent the map stored in instance variable and 
     * convert them into a map layout in form of two dimensional Entities Object array.
     * Count number of ghost for each type of ghost (Ambusher, Chaser, Ignorant, Whim).
     * <p>
     * Set the two dimensional array of type Entities Object (Contain only fruit, superfruit and wall) to instance variable name mapLayOutObj.
     */
    public void mapCharToObject(){

        Entities[][] arrObj = new Entities[this.mapLayOut.length][];
        for (int i = 0; i < this.mapLayOut.length; i++){

            Entities[] inner = new Entities[this.mapLayOut[i].length];

            for (int j = 0; j < this.mapLayOut[i].length; j++){

                if (this.mapLayOut[i][j] == '1'){
                    Entities obj = new Horizontal(this.mapHorizontal,j * 16,i * 16,true);
                    inner[j] = obj;
                }

                else if (this.mapLayOut[i][j] == '2'){
                    Entities obj = new Vertical(this.mapVertical,j * 16,i * 16,true);
                    inner[j] = obj;
                }

                else if (this.mapLayOut[i][j] == '3'){
                    Entities obj = new UpLeft(this.mapUpLeft,j * 16,i * 16,true);
                    inner[j] = obj;
                }

                else if (this.mapLayOut[i][j] == '4'){
                    Entities obj = new UpRight(this.mapUpRight,j * 16,i * 16,true);
                    inner[j] = obj;
                }

                else if (this.mapLayOut[i][j] == '5'){
                    Entities obj = new DownLeft(this.mapDownLeft,j * 16,i * 16,true);
                    inner[j] = obj;
                }

                else if (this.mapLayOut[i][j] == '6'){
                    Entities obj = new DownRight(this.mapDownRight,j * 16,i * 16,true);
                    inner[j] = obj;
                }

                else if (this.mapLayOut[i][j] == '7'){
                    Entities obj = new Fruit(this.mapFruit,j * 16,i * 16,false);
                    inner[j] = obj;
                    this.numFruit += 1;
                }

                else if (this.mapLayOut[i][j] == '8'){
                    Entities obj = new SuperFruit(this.mapSuperFruit,j * 16,i * 16,false);
                    inner[j] = obj;
                    this.numFruit += 1;
                }

                else if (this.mapLayOut[i][j] == '9'){
                    Entities obj = new Soda(this.mapSoda,j * 16,i * 16,false);
                    inner[j] = obj;
                    this.numFruit += 1;
                }

                else if (this.mapLayOut[i][j] == 'a'){
                    this.numAmbusher += 1;
                    Entities obj = new Empty(null,j * 16,i * 16,false);
                    inner[j] = obj;
                }

                else if (this.mapLayOut[i][j] == 'c'){
                    this.numChaser += 1;
                    Entities obj = new Empty(null,j * 16,i * 16,false);
                    inner[j] = obj;
                }

                else if (this.mapLayOut[i][j] == 'i'){
                    this.numIgnorant += 1;
                    Entities obj = new Empty(null,j * 16,i * 16,false);
                    inner[j] = obj;
                }

                else if (this.mapLayOut[i][j] == 'w'){
                    this.numWhim += 1;
                    Entities obj = new Empty(null,j * 16,i * 16,false);
                    inner[j] = obj;
                }

                else{
                    Entities obj = new Empty(null,j * 16,i * 16,false);
                    inner[j] = obj;
                }
            }
            arrObj[i] = inner;
        }
        this.mapLayOutObj = arrObj;
    }

    /**
     * Draw the map using the two dimensional array that contain Entities object: 
     * get the image from each object, Xcoordinate of the object and Ycoordinate of the object.
     * <p>
     * If the image of the object null, continue.
     *
     * @param app PApplet Object
     */
    public void draw(App app){
        for (int i = 0; i < this.mapLayOutObj.length; i++){

            for (int j = 0; j < this.mapLayOutObj[i].length; j++){

                if (this.mapLayOutObj[i][j].getImage() == null){
                    continue;
                }

                PImage k = this.mapLayOutObj[i][j].getImage();
                int x = this.mapLayOutObj[i][j].getX();
                int y = this.mapLayOutObj[i][j].getY();

                app.image(k,x,y);
            }
        }
    }
}