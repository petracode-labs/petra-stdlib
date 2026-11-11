package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;

@Base
public class ArrayListVerificationUnit implements VerificationUnit<ArrayList<Object>> {
    private final int initialCapacity = 2;
    private final ArrayList<Object> list = new ArrayList<>(initialCapacity);
    private final Object element = new Object();
    private int index;

    @Override
    public ArrayList<Object> target() {
        return list;
    }

    public void verifyAdd() {
        list.add(element);
    }

    public void verifyGetBoundsValid() {
        if (index >= 0 && index < list.size()) {
            list.get(index);
        }
    }

    public void verifyGetBoundsFail() {
        try {
            if (index < 0 || index >= list.size()) {
                list.get(index);
            }
        } catch (AssertionError e) {}
    }

    public void verifySetBoundsValid() {
        if (index >= 0 && index < list.size()) {
            list.set(index, element);
        }
    }

    public void verifySetBoundsFail() {
        try {
            if (index < 0 || index >= list.size()) {
                list.set(index, element);
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