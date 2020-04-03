package ecc;


import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testRealFieldPointIsOnCurve() {
        Point<RealFieldElement> p = new Point<RealFieldElement>(
                new RealFieldElement(-1), new RealFieldElement(1),
                new RealFieldElement(5),new RealFieldElement(7));
        System.out.println(p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRealFieldPointIsNotOnCurve() {
        Point p = new Point<RealFieldElement>(
                new RealFieldElement(-1), new RealFieldElement(2),
                new RealFieldElement(5), new RealFieldElement(7));
    }

    @Test
    public void testRealFieldCreatePointAtInfinity() {
        Point inf = new Point<RealFieldElement>(
                new RealFieldElement(5),new RealFieldElement(7));
        System.out.println(inf);
    }

    @Test
    public void testNotEquals() {
        Point a = new Point<RealFieldElement>(
                new RealFieldElement(3), new RealFieldElement(-7),
                new RealFieldElement(5), new RealFieldElement(7)
                );
        Point b = new Point<RealFieldElement>(
                new RealFieldElement(18), new RealFieldElement(77),
                new RealFieldElement(5), new RealFieldElement(7)
                );
        assertTrue( ! a.equals(b));
        assertFalse( ! a.equals(a));
    }

    @Test
    public void testAdd0() {
        Point p1 = new Point<RealFieldElement>(
                new RealFieldElement(-1), new RealFieldElement(-1),
                new RealFieldElement(5), new RealFieldElement(7));
        Point p2 = new Point<RealFieldElement>(
                new RealFieldElement(-1), new RealFieldElement(1),
                new RealFieldElement(5), new RealFieldElement(7));
        assertEquals(p1, p1.add(new Point<RealFieldElement>(
                new RealFieldElement(5), new RealFieldElement(7))));
        assertEquals(p2, (new Point<RealFieldElement>(
                new RealFieldElement(5), new RealFieldElement(7))).add(p2));
        assertEquals(new Point<RealFieldElement>(
                new RealFieldElement(5), new RealFieldElement(7)), p1.add(p2));
    }

    @Test
    public void testAdd1() {
        Point a = new Point<RealFieldElement>(
                new RealFieldElement(3), new RealFieldElement(7),
                new RealFieldElement(5), new RealFieldElement(7));
        Point b = new Point<RealFieldElement>(
                new RealFieldElement(-1), new RealFieldElement(-1),
                new RealFieldElement(5), new RealFieldElement(7));
        assertEquals(new Point<RealFieldElement>(
                new RealFieldElement(2), new RealFieldElement(-5),
                new RealFieldElement(5), new RealFieldElement(7)),
                a.add(b));
    }

    @Test
    public void testAdd2() {
        Point a = new Point<RealFieldElement>(
                new RealFieldElement(-1), new RealFieldElement(-1),
                new RealFieldElement(5), new RealFieldElement(7));
        assertEquals(new Point<RealFieldElement>(
                new RealFieldElement(18), new RealFieldElement(77),
                new RealFieldElement(5), new RealFieldElement(7)),
                a.add(a));
    }
}
