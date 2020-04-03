package ecc;


import org.junit.Test;
import util.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ECCTest {

    @Test
    public void testFiniteFieldPointIsOnCurve() {
        Point<FiniteFieldElement> p = new Point<>(
                new FiniteFieldElement(192, 223), new FiniteFieldElement(105, 223),
                new FiniteFieldElement(0, 223), new FiniteFieldElement(7, 223)
        );
        System.out.println(p);
    }

    @Test
    public void testOnCurve() {
        long prime = 223;
        FieldElement a = new FiniteFieldElement(0, prime);
        FieldElement b = new FiniteFieldElement(7, prime);
        List<Pair<Long, Long>> validPoints = new ArrayList<Pair<Long, Long>>(List.of(
                new Pair<>(192L, 105L),
                new Pair<>(17L, 56L),
                new Pair<>(1L, 193L)));
        List<Pair<Long, Long>> invalidPoints = new ArrayList<>(List.of(
                new Pair<>(200L, 19L),
                new Pair<>(42L, 99L)));
        for (Pair<Long, Long> pair : validPoints) {
            FieldElement x = new FiniteFieldElement(pair.getLeft(), prime);
            FieldElement y = new FiniteFieldElement(pair.getRight(), prime);
            Point<FieldElement> p = new Point<>(x, y, a, b);
        }
        for (Pair<Long, Long> pair : invalidPoints) {
            FieldElement x = new FiniteFieldElement(pair.getLeft(), prime);
            FieldElement y = new FiniteFieldElement(pair.getRight(), prime);
            IllegalArgumentException thrown = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Point<>(x, y, a, b));
            assertEquals(
                    "ecc.Point: point (" + x + "," + y + ") is not on the curve",
                    thrown.getMessage());
        }
    }

    @Test
    public void testFiniteFieldPointAddition() {
        long prime = 223;
        FieldElement a = new FiniteFieldElement(0, prime);
        FieldElement b = new FiniteFieldElement(7, prime);
        FieldElement x1 = new FiniteFieldElement(192, prime);
        FieldElement y1 = new FiniteFieldElement(105, prime);
        FieldElement x2 = new FiniteFieldElement(17, prime);
        FieldElement y2 = new FiniteFieldElement(56, prime);
        Point<FieldElement> p1 = new Point<FieldElement>(x1, y1, a, b);
        Point<FieldElement> p2 = new Point<FieldElement>(x2, y2, a, b);
        System.out.print(p1.add(p2));
        FieldElement x3 = new FiniteFieldElement(170, prime);
        FieldElement y3 = new FiniteFieldElement(142, prime);
        Point<FieldElement> sum = new Point<>(x3, y3, a, b);
        assertEquals(sum, p1.add(p2));
    }

    @Test
    public void testAdd() {
        long prime = 223;
        FieldElement a = new FiniteFieldElement(0, prime);
        FieldElement b = new FiniteFieldElement(7, prime);
        List<List<Pair<Long, Long>>> additions =
                new ArrayList<>(List.of(
                        new ArrayList<>(List.of(
                                new Pair<>(192L, 105L),
                                new Pair<>(17L, 56L),
                                new Pair<>(170L, 142L)
                        )),
                        new ArrayList<>(List.of(
                                new Pair<>(47L, 71L),
                                new Pair<>(117L, 141L),
                                new Pair<>(60L, 139L)
                        )),
                        new ArrayList<>(List.of(
                                new Pair<>(143L, 98L),
                                new Pair<>(76L, 66L),
                                new Pair<>(47L, 71L)
                        ))
                ));
        for (List<Pair<Long, Long>> c : additions) {
            FieldElement x1 = new FiniteFieldElement(c.get(0).getLeft(), prime);
            FieldElement y1 = new FiniteFieldElement(c.get(0).getRight(), prime);
            FieldElement x2 = new FiniteFieldElement(c.get(1).getLeft(), prime);
            FieldElement y2 = new FiniteFieldElement(c.get(1).getRight(), prime);
            FieldElement x3 = new FiniteFieldElement(c.get(2).getLeft(), prime);
            FieldElement y3 = new FiniteFieldElement(c.get(2).getRight(), prime);
            Point<FieldElement> p1 = new Point<>(x1, y1, a, b);
            Point<FieldElement> p2 = new Point<>(x2, y2, a, b);
            Point<FieldElement> p3 = new Point<>(x3, y3, a, b);
            assertEquals(p3, p1.add(p2));
        }
    }

    @Test
    public void testRmul() {
        long prime = 223;
        FieldElement a = new FiniteFieldElement(0, prime);
        FieldElement b = new FiniteFieldElement(7, prime);
        List<List<Long>> multiplications =
                // coefficient, x1, y1, x2, y2
                new ArrayList<>(List.of(
                        Stream.of(2L, 192L, 105L, 49L, 71L).collect(Collectors.toList()),
                        Stream.of(2L, 143L, 98L, 64L, 168L).collect(Collectors.toList()),
                        Stream.of(2L, 47L, 71L, 36L, 111L).collect(Collectors.toList()),
                        Stream.of(4L, 47L, 71L, 194L, 51L).collect(Collectors.toList()),
                        Stream.of(8L, 47L, 71L, 116L, 55L).collect(Collectors.toList()),
                        Stream.of(21L, 47L, 71L, null, null).collect(Collectors.toList())
                ));
        for (List<Long> c : multiplications) {
            long scalar = c.get(0);
            FieldElement x1 = new FiniteFieldElement(c.get(1), prime);
            FieldElement y1 = new FiniteFieldElement(c.get(2), prime);
            Point<FieldElement> p1 = new Point<>(x1, y1, a, b);
            // initialize the second point based on whether it's the point at infinity
            Point<FieldElement> p2 = null;
            if (c.get(3) == null) { // x2 == null
                p2 = new Point<>(a, b);
            } else {
                FieldElement x2 = new FiniteFieldElement(c.get(3), prime);
                FieldElement y2 = new FiniteFieldElement(c.get(4), prime);
                p2 = new Point<>(x2, y2, a, b);
            }
            assertEquals(p2, p1.rmul(BigInteger.valueOf(scalar)));
        }
    }

    @Test
    public void testExercise3_2() {
        long prime = 223;
        FieldElement a = new FiniteFieldElement(0, prime);
        FieldElement b = new FiniteFieldElement(7, prime);
        List<Pair<Pair<Long, Long>, Pair<Long, Long>>> coordinates =
                new ArrayList<>(List.of(
                        new Pair<>(
                                new Pair<>(170L, 142L),
                                new Pair<>(60L, 139L)),
                        new Pair<>(
                                new Pair<>(47L, 71L),
                                new Pair<>(17L, 56L)),
                        new Pair<>(
                                new Pair<>(143L, 98L),
                                new Pair<>(76L, 66L))));
        for (Pair<Pair<Long, Long>, Pair<Long, Long>> p : coordinates) {
            FieldElement x1 = new FiniteFieldElement(p.getLeft().getLeft(), prime);
            FieldElement y1 = new FiniteFieldElement(p.getLeft().getRight(), prime);
            FieldElement x2 = new FiniteFieldElement(p.getRight().getLeft(), prime);
            FieldElement y2 = new FiniteFieldElement(p.getRight().getRight(), prime);
            Point<FieldElement> p1 = new Point<>(x1, y1, a, b);
            Point<FieldElement> p2 = new Point<>(x2, y2, a, b);
            System.out.println(p1.add(p2));
        }
    }

    @Test
    public void testExercise3_5() {
        long prime = 223;
        FieldElement a = new FiniteFieldElement(0, prime);
        FieldElement b = new FiniteFieldElement(7, prime);
        FieldElement x = new FiniteFieldElement(15, prime);
        FieldElement y = new FiniteFieldElement(86, prime);
        Point<FieldElement> p = new Point<>(x, y, a, b);
        Point<FieldElement> inf = new Point<>(a, b);
        long counter = 1;
        Point<FieldElement> product = p;
        while ( ! product.equals(inf)) {
            product = product.add(p);
            counter++;
        }
        assertEquals(7, counter);
    }

    @Test
    public void testVerifyGeneratorpointIsOnCurve() {
        BigInteger gx = new BigInteger("79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798", 16);
        BigInteger gy = new BigInteger("483ada7726a3c4655da4fbfc0e1108a8fd17b448a68554199c47d08ffb10d4b8", 16);
        BigInteger p = BigInteger.valueOf(2).pow(256)
                .subtract(BigInteger.valueOf(2).pow(32)).subtract(BigInteger.valueOf(977));
        System.out.println("p: " + p.toString(10));
        System.out.println("lhs: " + gy.modPow(BigInteger.valueOf(2), p));
        System.out.println("rhs: " + gx.pow(3).add(BigInteger.valueOf(7)).mod(p));
        boolean onCurve = gy.modPow(BigInteger.valueOf(2), p).equals(gx.pow(3).add(BigInteger.valueOf(7)).mod(p));
        assertTrue(onCurve);
    }

    @Test
    public void testVerifyOrderOfGeneratorpointIsN() {
        BigInteger gx = new BigInteger("79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798", 16);
        BigInteger gy = new BigInteger("483ada7726a3c4655da4fbfc0e1108a8fd17b448a68554199c47d08ffb10d4b8", 16);
        BigInteger p = BigInteger.valueOf(2).pow(256)
                .subtract(BigInteger.valueOf(2).pow(32)).subtract(BigInteger.valueOf(977));
        BigInteger n = new BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 16);
        FieldElement x = new FiniteFieldElement(gx, p);
        FieldElement y = new FiniteFieldElement(gy, p);
        FieldElement seven = new FiniteFieldElement(BigInteger.valueOf(7), p);
        FieldElement zero = new FiniteFieldElement(BigInteger.ZERO, p);
        Point<FieldElement> G = new Point<>(x, y, zero, seven);
        Point<FieldElement> inf = new Point<>(zero, seven);
        Point<FieldElement> gOrderN = G.rmul(n);
        System.out.println("n*G: " + gOrderN);
        assertEquals(inf, gOrderN);
    }

}
