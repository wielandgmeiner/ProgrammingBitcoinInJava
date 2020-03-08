package ecc;


import org.junit.Test;

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
                    .collect(Collectors.toList())
                    .stream()
                    .map( x -> x*k % prime)
                    .sorted()
                    .collect(Collectors.toList()));
        }
    }
}
