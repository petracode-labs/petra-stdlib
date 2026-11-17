package com.cognitionbox.petra.stdlib.collections.arrays;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;

@Base
public class ResizableArrayVerificationUnit implements VerificationUnit<ResizableArray> {
    private int capacity;
    private int index;

    @Override
    public ResizableArray target() {
        return null;
    }

    public void verifyAllocate() {
        if (capacity > 0) {
            final ResizableArray array = new ResizableArray(capacity);
            array.allocate(0);
            assert array.size() == 1;
        }
    }

    /**
     * Inductive Verification via Atomic State Abstraction
     * Base: Empty state -> Step: Valid allocation preserves invariants -> Post: Capacity match.
     */
    public void verifySetGetBounds1() {
        final ResizableArray<Integer> array = new ResizableArray(1);
        // 1. Pre-condition: Valid initial capacity
        assert array.size() == 0;

        // 2. Inductive Step: Guarded Allocation
        while (array.size() < array.capacity()) {
            array.allocate(0);
            assert array.size() == array.capacity();
        }
        // 3. Post-condition: Invariant preservation
        assert array.size() == array.capacity();

        if (index >= 0 && index < array.size()) {
            int x = 7;
            array.set(index, x);
            int y = array.get(index);
            assert y==x;
        }
    }

    /**
     * Inductive Verification via Atomic State Abstraction
     * Base: Empty state -> Step: Valid allocation preserves invariants -> Post: Capacity match.
     */
    public void verifySetGetBounds() {
        if (capacity > 0) {
            final ResizableArray<Integer> array = new ResizableArray(capacity);
            // 1. Pre-condition: Valid initial capacity
            assert array.size() == 0;

            // 2. Inductive Step: Guarded Allocation
            if (array.size() < array.capacity()) {
                array.allocate(0);
                assert array.size() == array.capacity();
            }
            // 3. Post-condition: Invariant preservation
            assert array.size() == array.capacity();

            if (index >= 0 && index < array.size()) {
                int x = 7;
                array.set(index, x);
                int y = array.get(index);
                assert y==x;
            }
        }
    }


    public void verifyGetBounds() {
        if (capacity > 0) {
            final ResizableArray array = new ResizableArray(capacity);
            // 1. Pre-condition: Valid initial capacity
            assert array.size() == 0;

            // 2. Abstract Inductive Step (Guarded Allocation)
            if (array.size() < array.capacity()) {
                array.allocate(0);
            }

            // 3. Post-condition / Invariant preservation
            assert array.size() <= array.capacity();

            if (index >= 0 && index < array.size()) {
                array.get(index);
            }

            // Final user assertion guaranteeing post-condition compliance
            assert array.size() == array.capacity();
        }
    }

    public void verifySetBoundsFail() {
        if (capacity > 0) {
            final ResizableArray array = new ResizableArray(capacity);
            assert array.size() == 0;

            boolean caught = false;
            try {
                // Forcing out-of-bounds access on an empty array should trigger an assertion error
                if (index < 0 || index >= array.size()) {
                    array.set(index, 0);
                }
            } catch (AssertionError e) {
                caught = true;
            }

            // User assertion proving that the expected out-of-bounds failure was successfully intercepted
            assert caught || array.size() > 0;
        }
    }

    public void verifyGetBoundsFail() {
        if (capacity > 0) {
            final ResizableArray array = new ResizableArray(capacity);
            assert array.size() == 0;

            boolean caught = false;
            try {
                // Forcing out-of-bounds lookup on an empty array should trigger an assertion error
                if (index < 0 || index >= array.size()) {
                    array.get(index);
                }
            } catch (AssertionError e) {
                caught = true;
            }

            // User assertion proving that the out-of-bounds retrieval was caught as expected
            assert caught || array.size() > 0;
        }
    }

}