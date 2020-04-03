package util;

import ecc.FiniteFieldElement;
import ecc.Point;
import ecc.S256FieldElement;
import ecc.S256Point;

import java.math.BigInteger;

/*
 * Constants for the Curve secp256k1
 */
public final class S256Util {
    // prime
    public static BigInteger        P
            = BigInteger.valueOf(2).pow(256)
            .subtract(BigInteger.valueOf(2).pow(32)).subtract(BigInteger.valueOf(977));
    // order of the curve
    public static BigInteger        N
            = new BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 16);
    // secp256k1 parameter
    public static S256FieldElement  A
            = new S256FieldElement(BigInteger.ZERO);
    // secp256k1 parameter
    public static S256FieldElement  B
            = new S256FieldElement(BigInteger.valueOf(7));
    // generator point
    public static S256Point         G
            = new S256Point(
            new S256FieldElement(
                    new BigInteger("79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798", 16)),
            new S256FieldElement(
                    new BigInteger("483ada7726a3c4655da4fbfc0e1108a8fd17b448a68554199c47d08ffb10d4b8", 16))
    );
    // point at infinity
    public static S256Point         INFINITY
            = new S256Point(null, null);
}
