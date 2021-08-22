package ghost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class WakaTest {

    private Waka waka;
    private Map map;
    private JsonParser jParse;

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
    }

    @Test 
    public void simpleTest() {
        assertNotNull(waka);
    }

    @Test
    public void testGetterSetter(){
        assertEquals("Left",waka.getDirection());
        assertEquals(13,waka.getX());
        assertEquals(20,waka.getY());
        assertEquals(0,waka.getPixelsX());
        assertEquals(0,waka.getPixelsY());
        assertFalse(waka.getIsSuper());
        assertEquals(0,waka.getfruitEaten());
        assertEquals(3,waka.getLives());
        assertTrue(waka.pixelCheck());
        assertEquals(null,waka.getfDirection());
        waka.setDirection("Right");
        assertEquals("Right",waka.getDirection());
        waka.setX(1);
        waka.setY(1);
        assertEquals(1,waka.getX());
        assertEquals(1,waka.getY());
        assertEquals(0,waka.getFrameRate());
        waka.setIsSuper(true);
        assertTrue(waka.getIsSuper());
        waka.setIsSoda(true);
        assertTrue(waka.getIsSoda());
    }

    @Test
    public void testTickLeft(){
        //waka go left
        waka.moveLeft(map);
        assertEquals(waka.getDirection(),"Left");
        waka.tick(map);
        assertEquals(waka.getPixelsX(),-1);
        while(waka.getX() != 12){
            waka.tick(map);
            if(waka.getPixelsX() == -8 && waka.getX() == 13){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        //waka try to move up or down
        waka.moveDown(map);
        assertEquals(waka.getfDirection(),"Down");
        waka.moveUp(map);
        assertEquals(waka.getfDirection(),"Up");
        assertEquals(waka.getX(),12);
        assertEquals(waka.getY(),20);
        assertTrue(waka.pixelCheck());
    }

    @Test
    public void testTickLeftSuper(){
        //waka go left and eat superfruit
        waka.setX(2);
        waka.setY(4);
        waka.moveUp(map);
        assertEquals(waka.getDirection(),"Left");
        while(waka.getX() != 1){
            waka.tick(map);
            if(waka.getPixelsX() == -8 && waka.getX() == 2){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        assertTrue(waka.getIsSuper());
    }

    @Test
    public void testTickRight(){
        //waka go right
        waka.moveRight(map);
        assertEquals(waka.getDirection(),"Right");
        waka.tick(map);
        assertEquals(waka.getPixelsX(),1);
        while(waka.getX() != 14){
            waka.tick(map);
            if(waka.getPixelsX() == 8 && waka.getX() == 13){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        //waka try to move up or down
        waka.moveUp(map);
        assertEquals(waka.getfDirection(),"Up");
        waka.moveDown(map);
        assertEquals(waka.getfDirection(),"Down");
        assertEquals(waka.getX(),14);
        assertEquals(waka.getY(),20);
        assertTrue(waka.pixelCheck());
    }

    @Test
    public void testTickRightSuper(){
        //waka move right and eat superfruit
        waka.setX(25);
        waka.setY(4);
        waka.moveRight(map);
        assertEquals(waka.getDirection(),"Right");
        while(waka.getX() != 26){
            waka.tick(map);
            if(waka.getPixelsX() == 8 && waka.getX() == 25){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        assertTrue(waka.getIsSuper());

    }

    @Test
    public void testTickUp(){
        waka.setX(9);
        waka.setY(20);

        // waka go up
        waka.moveUp(map);
        assertEquals(waka.getDirection(), "Up");
        waka.tick(map);
        assertEquals(waka.getPixelsY(),-1);
        while(waka.getY() != 19){
            waka.tick(map);
            if(waka.getPixelsY() == -8 && waka.getY() == 20){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        // waka try to right and left
        waka.moveRight(map);
        assertEquals(waka.getfDirection(),"Right");
        waka.moveLeft(map);
        assertEquals(waka.getfDirection(),"Left");

        assertEquals(waka.getX(),9);
        assertEquals(waka.getY(),19);
        assertTrue(waka.pixelCheck());
    }

    @Test
    public void testTickUpSuper(){
        //waka move up and eat superFruit
        waka.setX(1);
        waka.setY(5);
        waka.moveUp(map);
        assertEquals(waka.getDirection(),"Up");
        while(waka.getY() != 4){
            waka.tick(map);
            if(waka.getPixelsY() == -8 && waka.getY() == 5){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        assertTrue(waka.getIsSuper());
    }

    @Test
    public void testTickDown(){
        // waka go down
        waka.setX(9);
        waka.setY(20);
        waka.moveDown(map);
        assertEquals(waka.getDirection(),"Down");
        waka.tick(map);
        assertEquals(waka.getPixelsY(),1);
        while(waka.getY() != 21){
            waka.tick(map);
            if(waka.getPixelsY() == 8 && waka.getY() == 20){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        // waka try to move right and left
        waka.moveLeft(map);
        assertEquals(waka.getfDirection(),"Left");
        waka.moveRight(map);
        assertEquals(waka.getfDirection(),"Right");

        assertEquals(waka.getX(),9);
        assertEquals(waka.getY(),21);
        assertTrue(waka.pixelCheck());
    }

    @Test
    public void testTickDownSuper(){
        //waka move down and eat superFruit
        waka.setX(1);
        waka.setY(31);
        waka.moveDown(map);
        assertEquals(waka.getDirection(),"Down");
        while(waka.getY() != 32){
            waka.tick(map);
            if(waka.getPixelsY() == 8 && waka.getY() == 31){
                waka.moveRight(map);
                assertEquals(waka.getDirection(),"Down");
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        assertTrue(waka.getIsSuper());
    }

    @Test
    public void testTickDownSoda(){
        //waka move down and drink Soda
        waka.setX(1);
        waka.setY(8);
        waka.moveDown(map);
        assertEquals(waka.getDirection(),"Down");
        while(waka.getY() != 9){
            waka.tick(map);
            if(waka.getPixelsY() == 8 && waka.getY() == 8){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        assertTrue(waka.getIsSoda());
    }

    @Test
    public void testTickUpSoda(){
        //waka move down and drink Soda
        waka.setX(26);
        waka.setY(10);
        waka.moveUp(map);
        assertEquals(waka.getDirection(),"Up");
        while(waka.getY() != 9){
            waka.tick(map);
            if(waka.getPixelsY() == 8 && waka.getY() == 10){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        assertTrue(waka.getIsSoda());
    }

    @Test
    public void testTickLeftSoda(){
        //waka move down and drink Soda
        waka.setX(2);
        waka.setY(26);
        waka.moveLeft(map);
        assertEquals(waka.getDirection(),"Left");
        while(waka.getX() != 1){
            waka.tick(map);
            if(waka.getPixelsY() == 8 && waka.getX() == 2){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        assertTrue(waka.getIsSoda());
    }

    @Test
    public void testTickRightSoda(){
        //waka move down and drink Soda
        waka.setX(25);
        waka.setY(26);
        waka.moveRight(map);
        assertEquals(waka.getDirection(),"Right");
        while(waka.getX() != 26){
            waka.tick(map);
            if(waka.getPixelsY() == 8 && waka.getX() == 25){
                assertEquals(waka.getfruitEaten(),1);
                assertFalse(waka.pixelCheck());
            }
        }
        assertTrue(waka.getIsSoda());
    }

    @Test
    public void testTickLeftWall(){
        waka.setX(9);
        waka.setY(20);
        waka.setDirection("Left");
        waka.tick(map);
        assertEquals(0,waka.getPixelsX());
    }

    @Test
    public void testTickUpWall(){
        waka.setDirection("Up");
        waka.tick(map);
        assertEquals(0,waka.getPixelsY());
    }

    @Test
    public void testTickDownWall(){
        waka.setDirection("Down");
        waka.tick(map);
        assertEquals(0,waka.getPixelsY());
    }

    @Test
    public void testTickRightWall(){
        waka.setX(18);
        waka.setY(20);
        waka.setDirection("Right");
        waka.tick(map);
        assertEquals(0,waka.getPixelsX());
    }

    @Test
    public void testNextMoveDown(){
        waka.moveDown(map);
        while (waka.getX() != 9){
            waka.tick(map);
        }
        waka.checkNextMove(map);
        assertEquals("Down",waka.getDirection());
        waka.setX(21);
        waka.setY(17);
        waka.moveRight(map);
        waka.tick(map);
        waka.moveDown(map);
        waka.checkNextMove(map);
        assertEquals("Right",waka.getDirection());
    }

    @Test
    public void testNextMoveUp(){
        waka.moveUp(map);
        while (waka.getX() != 9){
            waka.tick(map);
        }
        waka.checkNextMove(map);
        assertEquals("Up",waka.getDirection());
        waka.setX(6);
        waka.setY(17);
        waka.moveLeft(map);
        waka.tick(map);
        waka.moveUp(map);
        waka.checkNextMove(map);
        assertEquals("Left",waka.getDirection());
    }

    @Test
    public void testNextMoveLeft(){
        waka.setX(6);
        waka.setY(18);
        waka.setDirection("Up");
        waka.moveLeft(map);
        while (waka.getY() != 17){
            waka.tick(map);
        }
        waka.checkNextMove(map);
        assertEquals("Left",waka.getDirection());
        waka.setDirection("Up");
        waka.tick(map);
        waka.moveLeft(map);
        waka.checkNextMove(map);
        assertEquals("Up",waka.getDirection());
    }

    @Test
    public void testNextMoveRight(){
        waka.setX(21);
        waka.setY(18);
        waka.setDirection("Up");
        waka.moveRight(map);
        while (waka.getY() != 17){
            waka.tick(map);
        }
        waka.checkNextMove(map);
        assertEquals("Right",waka.getDirection());
        waka.setDirection("Up");
        waka.tick(map);
        waka.moveRight(map);
        waka.checkNextMove(map);
        assertEquals("Up",waka.getDirection());
    }

    @Test
    public void testReset(){
        waka.reset();
        assertEquals("Left",waka.getDirection());
        assertEquals(null,waka.getfDirection());
        assertEquals(13,waka.getX());
        assertEquals(20,waka.getY());
        assertEquals(0,waka.getPixelsX());
        assertEquals(0,waka.getPixelsY());
        assertEquals(2,waka.getLives());
        assertEquals(0,waka.getFrameRate());
    }
}