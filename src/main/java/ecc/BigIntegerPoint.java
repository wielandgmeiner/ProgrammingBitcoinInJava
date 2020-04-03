package ecc;

import java.math.BigInteger;
import java.util.Objects;

// this is bs, just works because Jimmy's examples are with nice whole numbers
// this should have been implemented with BigDecimal in the first place
public class BigIntegerPoint {

    private BigInteger x;
    private BigInteger y;
    private BigInteger a;
    private BigInteger b;

    public BigIntegerPoint(BigInteger x, BigInteger y, BigInteger a, BigInteger b) {

        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;

        if (a == null || b == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": curve parameter must not be null");
        }
        if (x == null && y == null) {
            // Point at infinity
            return;
        }
        if (y.pow(2).compareTo(x.pow(3).add(a.multiply(x)).add(b)) != 0) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + String.format(": point (%d,%d) is not on the curve", x, y));
        }
    }

    // Point at infinity
    public BigIntegerPoint(long a, long b) {
        this(null, null, BigInteger.valueOf(a), BigInteger.valueOf(b));
    }

    public BigIntegerPoint(long x, long y, long a, long b) {
        this(BigInteger.valueOf(x), BigInteger.valueOf(y), BigInteger.valueOf(a), BigInteger.valueOf(b));
    }

    public BigIntegerPoint add(BigIntegerPoint that) {

        if ( ! this.a.equals(that.getA()) || ! this.b.equals(that.getB())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Points must be on the same curve");
        }
        if (this.x == null) {
            return that;
        }
        if (that.x == null) {
            return this;
        }
        if (this.x.compareTo(that.getX()) == 0 && this.y.compareTo(that.getY()) != 0) {
            return new BigIntegerPoint(this.a.longValue(), this.b.longValue());
        }
        if (this.x.compareTo(that.getX()) != 0) {
            BigInteger slope = (that.getY().subtract(this.y)).divide(that.getX().subtract(this.x));
            BigInteger x3 = slope.pow(2).subtract(this.x).subtract(that.getX());
            BigInteger y3 = slope.multiply(this.x.subtract(x3)).subtract(this.y);
            return new BigIntegerPoint(x3, y3, this.a, this.b);
        }
        // tangent without further section
        if (this.equals(that) && this.y.compareTo(BigInteger.ZERO) == 0) {
            return new BigIntegerPoint(this.a.longValue(), this.b.longValue());
        }
        if (this.equals(that)) {
            BigInteger slope = (this.x.pow(2).multiply(BigInteger.valueOf(3)).add(this.a))
                    .divide(this.y.multiply(BigInteger.valueOf(2)));
            BigInteger x3 = slope.pow(2).subtract(this.x.multiply(BigInteger.valueOf(2)));
            BigInteger y3 = (slope.multiply(this.x.subtract(x3))).subtract(this.y);
            return new BigIntegerPoint(x3, y3, this.a, this.b);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BigIntegerPoint point = (BigIntegerPoint) o;
        return Objects.equals(a, point.a) &&
                Objects.equals(b, point.b) &&
                Objects.equals(x, point.x) &&
                Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, x, y);
    }

    @Override
    public String toString() {
        if (x == null || y == null) {
            return "Point(Infinity)";
        }
        return "Point(" + x + "," + y + ")_" + a + "_" + b;
    }

    public BigInteger getA() {
        return a;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    public BigInteger getB() {
        return b;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }
}


