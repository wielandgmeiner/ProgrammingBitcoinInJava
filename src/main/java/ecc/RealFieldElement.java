package ecc;

import java.util.Objects;

public class RealFieldElement implements FieldElement {

    // would probably better with BigDecimal instead of Double
    private Double num;

    private final Double THRESHOLD = 0.0001;

    public RealFieldElement(Double num) {

        if (num == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": argument must not be null");
        }
        this.num = num;
    }

    public RealFieldElement(Integer num) {

        if (num == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": argument must not be null");
        }
        this.num = Double.valueOf(num);
    }

    @Override
    public FieldElement add(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": argument must not be null");
        }
        if (that.getClass() != RealFieldElement.class) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type RealFieldElement");
        }
        RealFieldElement rfe = (RealFieldElement) that;
        Double res = this.num + rfe.getNum();
        return new RealFieldElement(res);
    }

    @Override
    public FieldElement subtract(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": argument must not be null");
        }
        if (that.getClass() != RealFieldElement.class) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type RealFieldElement");
        }
        RealFieldElement rfe = (RealFieldElement) that;
        Double res = this.num - rfe.getNum();
        return new RealFieldElement(res);
    }

    @Override
    public FieldElement multiply(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": argument must not be null");
        }
        if (that.getClass() != RealFieldElement.class) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type RealFieldElement");
        }
        RealFieldElement rfe = (RealFieldElement) that;
        Double res = this.num * rfe.getNum();
        return new RealFieldElement(res);
    }

    @Override
    public FieldElement divide(FieldElement that) {

        if (that == null) {
            throw new NullPointerException(getClass().getCanonicalName()
                    + ": argument must not be null");
        }
        if (that.getClass() != RealFieldElement.class) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Argument must be of type RealFieldElement");
        }
        RealFieldElement rfe = (RealFieldElement) that;
        if (rfe.equals(new RealFieldElement(0))) {
            throw new IllegalArgumentException(getClass().getCanonicalName()
                    + ": Cannot divide by Zero");
        }
        Double quotient = this.num / rfe.getNum();
        return new RealFieldElement(quotient);
    }

    @Override
    public FieldElement pow(long exp) {
        Double num = Math.pow(this.num, exp);
        return new RealFieldElement(num);
    }

    @Override
    public FieldElement valueOf(long val) {
        return new RealFieldElement(Double.valueOf(val));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealFieldElement that = (RealFieldElement) o;
        return Math.abs(this.num - that.getNum()) < THRESHOLD;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public boolean isZero() {
        return this.num == 0;
    }

    @Override
    public String toString() {
        return "RealFieldElement_(" + num + ")";
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }
}


