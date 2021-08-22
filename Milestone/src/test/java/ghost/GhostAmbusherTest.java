package ghost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GhostAmbusherTest {

    private Waka waka;
    private Map map;
    private JsonParser jParse;
    private Ambusher ambusher;

    @BeforeEach
    public void initialise(){
        waka = new Waka(null,null,null,null,null);
        jParse = new JsonParser();
        jParse.readJson("config.json");
        map = new Map(null,null,null,null,null,null,null,null,null);
        map.parseMapToChar(jParse);
        map.mapCharToObject();
        waka.setConfig(jParse);
        waka.setX(6);
        waka.setY(17);
        ambusher = new Ambusher("Ambusher",null,waka,27,0,null,null);
        ambusher.setGhostPosition(map,1,'a');
        ambusher.setConfig(jParse,2,3);
    }

    @Test
    public void testCheckTargetScatter(){
        ambusher.checkTarget();
        assertEquals(27,ambusher.getTargetX());
        assertEquals(0,ambusher.getTargetY());
    }

    @Test
    public void testCheckTargetChase(){
        ambusher.setMode("Chase");
        //Waka Left
        ambusher.checkTarget();
        assertEquals(2,ambusher.getTargetX());
        assertEquals(17,ambusher.getTargetY());

        //Waka Right
        waka.setDirection("Right");
        ambusher.checkTarget();
        assertEquals(10,ambusher.getTargetX());
        assertEquals(17,ambusher.getTargetY());

        //Waka Down
        waka.setDirection("Down");
        ambusher.checkTarget();
        assertEquals(6,ambusher.getTargetX());
        assertEquals(21,ambusher.getTargetY());

        //Waka Up
        waka.setDirection("Up");
        ambusher.checkTarget();
        assertEquals(6,ambusher.getTargetX());
        assertEquals(13,ambusher.getTargetY());
    }

}