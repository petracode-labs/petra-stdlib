package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.maps.ArrayHashMap;

@Base
public class ArrayHashMapVerificationUnit implements VerificationUnit<ArrayHashMap<Integer, Object>> {
    private final int capacity = 3;
    private final ArrayHashMap<Integer, Object> map = new ArrayHashMap<>(capacity);
    private final Object value = new Object();
    private Integer key;

    @Override
    public ArrayHashMap<Integer, Object> target() {
        return map;
    }

    public void verifyPutValid() {
        if (key != null) {
            map.put(key, value);
        }
    }

    public void verifyGetValid() {
        if (key != null && map.containsKey(key)) {
            map.get(key);
        }
    }

    public void verifyGetMissingKeyFail() {
        try {
            if (key != null && !map.containsKey(key)) {
                map.get(key);
            }
        } catch (AssertionError e) {}
    }

    public void verifyRemoveValid() {
        if (key != null) {
            map.remove(key);
        }
    }

    public void verifyContainsKeyValid() {
        if (key != null) {
            map.containsKey(key);
        }
    }
}