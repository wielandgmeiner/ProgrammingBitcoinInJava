package util;

import org.junit.Test;

import static org.junit.Assert.*;
import static util.S256Util.*;

public class S256UtilTest {

    @Test
    public void testOrderOfGisN() {
        assertEquals(INFINITY, G.rmul(N));
    }
}
