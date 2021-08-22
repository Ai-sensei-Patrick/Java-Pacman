package ghost;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.*;

public class JsonParser{

    private String filename;
    private Long speed;
    private Long lives;
    private Long frightenedLength;
    private Long sodaLength;
    private JSONArray modeLengths;

    /**
    * Store filename of the maps, speed of ghost and waka, modeLengths for each ghost, frigthenedLength for each ghost and sodaLength for each ghost.
    */
    public JsonParser(){
        this.filename = null;
        this.speed = null;
        this.lives = null;
        this.modeLengths = null;
        this.frightenedLength = null;
        this.sodaLength = null;
    }

    /**
     * Return filename of the map.
     *
     * @return File name of the map.
     */
    public String getFilename(){
        return this.filename;
    }

    /**
     * Return speed of ghost and waka
     *
     * @return speed of ghost and waka
     */
    public Long getSpeed(){
        return this.speed;
    }

    /**
     * Return live of waka
     * 
     * @return Lives of waka.
     */
    public Long getLives(){
        return this.lives;
    }

    /**
     * Return ghost frightened mode duration.
     *
     * @return ghost frightened mode duration.
     */
    public Long getFrightened(){
        return this.frightenedLength;
    }

    /**
     * Return ghost Soda mode duration.
     *
     * @return ghost soda mode duration.
     */
    public Long getSoda(){
        return this.sodaLength;
    }

    /**
     * Return ghost mode duration.
     *
     * @return ghost mode duration.
     */
    public JSONArray getModeLengths(){
        return this.modeLengths;
    }

    /**
     * Read .CONFIG file and store them to instance variables : lives, filename, speed, modeLengths, sodaLengths, frightenedLengths.
     *
     * @param filenameJSON filename of .CONFIG file.
     */
    public void readJson(String filenameJSON){

        JSONParser jsonParser = new JSONParser();

        try{
            FileReader reader = new FileReader(filenameJSON);
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject jsObj = (JSONObject) obj;

            String filename = (String) jsObj.get("map");
            this.filename = filename;

            Long speed = (Long) jsObj.get("speed");
            this.speed = speed;

            Long lives = (Long) jsObj.get("lives");
            this.lives = lives;

            Long frightened = (Long) jsObj.get("frightenedLength");
            this.frightenedLength = frightened;

            Long soda = (Long) jsObj.get("sodaLength");
            this.sodaLength = soda; 

            JSONArray modeLengths = (JSONArray) jsObj.get("modeLengths");
            this.modeLengths = modeLengths;

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}