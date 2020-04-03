package ecc;

import java.math.BigInteger;

import static util.S256Util.P;

public class S256FieldElement extends FiniteFieldElement {

    public S256FieldElement(BigInteger num) {
        super(num, P);
    }

    public S256FieldElement(long num) {
        super(num, P.longValue());
    }

    @Override
    public String toString() {
        return String.format("S256FieldElement_(%064d)", this.getNum());
    }
}
