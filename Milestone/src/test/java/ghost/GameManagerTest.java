package ghost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import processing.core.PApplet;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class GameManagerTest{

    private Waka waka;
    private ArrayList<Ghost> ghosts;
    private JsonParser jParse;
    private Map map;
    private GameManager gameManager;
    private App app;

    @BeforeEach
    public void initialise(){
        gameManager = new GameManager();
        waka = new Waka(null,null,null,null,null);
        jParse = new JsonParser();
        jParse.readJson("config.json");
        map = new Map(null,null,null,null,null,null,null,null,null);
        map.parseMapToChar(jParse);
        map.mapCharToObject();
        waka.setConfig(jParse);
        waka.setWakaPosition(map);
        gameManager.setWaka(waka);
        gameManager.setjParse(jParse);
        gameManager.setMap(map);
        Ambusher ambusher = new Ambusher("Ambusher",null,waka,27,0,null,null);
        ambusher.setGhostPosition(map,1,'a');
        ambusher.setConfig(jParse,0,1);
        gameManager.addGhost(ambusher);
        Chaser chaser = new Chaser("Chaser",null,waka,0,0,null,null);
        chaser.setGhostPosition(map,1,'c');
        chaser.setConfig(jParse,2,3);
        gameManager.addGhost(chaser);
        Ignorant ignorant = new Ignorant("Ignorant",null,waka,0,35,null,null);
        ignorant.setGhostPosition(map,1,'i');
        ignorant.setConfig(jParse,4,5);
        gameManager.addGhost(ignorant);
        Whim whim = new Whim("Whim",null,waka,27,35,null,null);
        whim.setGhostPosition(map,1,'w');
        whim.setConfig(jParse,6,7);
        whim.setChaser(chaser);
        gameManager.addGhost(whim);
        app = new App();
    }

    @Test
    public void checkAllGhostCollisionWaka(){
        ArrayList<Ghost> ghosts = gameManager.getGhosts();
        ghosts.get(1).setX(13);
        ghosts.get(1).setY(20);
        assertTrue(gameManager.wakaGhostCollide());
    }

    @Test
    public void checkRemoveGhost(){
        ArrayList<Ghost> ghosts = gameManager.getGhosts();
        assertEquals(1,gameManager.getChaserIndex());
        ghosts.get(0).setX(13);
        ghosts.get(0).setY(20);
        gameManager.wakaGhostCollide();
        gameManager.removeGhost();
        assertEquals(0,gameManager.getChaserIndex());
        assertEquals(1,gameManager.getRemovedGhost().size());
        assertEquals(3,ghosts.size());

        //Remove a ghost infront of chaser
        ghosts.get(1).setX(13);
        ghosts.get(1).setY(20);
        gameManager.wakaGhostCollide();
        gameManager.removeGhost();
        assertEquals(2,ghosts.size());
        assertEquals(2,gameManager.getRemovedGhost().size());

        //Remove chaser
        ghosts.get(0).setX(13);
        ghosts.get(0).setY(20);
        gameManager.wakaGhostCollide();
        gameManager.removeGhost();
        assertEquals(1,ghosts.size());
        assertEquals(3,gameManager.getRemovedGhost().size());
    }

    @Test
    public void testIsGameEnd(){
        Waka waka = gameManager.getWaka();
        assertFalse(gameManager.isGameEnd());
        waka.setLives(0);
        assertTrue(gameManager.isGameEnd());
    }

    @Test
    public void testIsGameWin(){
        Waka waka = gameManager.getWaka();
        assertFalse(gameManager.isGameWin());
        waka.setFruitEaten(303);
        assertTrue(gameManager.isGameWin());
    }

    @Test
    public void testSetBooleanLine(){
        ArrayList<Ghost> ghosts = gameManager.getGhosts();
        assertFalse(ghosts.get(1).getDrawLine());
        gameManager.setBooleanLine(true);
        assertTrue(ghosts.get(1).getDrawLine());
    }

    @Test
    public void testSetFrightenMode(){
        ArrayList<Ghost> ghosts = gameManager.getGhosts();
        gameManager.setGhostMode("Frighten");
        assertEquals(ghosts.get(1).getMode(),"Frighten");
    }

    @Test
    public void testCheckGhostMode(){
        ArrayList<Ghost> ghosts = gameManager.getGhosts();
        assertFalse(gameManager.checkGhostFrightenMode());
        gameManager.setGhostMode("Frighten");
        assertEquals(ghosts.get(1).getMode(),"Frighten");
        assertTrue(gameManager.checkGhostFrightenMode());
    }

    @Test
    public void testCheckResetGameWinLose(){
        //Reset win
        gameManager.setWin(true);
        gameManager.resetGame();
        assertTrue(gameManager.getWin());
        gameManager.setFrameRate(599);
        gameManager.resetGame();
        assertFalse(gameManager.getWin());

        //Reset Lose
        gameManager.setFrameRate(0);
        gameManager.setLose(true);
        gameManager.resetGame();
        assertTrue(gameManager.getLose());
        gameManager.setFrameRate(599);
        gameManager.resetGame();
        assertFalse(gameManager.getLose());
    }

    @Test
    public void testCheckResetGameWakaGhostCollide(){
        //Reset Waka and Ghost collide
        ArrayList<Ghost> ghosts = gameManager.getGhosts();
        ghosts.get(0).setX(13);
        ghosts.get(0).setY(20);
        gameManager.wakaGhostCollide();
        gameManager.removeGhost();
        assertEquals(1,gameManager.getRemovedGhost().size());
        assertEquals(3,ghosts.size());
        gameManager.resetGame();
        assertEquals(0,gameManager.getRemovedGhost().size());
        assertEquals(4,ghosts.size());
    }

    @Test
    public void testKeyPressed(){
        waka.setX(6);
        waka.setY(17);

        // Press Up
        app.key = PApplet.CODED;
        app.keyCode = PApplet.UP;
        gameManager.keyPressed(app);
        assertEquals("Up",waka.getDirection());

        //Press Down
        app.key = PApplet.CODED;
        app.keyCode = PApplet.DOWN;
        gameManager.keyPressed(app);
        assertEquals("Down",waka.getDirection());

        //Press Left
        app.key = PApplet.CODED;
        app.keyCode = PApplet.LEFT;
        gameManager.keyPressed(app);
        assertEquals("Left",waka.getDirection());

        //Press Right
        app.key = PApplet.CODED;
        app.keyCode = PApplet.RIGHT;
        gameManager.keyPressed(app);
        assertEquals("Right",waka.getDirection());
    }
}