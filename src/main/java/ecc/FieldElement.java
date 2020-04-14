package ecc;

public interface FieldElement {

    FieldElement ZERO = null;

    FieldElement add(FieldElement that);

    FieldElement subtract(FieldElement that);

    FieldElement multiply(FieldElement that);

    FieldElement divide(FieldElement that);

    FieldElement pow(long exp);

    FieldElement valueOf(long val);

    Number getNum();

    @Override
    String toString();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    boolean isZero();
}
