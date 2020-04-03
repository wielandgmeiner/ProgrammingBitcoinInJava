package ecc;


import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class BigIntegerPointTest {

    @Test
    public void testPointIsOnCurve() {
        BigIntegerPoint p = new BigIntegerPoint(-1, -1, 5, 7);
        System.out.println(p);
    }

    @Test
    public void testOddPointIsOnCurve() {
        System.out.println(6.08276253*6.08276253);
        System.out.println(3*3*3 + 1*3 + 7);
        assertTrue(Math.abs(Math.abs(6.08276253*6.08276253) - Math.abs(3*3*3 + 1*3 + 7)) < 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPointIsNotOnCurve() {
        BigIntegerPoint p = new BigIntegerPoint(-1, -2, 5, 7);
    }

    @Test
    public void testCreatePointAtInfinity() {
        BigIntegerPoint inf = new BigIntegerPoint(5, 7);
        System.out.println(inf);
    }

    @Test
    public void testNotEquals() {
        BigIntegerPoint a = new BigIntegerPoint(3, -7, 5, 7);
        BigIntegerPoint b = new BigIntegerPoint(18, 77, 5, 7);
        assertTrue( ! a.equals(b));
        assertFalse( ! a.equals(a));
    }

    @Test
    public void testAdd0() {
        BigIntegerPoint p1 = new BigIntegerPoint(-1, -1, 5, 7);
        BigIntegerPoint p2 = new BigIntegerPoint(-1, 1, 5, 7);
        assertEquals(p1, p1.add(new BigIntegerPoint(5, 7)));
        assertEquals(p2, (new BigIntegerPoint(5, 7)).add(p2));
        assertEquals(new BigIntegerPoint(5, 7), p1.add(p2));
    }

    @Test
    public void testAdd1() {
        BigIntegerPoint a = new BigIntegerPoint(3, 7, 5, 7);
        BigIntegerPoint b = new BigIntegerPoint(-1, -1, 5, 7);
        assertEquals(new BigIntegerPoint(2, -5, 5, 7), a.add(b));
    }

    @Test
    public void testAdd2() {
        BigIntegerPoint a = new BigIntegerPoint(-1, -1, 5, 7);
        assertEquals(new BigIntegerPoint(18, 77, 5, 7), a.add(a));
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
        BigIntegerPoint z = new BigIntegerPoint(x3, y3, a, b);
        System.out.println(z);
        assertEquals(new BigIntegerPoint(3, -7, a, b), z);
    }

    @Test
    public void fooTest() {
        //Method 1
        BigDecimal f1 = new BigDecimal("0.0");
        BigDecimal pointOne = new BigDecimal("0.1");
        for (int i = 1; i <= 11; i++) {
            f1 = f1.add(pointOne);
        }

        //Method 2
        BigDecimal f2 = new BigDecimal("0.1");
        BigDecimal eleven = new BigDecimal("11");
        f2 = f2.multiply(eleven);

        System.out.println("f1 = " + f1);
        System.out.println("f2 = " + f2);

        if (f1.compareTo(f2) == 0)
            System.out.println("f1 and f2 are equal using BigDecimal\n");
        else
            System.out.println("f1 and f2 are not equal using BigDecimal\n");

        System.out.println("pointOne / f1: " + pointOne.divide(f1, 20, RoundingMode.HALF_UP));
    }
}
