package ecc;


import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FieldElementTest {

    @Test
    public void testNotEquals() {
        FieldElement a = new FieldElement(2, 31);
        FieldElement b = new FieldElement(2, 31);
        FieldElement c = new FieldElement(15, 31);
        assertTrue(a.equals(b));
        assertTrue(a.notEquals(c));
        assertFalse(a.notEquals(b));
    }

    @Test
    public void testAddition() {
        FieldElement a = new FieldElement(7, 13);
        FieldElement b = new FieldElement(12, 13);
        FieldElement c = new FieldElement(6, 13);
        assertTrue(a.add(b).equals(c));

        a = new FieldElement(2, 31);
        b = new FieldElement(15, 31);
        assertTrue(a.add(b).equals(new FieldElement(17, 31)));

        a = new FieldElement(17, 31);
        b = new FieldElement(21, 31);
        assertTrue(a.add(b).equals(new FieldElement(7, 31)));
    }

    @Test
    public void testSubtraction() {
        FieldElement a = new FieldElement(29, 31);
        FieldElement b = new FieldElement(4, 31);
        assertTrue(a.subtract(b).equals(new FieldElement(25, 31)));

        a = new FieldElement(15, 31);
        b = new FieldElement(30, 31);
        assertTrue(a.subtract(b).equals(new FieldElement(16, 31)));
    }

    @Test
    public void testMultiplication() {
        FieldElement a = new FieldElement(24, 31);
        FieldElement b = new FieldElement(19, 31);
        assertTrue(a.multiply(b).equals(new FieldElement(22, 31)));
    }

    @Test
    public void testPow() {
        FieldElement a = new FieldElement(17, 31);
        assertTrue(a.pow(3).equals(new FieldElement(15, 31)));
        FieldElement b = new FieldElement(5, 31);
        FieldElement c = new FieldElement(18, 31);
        assertTrue(b.pow(5).multiply(c).equals(new FieldElement(16, 31)));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddTwoElementsFromDifferentFields() {
        FieldElement a = new FieldElement(7, 13);
        FieldElement b = new FieldElement(12, 11);
        a.add(b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrimeArgumentInConstructorIsNotPrime() {
        FieldElement a = new FieldElement(7, 10);
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
