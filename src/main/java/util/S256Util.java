package util;

import ecc.FieldElement;
import ecc.FiniteFieldElement;
import ecc.Point;
import ecc.S256FieldElement;

import java.math.BigInteger;

/*
 * Constants for the Curve secp256k1
 */
public final class S256Util {
    // prime
    public static BigInteger                P;
    // order of the curve
    public static BigInteger                N;
    // secp256k1 parameter
    public static S256FieldElement          A;
    // secp256k1 parameter
    public static S256FieldElement          B;
    // generator point
    public static Point<S256FieldElement>   G;
    // point at infinity
    public static Point<S256FieldElement>   INFINITY;

    static {
        P = BigInteger.valueOf(2).pow(256).subtract(BigInteger.valueOf(2).pow(32)).subtract(BigInteger.valueOf(977));
        N = new BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 16);
        A = new S256FieldElement(BigInteger.ZERO);
        B = new S256FieldElement(BigInteger.valueOf(7));
        G = new Point<>(
                new S256FieldElement(
                    new BigInteger("79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798", 16)),
                new S256FieldElement(
                    new BigInteger("483ada7726a3c4655da4fbfc0e1108a8fd17b448a68554199c47d08ffb10d4b8", 16)),
                A,
                B);
        INFINITY = new Point<>(null, null, A, B);
    }

    public static Point<S256FieldElement> s256FieldElementPoint(String x, String y) {
        BigInteger gx = new BigInteger(x, 16);
        BigInteger gy = new BigInteger(y, 16);
        S256FieldElement sx = new S256FieldElement(gx);
        S256FieldElement sy = new S256FieldElement(gy);
        Point<S256FieldElement> point = new Point<S256FieldElement>(sx, sy, A, B);
        return point;
    }
}
