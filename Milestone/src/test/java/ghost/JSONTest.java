package ghost;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class JSONTest {

    private JsonParser jParse;
    
    @BeforeEach
    public void intialise(){
        jParse = new JsonParser();
    }

    public void testAllMethod(){
        assertNull(jParse.getFilename());
        assertEquals(0,jParse.getLives());
        assertEquals(0,jParse.getSpeed());
        assertEquals(0,jParse.getFrightened());
        assertEquals(0,jParse.getSoda());
        assertNull(jParse.getModeLengths());

        jParse.readJson("config.json");

        //After read .CONFIG file
        assertNotNull(jParse.getFilename());
        assertEquals(3,jParse.getLives());
        assertEquals(1,jParse.getSpeed());
        assertEquals(10,jParse.getFrightened());
        assertEquals(10,jParse.getSoda());
        assertNotNull(jParse.getModeLengths());
    }

}