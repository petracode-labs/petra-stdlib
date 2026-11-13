package com.cognitionbox.petra.stdlib.collections.arrays;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;

@Base
public class ArrayListVerificationUnit implements VerificationUnit<ArrayListV2<Object>> {
    private final int initialCapacity = 2;
    private final ArrayListV2<Object> list = new ArrayListV2<>(initialCapacity);
    private final Object element = new Object();
    private int index;

    @Override
    public ArrayListV2<Object> target() {
        return list;
    }

    public void verifyAdd() {
        list.add(element);
    }

    public void verifyGetBoundsValid() {
        if (index >= 0 && index < list.size()) {
            list.getAt(index);
        }
    }

    public void verifyGetBoundsFail() {
        try {
            if (index < 0 || index >= list.size()) {
                list.getAt(index);
            }
        } catch (AssertionError e) {}
    }

    public void verifySetBoundsValid() {
        if (index >= 0 && index < list.size()) {
            list.setAt(index, element);
        }
    }

    public void verifySetBoundsFail() {
        try {
            if (index < 0 || index >= list.size()) {
                list.setAt(index, element);
            }
        } catch (AssertionError e) {}
    }

    public void verifyRemoveBoundsValid() {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        }
    }

    public void verifyRemoveBoundsFail() {
        try {
            if (index < 0 || index >= list.size()) {
                list.remove(index);
            }
        } catch (AssertionError e) {}
    }
}