package util;

import ecc.Point;
import ecc.S256FieldElement;
import ecc.Signature;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static util.S256Util.*;

public class S256UtilTest {

    @Test
    public void testOrderOfGisN() {
        assertEquals(INFINITY, G.rmul(N));
    }

    @Test
    public void testSignature() {
        BigInteger e = new BigInteger(1, hash256("my secret"));
        assertEquals("8b387de39861728c92ec9f589c303b1038ff60eb3963b12cd212263a1d1e0f00",
                e.toString(16));
        BigInteger z = new BigInteger(1, hash256("my message"));
        assertEquals("231c6f3d980a6b0fb7152f85cee7eb52bf92433d9919b9c5218cb08e79cce78",
                z.toString(16));
        BigInteger k = BigInteger.valueOf(1234567890L);
        assertEquals("499602d2", k.toString(16));
        BigInteger r = (BigInteger) G.rmul(k).getX().getNum();
        assertEquals("2b698a0f0a4041b77e63488ad48c23e8e8838dd1fb7520408b121697b782ef22",
                r.toString(16));
        BigInteger k_inv = k.modPow(N.subtract(BigInteger.valueOf(2L)), N);
        assertEquals("6bd555ecd0e4e06df23bfbb091158daaa0c6ba7347f32b95f4484e8dceb39d91",
                k_inv.toString(16));
        BigInteger s = z.add(r.multiply(e).mod(N)).multiply(k_inv).mod(N);
        assertEquals("bb14e602ef9e3f872e25fad328466b34e6734b7a0fcd58b1eb635447ffae8cb9",
                s.toString(16));
        Point<S256FieldElement> point = G.rmul(e);
        System.out.println(point);
        System.out.println(z.toString(16));
        System.out.println(r.toString(16));
        System.out.println(s.toString(16));

        // verify
        assertTrue(verify(point, z, new Signature(r, s)));
    }
}
