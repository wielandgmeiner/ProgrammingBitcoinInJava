package ecc;

import org.junit.Test;

import static org.junit.Assert.*;
import static util.S256Util.*;

public class S256PointTest {

    @Test
    public void testOrder() {
        Point<S256FieldElement> p = G.rmul(N);
        assertEquals(INFINITY, p);
    }
}
