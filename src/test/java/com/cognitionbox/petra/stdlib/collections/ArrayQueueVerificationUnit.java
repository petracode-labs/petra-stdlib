package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;

@Base
public class ArrayQueueVerificationUnit implements VerificationUnit<ArrayQueue<Object>> {
    private final int capacity = 3;
    private final ArrayQueue<Object> queue = new ArrayQueue<>(capacity);
    private final Object element = new Object();

    @Override
    public ArrayQueue<Object> target() {
        return queue;
    }

    public void verifyEnqueueValid() {
        if (!queue.isFull()) {
            queue.enqueue(element);
        }
    }

    public void verifyEnqueueOverflowFail() {
        try {
            if (queue.isFull()) {
                queue.enqueue(element);
            }
        } catch (AssertionError e) {}
    }

    public void verifyDequeueValid() {
        if (!queue.isEmpty()) {
            queue.dequeue();
        }
    }

    public void verifyDequeueUnderflowFail() {
        try {
            if (queue.isEmpty()) {
                queue.dequeue();
            }
        } catch (AssertionError e) {}
    }

    public void verifyPeekValid() {
        if (!queue.isEmpty()) {
            queue.peek();
        }
    }

    public void verifyPeekEmptyFail() {
        try {
            if (queue.isEmpty()) {
                queue.peek();
            }
        } catch (AssertionError e) {}
    }
}