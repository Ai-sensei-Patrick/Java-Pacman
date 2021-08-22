package ghost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GhostWhimTest {

    private Waka waka;
    private Map map;
    private JsonParser jParse;
    private Ghost chaser;
    private Whim whim;

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
        whim = new Whim("Whim",null,waka,27,35,null,null);
        whim.setChaser(chaser);
        assertNotNull(whim.getChaser());
    }

    @Test
    public void testCheckTargetScatter(){
        whim.checkTarget();
        assertEquals(27,whim.getTargetX());
        assertEquals(35,whim.getTargetY());
    }

    @Test
    public void testCheckTargetChase(){
        whim.setMode("Chase");
        waka.setX(6);
        waka.setY(17);
        chaser.setX(13);
        chaser.setY(20);

        //Waka Left
        whim.checkTarget();
        assertEquals(0,whim.getTargetX());
        assertEquals(14,whim.getTargetY());

        //Waka Right
        waka.setDirection("Right");
        whim.checkTarget();
        assertEquals(3,whim.getTargetX());
        assertEquals(14,whim.getTargetY());

        //Waka Down
        waka.setDirection("Down");
        whim.checkTarget();
        assertEquals(0,whim.getTargetX());
        assertEquals(18,whim.getTargetY());

        //Waka Up
        waka.setDirection("Up");
        whim.checkTarget();
        assertEquals(0,whim.getTargetX());
        assertEquals(10,whim.getTargetY());

        //No chaser
        whim.setNoChaser(true);
        whim.checkTarget();
        assertEquals(6,whim.getTargetX());
        assertEquals(17,whim.getTargetY());

    }
}