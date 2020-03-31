package ecc;


import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FiniteFieldElementTest {

    @Test
    public void testNotEquals() {
        FiniteFieldElement a = new FiniteFieldElement(2, 31);
        FiniteFieldElement b = new FiniteFieldElement(2, 31);
        FiniteFieldElement c = new FiniteFieldElement(15, 31);
        assertTrue(a.equals(b));
        assertTrue( ! a.equals(c));
        assertFalse( ! a.equals(b));
    }

    @Test
    public void testAddition() {
        FiniteFieldElement a = new FiniteFieldElement(7, 13);
        FiniteFieldElement b = new FiniteFieldElement(12, 13);
        FiniteFieldElement c = new FiniteFieldElement(6, 13);
        assertTrue(a.add(b).equals(c));

        a = new FiniteFieldElement(2, 31);
        b = new FiniteFieldElement(15, 31);
        assertTrue(a.add(b).equals(new FiniteFieldElement(17, 31)));

        a = new FiniteFieldElement(17, 31);
        b = new FiniteFieldElement(21, 31);
        assertTrue(a.add(b).equals(new FiniteFieldElement(7, 31)));
    }

    @Test
    public void testSubtraction() {
        FiniteFieldElement a = new FiniteFieldElement(29, 31);
        FiniteFieldElement b = new FiniteFieldElement(4, 31);
        assertTrue(a.subtract(b).equals(new FiniteFieldElement(25, 31)));

        a = new FiniteFieldElement(15, 31);
        b = new FiniteFieldElement(30, 31);
        assertTrue(a.subtract(b).equals(new FiniteFieldElement(16, 31)));
    }

    @Test
    public void testMultiplication() {
        FiniteFieldElement a = new FiniteFieldElement(24, 31);
        FiniteFieldElement b = new FiniteFieldElement(19, 31);
        assertTrue(a.multiply(b).equals(new FiniteFieldElement(22, 31)));
    }

    @Test
    public void testPow() {
        FiniteFieldElement a = new FiniteFieldElement(17, 31);
        assertTrue(a.pow(3).equals(new FiniteFieldElement(15, 31)));
        FiniteFieldElement b = new FiniteFieldElement(5, 31);
        FiniteFieldElement c = new FiniteFieldElement(18, 31);
        assertTrue(b.pow(5).multiply(c).equals(new FiniteFieldElement(16, 31)));
    }

    @Test
    public void testDivision() {
        FiniteFieldElement a = new FiniteFieldElement(3, 31);
        FiniteFieldElement b = new FiniteFieldElement(24, 31);
        assertTrue(a.divide(b).equals(new FiniteFieldElement(4, 31)));

        FiniteFieldElement c = new FiniteFieldElement(17, 31);
        assertTrue(c.pow(-3).equals(new FiniteFieldElement(29, 31)));

        FiniteFieldElement d = new FiniteFieldElement(4, 31);
        FiniteFieldElement e = new FiniteFieldElement(11, 31);
        assertTrue(d.pow(-4).multiply(e).equals(new FiniteFieldElement(13, 31)));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddTwoElementsFromDifferentFields() {
        FiniteFieldElement a = new FiniteFieldElement(7, 13);
        FiniteFieldElement b = new FiniteFieldElement(12, 11);
        a.add(b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrimeArgumentInConstructorIsNotPrime() {
        FiniteFieldElement a = new FiniteFieldElement(7, 10);
    }

    /*
     * Exercises
     */
    @Test
    public void exercise5() {
        int prime = 19;
        for (int k : new ArrayList<Integer>(List.of(1,3,7,13,18))) {
            System.out.println(
                    IntStream.range(0, prime)
                    .boxed()
                    .map( x -> x*k % prime)
                    .sorted()
                    .collect(Collectors.toList())
            );
        }
    }

    @Test
    public void exercise7() {
        for (int prime : new ArrayList<Integer>(List.of(7, 11, 17, 31, 43))) {
            System.out.println(
                    IntStream.range(1, prime)
                    .boxed()
                    .map( x -> BigInteger.valueOf(x).pow(prime - 1).mod(BigInteger.valueOf(prime)))
                    .sorted()
                    .collect(Collectors.toList())
                    );
        }
    }
}
