package ecc;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RealFieldElementTest {

    @Test
    public void testNotEquals() {
        RealFieldElement a = new RealFieldElement(2);
        RealFieldElement b = new RealFieldElement(2);
        RealFieldElement c = new RealFieldElement(15);
        assertTrue(a.equals(b));
        assertTrue( ! a.equals(c));
        assertFalse( ! a.equals(b));
    }

    @Test
    public void testAddition() {
        RealFieldElement a = new RealFieldElement(7);
        RealFieldElement b = new RealFieldElement(12);
        RealFieldElement c = new RealFieldElement(19);
        assertTrue(a.add(b).equals(c));

        a = new RealFieldElement(2);
        b = new RealFieldElement(15);
        assertTrue(a.add(b).equals(new RealFieldElement(17)));

        a = new RealFieldElement(17);
        b = new RealFieldElement(21);
        assertTrue(a.add(b).equals(new RealFieldElement(38)));
    }

    @Test
    public void testSubtraction() {
        RealFieldElement a = new RealFieldElement(29);
        RealFieldElement b = new RealFieldElement(4);
        assertTrue(a.subtract(b).equals(new RealFieldElement(25)));

        a = new RealFieldElement(15);
        b = new RealFieldElement(30);
        assertTrue(a.subtract(b).equals(new RealFieldElement(-15)));
    }

    @Test
    public void testMultiplication() {
        RealFieldElement a = new RealFieldElement(24);
        RealFieldElement b = new RealFieldElement(19);
        assertTrue(a.multiply(b).equals(new RealFieldElement(456)));
    }

    @Test
    public void testPow() {
        RealFieldElement a = new RealFieldElement(17);
        assertTrue(a.pow(3).equals(new RealFieldElement(4913)));
        RealFieldElement b = new RealFieldElement(5);
        RealFieldElement c = new RealFieldElement(18);
        assertTrue(b.pow(5).multiply(c).equals(new RealFieldElement(56250)));
    }

    @Test
    public void testDivision() {
        RealFieldElement a = new RealFieldElement(3);
        RealFieldElement b = new RealFieldElement(24);
        assertTrue(a.divide(b).equals(new RealFieldElement(0.125)));

        RealFieldElement c = new RealFieldElement(17);
        assertTrue(c.pow(-3).equals(new RealFieldElement(0.000203542)));

        RealFieldElement d = new RealFieldElement(4);
        RealFieldElement e = new RealFieldElement(11);
        assertTrue(d.pow(-4).multiply(e).equals(new RealFieldElement(0.04296875)));
    }
}
