package ghost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private Map map;
    private JsonParser jParse;

    @BeforeEach
    public void intialise(){
        jParse = new JsonParser();
        jParse.readJson("config.json");
        map = new Map(null,null,null,null,null,null,null,null,null);
    }

    @Test
    public void getterMethod(){
        assertEquals(0,map.getNumAmbusher());
        assertEquals(0,map.getNumChaser());
        assertEquals(0,map.getNumWhim());
        assertEquals(0,map.getNumIgnorant());
        assertEquals(0,map.getNumFruit());
    }

    @Test
    public void testMapToChar(){
        assertNull(map.getMapLayout());
        map.parseMapToChar(jParse);
        assertNotNull(map.getMapLayout());
    }

    @Test
    public void testMapCharToObject(){
        assertNull(map.getMapLayoutObj());
        map.parseMapToChar(jParse);
        map.mapCharToObject();
        assertEquals(303,map.getNumFruit());
        assertNotNull(map.getMapLayoutObj());
    }
}
