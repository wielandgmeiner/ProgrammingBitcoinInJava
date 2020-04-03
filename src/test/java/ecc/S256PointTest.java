package ecc;

import org.junit.Test;

import static org.junit.Assert.*;
import static util.S256Util.*;

public class S256PointTest {

    @Test
    public void testOrder() {
        S256Point p = G.rmul(N);
        assertEquals(INFINITY, p);
    }
}
