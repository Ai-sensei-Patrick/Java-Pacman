package ghost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GhostTest {

    private Waka waka;
    private Map map;
    private JsonParser jParse;
    private Ghost chaser;

    @BeforeEach
    public void initialise(){
        waka = new Waka(null,null,null,null,null);
        jParse = new JsonParser();
        jParse.readJson("config.json");
        map = new Map(null,null,null,null,null,null,null,null,null);
        map.parseMapToChar(jParse);
        map.mapCharToObject();
        waka.setConfig(jParse);
        waka.setWakaPosition(map);
        chaser = new Chaser("Chaser",null,waka,0,0,null,null);
        chaser.setGhostPosition(map,1,'c');
        chaser.setConfig(jParse,2,3);
    }

    @Test
    public void testSetterGetter(){
        assertEquals("Left",chaser.getDirection());
        assertEquals(12,chaser.getX());
        assertEquals(14,chaser.getY());
        assertEquals(0,chaser.getPixelsX());
        assertEquals(0,chaser.getPixelsY());
        assertEquals("Scatter",chaser.getMode());
        assertEquals("Chaser",chaser.getType());
        assertTrue(chaser.pixelCheck());
        chaser.setDirection("Right");
        assertEquals("Right",chaser.getDirection());
        chaser.setX(1);
        chaser.setY(1);
        assertEquals(1,chaser.getX());
        assertEquals(1,chaser.getY());
        chaser.setDrawLine(true);
        assertTrue(chaser.getDrawLine());
        chaser.setNoChaser(false);
        assertFalse(chaser.getNoChaser());
        chaser.setGhostPosition(map,2,'c');
        assertEquals(1,chaser.getX());
        assertEquals(1,chaser.getY());
    }

    @Test
    public void testcheckNextMove(){
        //From Left
        chaser.setTargetX(13);
        chaser.setTargetY(20);
        chaser.setX(6);
        chaser.setY(17);
        assertTrue(chaser.checkIntersection(map));
        assertEquals("Left",chaser.getDirection());
        chaser.checkNextMove(map);
        assertEquals("Down",chaser.getDirection());

        //From Right
        chaser.setDirection("Right");
        chaser.setTargetX(13);
        chaser.setTargetY(20);
        chaser.setX(6);
        chaser.setY(17);
        chaser.checkNextMove(map);
        assertEquals("Right",chaser.getDirection());

        //From Down
        chaser.setDirection("Down");
        chaser.setTargetX(13);
        chaser.setTargetY(20);
        chaser.setX(6);
        chaser.setY(17);
        chaser.checkNextMove(map);
        assertEquals("Right",chaser.getDirection());

        //Block Left and Right
        chaser.setDirection("Down");
        chaser.setTargetX(13);
        chaser.setTargetY(20);
        chaser.setX(1);
        chaser.setY(3);
        chaser.checkNextMove(map);
        assertEquals("Down",chaser.getDirection());

        //Block Up and Down
        chaser.setDirection("Right");
        chaser.setTargetX(13);
        chaser.setTargetY(20);
        chaser.setX(2);
        chaser.setY(32);
        chaser.checkNextMove(map);
        assertEquals("Right",chaser.getDirection());

        //Pixel back to 0 only
        chaser.tick(map);
        chaser.checkNextMove(map);
        assertEquals("Right",chaser.getDirection());
    }

    @Test
    public void checkIntersection(){
        chaser.setX(1);
        chaser.setY(4);
        assertFalse(chaser.checkIntersection(map));
        chaser.setX(26);
        chaser.setY(4);
        assertFalse(chaser.checkIntersection(map));
        chaser.setX(1);
        chaser.setY(32);
        assertFalse(chaser.checkIntersection(map));
        chaser.setX(26);
        chaser.setY(32);
        assertFalse(chaser.checkIntersection(map));
    }

    @Test
    public void testGhostTickRight(){
        chaser.setTargetX(13);
        chaser.setTargetY(20);
        chaser.setX(6);
        chaser.setY(17);
        chaser.setDirection("Up");
        chaser.tick(map);
        assertEquals(1,chaser.getPixelsX());
        while (chaser.getX() != 7){
            chaser.tick(map);
        }
        assertTrue(chaser.pixelCheck());
        assertEquals(7,chaser.getX());
    }

    @Test
    public void testGhostTickLeft(){
        chaser.setTargetX(2);
        chaser.setTargetY(20);
        chaser.setX(6);
        chaser.setY(17);
        chaser.tick(map);
        assertEquals("Left",chaser.getDirection());
        assertEquals(-1,chaser.getPixelsX());
        while (chaser.getX() != 5){
            chaser.tick(map);
        }
        assertTrue(chaser.pixelCheck());
        assertEquals(5,chaser.getX());
    }

    @Test
    public void testGhostTickDown(){
        chaser.setTargetX(6);
        chaser.setTargetY(20);
        chaser.setX(6);
        chaser.setY(17);
        chaser.tick(map);
        assertEquals("Down",chaser.getDirection());
        assertEquals(1,chaser.getPixelsY());
        while (chaser.getY() != 18){
            chaser.tick(map);
        }
        assertTrue(chaser.pixelCheck());
        assertEquals(18,chaser.getY());
    }

    @Test
    public void testGhostTickUp(){
        chaser.setTargetX(6);
        chaser.setTargetY(15);
        chaser.setX(6);
        chaser.setY(17);
        chaser.tick(map);
        assertEquals("Up",chaser.getDirection());
        assertEquals(-1,chaser.getPixelsY());
        while (chaser.getY() != 16){
            chaser.tick(map);
        }
        assertTrue(chaser.pixelCheck());
        assertEquals(16,chaser.getY());
    }

    @Test
    public void testGhostTurnLeft(){
        chaser.setDirection("Down");
        chaser.setX(26);
        chaser.setY(32);
        chaser.turn(map);
        assertEquals("Left",chaser.getDirection());

        //Try to turn when next tile is a wall
        chaser.setDirection("Down");
        chaser.setX(1);
        chaser.setY(32);
        chaser.turn(map);
        assertEquals("Right",chaser.getDirection());
    }

    @Test
    public void testGhostTurnRight(){
        chaser.setDirection("Up");
        chaser.setX(1);
        chaser.setY(4);
        chaser.turn(map);
        assertEquals("Right",chaser.getDirection());

        //Try to turn when next tile is a wall
        chaser.setDirection("Up");
        chaser.setX(26);
        chaser.setY(4);
        chaser.turn(map);
        assertEquals("Left",chaser.getDirection());
    }

    @Test
    public void testGhostTurnUp(){
        chaser.setDirection("Left");
        chaser.setX(1);
        chaser.setY(32);
        chaser.turn(map);
        assertEquals("Up",chaser.getDirection());

        //Try to change direction
        chaser.setDirection("Up");
        chaser.setX(1);
        chaser.setY(5);
        chaser.turn(map);
        assertEquals("Up",chaser.getDirection());
    }

    @Test
    public void testGhostTurnDown(){
        chaser.setDirection("Right");
        chaser.setX(26);
        chaser.setY(4);
        chaser.turn(map);
        assertEquals("Down",chaser.getDirection());

        //Try to change direction
        chaser.setDirection("Down");
        chaser.setX(1);
        chaser.setY(5);
        chaser.turn(map);
        assertEquals("Down",chaser.getDirection());
    }

    @Test
    public void testChaseMode(){
        chaser.tick(map);
        chaser.setFrameRate(7*60);
        chaser.tick(map);
        assertEquals("Chase",chaser.getMode());
    }

    @Test
    public void testScatterMode(){
        chaser.setMode("Chase");
        chaser.tick(map);
        chaser.setFrameRate(20*60);
        chaser.tick(map);
        assertEquals("Scatter",chaser.getMode());
    }

    @Test
    public void testFrightenMode(){
        chaser.setMode("Frighten");
        chaser.tick(map);
        chaser.setFrameRate(10*60);
        chaser.tick(map);
        assertEquals("Scatter",chaser.getMode());
    }

    @Test
    public void testSodaMode(){
        chaser.setMode("Soda");
        chaser.tick(map);
        chaser.setFrameRate(10*60);
        chaser.tick(map);
        assertEquals("Scatter",chaser.getMode());
    }

    @Test
    public void testReset(){
        chaser.reset();
        assertEquals(0,chaser.getFrameRate());
        assertEquals("Left",chaser.getDirection());
        assertEquals(12,chaser.getX());
        assertEquals(14,chaser.getY());
        assertEquals(0,chaser.getPixelsX());
        assertEquals(0,chaser.getPixelsY());
        assertEquals("Scatter",chaser.getMode());
    }

    @Test
    public void testBound(){
        chaser.setX(chaser.checkBoundX(29));
        chaser.setY(chaser.checkBoundY(37));
        assertEquals(27,chaser.getX());
        assertEquals(35,chaser.getY());
        chaser.setX(chaser.checkBoundX(-10));
        chaser.setY(chaser.checkBoundY(-8));
        assertEquals(0,chaser.getX());
        assertEquals(0,chaser.getY());
        chaser.setX(chaser.checkBoundX(10));
        chaser.setY(chaser.checkBoundY(15));
        assertEquals(10,chaser.getX());
        assertEquals(15,chaser.getY());
    }

    @Test
    public void testWakaGhostCollide(){
        chaser.setX(4);
        chaser.setY(5);
        waka.setX(4);
        waka.setY(5);
        assertTrue(chaser.checkWakaCollision());
        chaser.setX(3);
        chaser.setY(5);
        waka.setX(4);
        waka.setY(5);
        assertFalse(chaser.checkWakaCollision());
        chaser.setX(4);
        chaser.setY(5);
        waka.setX(4);
        waka.setY(6);
        assertFalse(chaser.checkWakaCollision());
    }
}