package ecc;

import java.math.BigInteger;
import java.util.Objects;

import static util.S256Util.P;

public class S256FieldElement extends FiniteFieldElement {

    public S256FieldElement(BigInteger num) {
        super(num, P);
    }

    public S256FieldElement(long num) {
        super(num, P.longValue());
    }

    @Override
    public FieldElement add(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": arguments must not be null");
        }
        if ( ! (that instanceof S256FieldElement)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type S256FieldElement");
        }
        S256FieldElement ffe = (S256FieldElement) that;
        if ( ! this.getPrime().equals(ffe.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot add two numbers in different Fields");
        }
        BigInteger num = (this.getNum().add(ffe.getNum()).mod(this.getPrime()));
        return new S256FieldElement(num);
    }

    @Override
    public FieldElement subtract(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": arguments must not be null");
        }
        if ( ! (that instanceof S256FieldElement)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type S256FieldElement");
        }
        S256FieldElement ffe = (S256FieldElement) that;
        if ( ! this.getPrime().equals(ffe.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot subtract two numbers in different Fields");
        }
        BigInteger num = (this.getNum().subtract(ffe.getNum()).mod(this.getPrime()));
        return new S256FieldElement(num);
    }

    @Override
    public FieldElement multiply(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": arguments must not be null");
        }
        if ( ! (that instanceof S256FieldElement)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type S256FieldElement");
        }
        S256FieldElement ffe = (S256FieldElement) that;
        if ( ! this.getPrime().equals(ffe.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot multiply two numbers in different Fields");
        }
        BigInteger num = (this.getNum().multiply(ffe.getNum()).mod(this.getPrime()));
        return new S256FieldElement(num);
    }

    @Override
    public FieldElement divide(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": arguments must not be null");
        }
        if ( ! (that instanceof S256FieldElement)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type S256FieldElement");
        }
        S256FieldElement ffe = (S256FieldElement) that;
        if ( ! this.getPrime().equals(ffe.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot divide two numbers in different Fields");
        }
        BigInteger exp = this.getPrime().subtract(BigInteger.valueOf(2));
        BigInteger divisor = ffe.getNum().modPow(exp, this.getPrime());
        BigInteger quotient = this.getNum().multiply(divisor).mod(this.getPrime());
        return new S256FieldElement(quotient);
    }

    @Override
    public FieldElement pow(long exp) {
        BigInteger num = this.getNum().modPow(BigInteger.valueOf(exp), this.getPrime());
        return new S256FieldElement(num);
    }

    @Override
    public FieldElement valueOf(long val) {
        return new S256FieldElement(BigInteger.valueOf(val));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        S256FieldElement that = (S256FieldElement) o;
        return getNum().equals(that.getNum()) &&
                getPrime().equals(that.getPrime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNum(), getPrime());
    }

    @Override
    public boolean isZero() {
        return this.getNum().equals(0);
    }

    @Override
    public String toString() {
        return String.format("S256FieldElement_(%064d)", this.getNum());
    }
}
