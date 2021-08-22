package ghost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GhostChaserTest {

    private Waka waka;
    private Map map;
    private JsonParser jParse;
    private Chaser chaser;

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
    public void testCheckTargetScatter(){
        chaser.checkTarget();
        assertEquals(0,chaser.getTargetX());
        assertEquals(0,chaser.getTargetY());
    }

    @Test
    public void testCheckTargetChase(){
        chaser.setMode("Chase");
        chaser.checkTarget();
        assertEquals(13,chaser.getTargetX());
        assertEquals(20,chaser.getTargetY());
    }
}
