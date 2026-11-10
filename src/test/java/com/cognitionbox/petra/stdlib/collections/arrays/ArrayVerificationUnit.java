package com.cognitionbox.petra.stdlib.collections.arrays;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;

@Base
public class ArrayVerificationUnit implements VerificationUnit<Array> {
    @Override
    public Array target() {
        return array;
    }

    private final int capacity = 10;
    private final Array array = new Array(capacity);
    private int index;

    public void verifySetBounds() {
        if (index >= 0 && index < capacity){
            array.set(index, 0);
        }
    }

    public void verifySetBoundsFail() {
        try {
            if (index <0 || index >= capacity){
                array.set(index, 0);
            }
        } catch (AssertionError e){}
    }

    public void verifyGetBounds() {
        if (index >= 0 && index < capacity){
            array.get(index);
        }
    }
}