package ecc;

import java.math.BigInteger;

import static util.S256Util.*;

public class S256Point extends Point<S256FieldElement> {

    public S256Point(S256FieldElement x, S256FieldElement y) {
        super(x, y, A, B);
    }

    // infinity
    public S256Point() {
        super(A, B);
    }

    // FIXME why doesn't overriding work? Problem with generics obviously
    // expected: ecc.S256Point<Point(Infinity)> but was: ecc.Point<Point(Infinity)>
    //java.lang.AssertionError: expected: ecc.S256Point<Point(Infinity)> but was: ecc.Point<Point(Infinity)>
    @Override
    public S256Point rmul(BigInteger scalar) {
        BigInteger coef = scalar.mod(N);
        S256Point current = this;
        S256Point result = new S256Point();
        while (coef.compareTo(BigInteger.ZERO) != 0) {
            if (coef.and(BigInteger.ONE).compareTo(BigInteger.ZERO) != 0) {
                result = (S256Point) result.add(current);
            }
            current = (S256Point) current.add(current);
            coef = coef.shiftRight(1);
        }
        return result;
//        return super.rmul(coefficient);
    }

    @Override
    public String toString() {
        if (getX() == null || getY() == null) {
            return "S256Point(Infinity)";
        }
        return "S256Point(" + getX() + "," + getY() + ")_" + A + "_" + B;
    }
}
