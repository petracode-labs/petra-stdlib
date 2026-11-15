package com.cognitionbox.petra.stdlib.collections.arrays;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.IntStack;

@Base
public class ResizableArrayVerificationUnit implements VerificationUnit<ResizableArray> {
    private final int capacity = 3;
    private final ResizableArray array = new ResizableArray(capacity);
    private int index;
    @Override
    public ResizableArray target() {
        return array;
    }

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