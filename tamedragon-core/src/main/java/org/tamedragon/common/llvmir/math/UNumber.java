package org.tamedragon.common.llvmir.math;

import java.math.BigInteger;

public abstract class UNumber extends Number {
    /**
     * Get this number as a {@link BigInteger}. This is a convenience method for
     * calling <code>new BigInteger(toString())</code>
     */
    public BigInteger toBigInteger() {
        return new BigInteger(toString());
    }
}