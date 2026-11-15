package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.maps.ArrayHashMap;

@Base
public class ArrayHashMapVerificationUnit implements VerificationUnit<ArrayHashMap<Integer, Object>> {
    private final int capacity = 3;
    private final ArrayHashMap<Integer, Object> map = new ArrayHashMap<>(capacity);
    private final Object value = new Object();
    private int key;

    @Override
    public ArrayHashMap<Integer, Object> target() {
        return map;
    }

    public void verifyPutValid() {
        if (key > 0 && key < 10) {
            map.put(key, value);
        }
    }

    public void verifyGetValid() {
        if (key > 0 && key < 10 && map.containsKey(key)) {
            map.get(key);
        }
    }

    public void verifyGetMissingKeyFail() {
        try {
            if (key > 0 && key < 10 && !map.containsKey(key)) {
                map.get(key);
            }
        } catch (AssertionError e) {}
    }

    public void verifyRemoveValid() {
        if (key > 0 && key < 10) {
            map.remove(key);
        }
    }

    public void verifyContainsKeyValid() {
        if (key > 0 && key < 10) {
            map.containsKey(key);
        }
    }
}