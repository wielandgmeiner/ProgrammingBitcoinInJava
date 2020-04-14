package ecc;

import java.math.BigInteger;

import static util.S256Util.*;

public class S256Point extends Point<S256FieldElement> {

    public S256Point(String x, String y) {
        this(
            new S256FieldElement(new BigInteger(x, 16)),
            new S256FieldElement(new BigInteger(y, 16))
        );
    }

    public S256Point(S256FieldElement x, S256FieldElement y) {
        super(x, y, A, B);
    }


}
