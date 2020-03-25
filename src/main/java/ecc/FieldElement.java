package ecc;

import java.math.BigInteger;
import java.util.Objects;

public class FieldElement {

    private BigInteger num;
    private BigInteger prime;

    public FieldElement(BigInteger num, BigInteger prime) {

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

    public FieldElement(long num, long prime) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(prime));
    }

    public boolean notEquals(Object o) {
        return ! equals(o);
    }

    public FieldElement add(Object o) {
        if (o == null || getClass() != o.getClass()) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": argument must be FieldElement");
        }
        FieldElement that = (FieldElement) o;
        if ( ! this.prime.equals(that.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot add two numbers in different Fields");
        }
        BigInteger num = (this.num.add(that.getNum()).mod(this.prime));
        return new FieldElement(num, this.prime);
    }

    public FieldElement subtract(Object o) {
        if (o == null || getClass() != o.getClass()) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": argument must be FieldElement");
        }
        FieldElement that = (FieldElement) o;
        if ( ! this.prime.equals(that.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot subtract two numbers in different Fields");
        }
        BigInteger num = (this.num.subtract(that.getNum()).mod(this.prime));
        return new FieldElement(num, this.prime);
    }

    public FieldElement multiply(Object o) {
        if (o == null || getClass() != o.getClass()) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": argument must be FieldElement");
        }
        FieldElement that = (FieldElement) o;
        if ( ! this.prime.equals(that.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot multiply two numbers in different Fields");
        }
        BigInteger num = (this.num.multiply(that.getNum()).mod(this.prime));
        return new FieldElement(num, this.prime);
    }

    public FieldElement pow(Object o) {
        if (o == null || ! (o instanceof Integer)) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": argument must be FieldElement");
        }
        int exp = (int) o;
        BigInteger num = this.num.modPow(BigInteger.valueOf(exp), this.prime);
        return new FieldElement(num, this.prime);
    }


    public FieldElement divide(Object o) {
        if (o == null || getClass() != o.getClass()) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": argument must be FieldElement");
        }
        FieldElement that = (FieldElement) o;
        if ( ! this.prime.equals(that.getPrime())) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot divide two numbers in different Fields");
        }
        BigInteger exp = this.getPrime().subtract(BigInteger.valueOf(2));
        BigInteger divisor = that.getNum().modPow(exp, this.prime);
        BigInteger quotient = this.num.multiply(divisor).mod(this.prime);
        return new FieldElement(quotient, this.prime);
    }

    @Override
    public String toString() {
        return "FieldElement{" +
                "num=" + num +
                ", prime=" + prime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldElement that = (FieldElement) o;
        return num.equals(that.num) &&
                prime.equals(that.prime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, prime);
    }

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


