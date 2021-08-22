package ghost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GhostIgnorantTest {

    private Waka waka;
    private Map map;
    private JsonParser jParse;
    private Ignorant ignorant;

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
        ignorant = new Ignorant("Ignorant",null,waka,0,35,null,null);
        ignorant.setGhostPosition(map,1,'i');
        ignorant.setConfig(jParse,4,5);
    }

    @Test
    public void testCheckTargetScatter(){
        ignorant.checkTarget();
        assertEquals(0,ignorant.getTargetX());
        assertEquals(35,ignorant.getTargetY());
    }

     @Test
    public void testCheckTargetChase(){
        //ignorant closer than 8 distance
        ignorant.setMode("Chase");
        ignorant.checkTarget();
        assertEquals(0,ignorant.getTargetX());
        assertEquals(35,ignorant.getTargetY());

        //ignorant 8 distance away
        ignorant.setX(1);
        ignorant.setY(4);
        ignorant.checkTarget();
        assertEquals(13,ignorant.getTargetX());
        assertEquals(20,ignorant.getTargetY());
    }
}