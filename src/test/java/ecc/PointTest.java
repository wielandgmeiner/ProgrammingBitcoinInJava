package ecc;


import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testPointIsOnCurve() {
        Point p = new Point(-1, -1, 5, 7);
        System.out.println(p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPointIsNotOnCurve() {
        Point p = new Point(-1, -2, 5, 7);
    }

    @Test
    public void testCreatePointAtInfinity() {
        Point inf = new Point(5, 7);
        System.out.println(inf);
    }

    @Test
    public void testNotEquals() {
        Point a = new Point(3, -7, 5, 7);
        Point b = new Point(18, 77, 5, 7);
        assertTrue( ! a.equals(b));
        assertFalse( ! a.equals(a));
    }

    @Test
    public void testAdd0() {
        Point p1 = new Point(-1, -1, 5, 7);
        Point p2 = new Point(-1, 1, 5, 7);
        assertEquals(p1, p1.add(new Point(5, 7)));
        assertEquals(p2, (new Point(5, 7)).add(p2));
        assertEquals(new Point(5, 7), p1.add(p2));
    }

    @Test
    public void testAdd1() {
        Point a = new Point(3, 7, 5, 7);
        Point b = new Point(-1, -1, 5, 7);
        assertEquals(new Point(2, -5, 5, 7), a.add(b));
    }

    @Test
    public void testAdd2() {
        Point a = new Point(-1, -1, 5, 7);
        assertEquals(new Point(18, 77, 5, 7), a.add(a));
    }

    /*
     * Exercises
     */
    @Test
    public void exercise4() {
        // small numbers in this exercise, so we just use int
        int a = 5;
        int b = 7;
        int x1 = 2;
        int y1 = 5;
        int x2 = -1;
        int y2 = -1;

        // (x1,y1) + (x2,y2)
        // s = (y2 – y1)/(x2 – x1)
        // x3 = s^2 – x1 – x2
        // y3 = s(x1 – x3) – y1

        int s = (y2 - y1)/(x2 - x1);
        int x3 = s*s - x1 -x2;
        int y3 = s*(x1 - x3) - y1;
        Point z = new Point(x3, y3, a, b);
        System.out.println(z);
        assertEquals(new Point(3, -7, a, b), z);
    }
}
