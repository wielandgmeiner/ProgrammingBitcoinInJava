package ecc;

import java.math.BigInteger;
import java.util.Objects;

public class Point<T extends FieldElement> {

    private T x;
    private T y;
    private T a;
    private T b;

    public Point(T x, T y, T a, T b) {

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
        if ( ! y.pow(2).equals(x.pow(3).add(a.multiply(x)).add(b))) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": point (" + x + "," + y + ") is not on the curve");
        }
    }

    // Point at infinity
    public Point(T a, T b) {
        this(null, null, a, b);
    }

    public Point add(Point that) {

        if ( ! this.a.equals(that.getA()) || ! this.b.equals(that.getB())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Points must be on the same curve");
        }
        // this is point at infinity
        if (this.x == null) {
            return that;
        }
        // that is point at infinity
        if (that.x == null) {
            return this;
        }
        // same x means points -> this.y == - that.y -> add yields point at infinity
        if (this.x.equals(that.getX()) && ! this.y.equals(that.getY())) {
            return new Point(this.a, this.b);
        }
        // points are different, regular case
        if ( ! this.x.equals(that.getX())) {
            FieldElement slope = (that.getY().subtract(this.y)).divide(that.getX().subtract(this.x));
            FieldElement x3 = slope.pow(2).subtract(this.x).subtract(that.getX());
            FieldElement y3 = slope.multiply(this.x.subtract(x3)).subtract(this.y);
            return new Point(x3, y3, this.a, this.b);
        }
        // tangent without further section
        if (this.equals(that) && this.y.isZero()) {
            return new Point(this.a, this.b);
        }
        // point added to itself
        if (this.equals(that)) {
            FieldElement slope = (this.x.pow(2).multiply(this.x.valueOf(3)).add(this.a))
                    .divide(this.y.multiply(this.x.valueOf(2)));
            FieldElement x3 = slope.pow(2).subtract(this.x.multiply(this.x.valueOf(2)));
            FieldElement y3 = (slope.multiply(this.x.subtract(x3))).subtract(this.y);
            return new Point(x3, y3, this.a, this.b);
        }
        return null;
    }

    public Point<T> rmul(BigInteger scalar) {
        BigInteger coef = scalar;
        Point<T> current = this;
        Point<T> result = new Point<>(this.a, this.b);
        while (coef.compareTo(BigInteger.ZERO) != 0) {
            if (coef.and(BigInteger.ONE).compareTo(BigInteger.ZERO) != 0) {
                result = result.add(current);
            }
            current = current.add(current);
            coef = coef.shiftRight(1);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point<T> point = (Point<T>) o;
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

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public T getB() {
        return b;
    }

    public void setB(T b) {
        this.b = b;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }
}


