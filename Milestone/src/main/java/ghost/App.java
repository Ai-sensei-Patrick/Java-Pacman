package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet{

    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;

    private GameManager gameManager;

    /**
     * Create a new GameManager Object.
     */
    public App() {
        //Set up your objects
        this.gameManager = new GameManager();
    }

    /**
     * Set Up a GameManager Object that contain all of the object in the game
     */
    public void setup(){
        frameRate(60);
        this.gameManager.readFile("config.json");
        this.gameManager.setUp(this);
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }
    
    /**
     * Handle and detect user input ,user press (Up, Down, Left, Right).
     */
    public void keyPressed(){
        this.gameManager.keyPressed(this);
    }

    /**
     * Draw all the Object contained inside the game manager
     */
    public void draw(){
        background(0,0,0);
        this.gameManager.draw(this);
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }

}
