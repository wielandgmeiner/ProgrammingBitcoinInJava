package ecc;

import java.math.BigInteger;
import java.util.Objects;

public class FiniteFieldElement implements FieldElement {

    private BigInteger num;
    private BigInteger prime;

    public FiniteFieldElement(BigInteger num, BigInteger prime) {

        if (num == null || prime == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": arguments must not be null");
        }
        if (num.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": argument num must not be smaller than 0");
        }
        if ( ! prime.isProbablePrime(16)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": prime must be prime");
        }
        if (num.compareTo(prime) >= 0) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": num must be greater than prime");
        }

        this.num = num;
        this.prime = prime;
    }

    public FiniteFieldElement(long num, long prime) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(prime));
    }

    @Override
    public FieldElement add(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": arguments must not be null");
        }
        if ( ! (that instanceof FiniteFieldElement)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type FiniteFieldElement");
        }
        FiniteFieldElement ffe = (FiniteFieldElement) that;
        if ( ! this.prime.equals(ffe.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot add two numbers in different Fields");
        }
        BigInteger num = (this.num.add(ffe.getNum()).mod(this.prime));
        return new FiniteFieldElement(num, this.prime);
    }

    @Override
    public FieldElement subtract(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": arguments must not be null");
        }
        if ( ! (that instanceof FiniteFieldElement)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type FiniteFieldElement");
        }
        FiniteFieldElement ffe = (FiniteFieldElement) that;
        if ( ! this.prime.equals(ffe.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot subtract two numbers in different Fields");
        }
        BigInteger num = (this.num.subtract(ffe.getNum()).mod(this.prime));
        return new FiniteFieldElement(num, this.prime);
    }

    @Override
    public FieldElement multiply(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": arguments must not be null");
        }
        if ( ! (that instanceof FiniteFieldElement)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type FiniteFieldElement");
        }
        FiniteFieldElement ffe = (FiniteFieldElement) that;
        if ( ! this.prime.equals(ffe.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot multiply two numbers in different Fields");
        }
        BigInteger num = (this.num.multiply(ffe.getNum()).mod(this.prime));
        return new FiniteFieldElement(num, this.prime);
    }

    @Override
    public FieldElement divide(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": arguments must not be null");
        }
        if ( ! (that instanceof FiniteFieldElement)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type FiniteFieldElement");
        }
        FiniteFieldElement ffe = (FiniteFieldElement) that;
        if ( ! this.prime.equals(ffe.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot divide two numbers in different Fields");
        }
        BigInteger exp = this.getPrime().subtract(BigInteger.valueOf(2));
        BigInteger divisor = ffe.getNum().modPow(exp, this.prime);
        BigInteger quotient = this.num.multiply(divisor).mod(this.prime);
        return new FiniteFieldElement(quotient, this.prime);
    }

    @Override
    public FieldElement pow(long exp) {
        BigInteger num = this.num.modPow(BigInteger.valueOf(exp), this.prime);
        return new FiniteFieldElement(num, this.prime);
    }

    @Override
    public FieldElement valueOf(long val) {
        return new FiniteFieldElement(BigInteger.valueOf(val), this.prime);
    }

    @Override
    public String toString() {
        return "FiniteFieldElement_" + prime.toString(16) + "(" + num.toString(16) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiniteFieldElement that = (FiniteFieldElement) o;
        return num.equals(that.num) &&
                prime.equals(that.prime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, prime);
    }

    @Override
    public boolean isZero() {
        return this.num.equals(0);
    }

    @Override
    public BigInteger getNum() {
        return num;
    }

    public void setNum(BigInteger num) {
        this.num = num;
    }

    public BigInteger getPrime() {
        return prime;
    }

    public void setPrime(BigInteger prime) {
        this.prime = prime;
    }
}


