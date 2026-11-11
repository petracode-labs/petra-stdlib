package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;

@Base
public class ArrayStackVerificationUnit implements VerificationUnit<ArrayStack<Object>> {
    private final int capacity = 3;
    private final ArrayStack<Object> stack = new ArrayStack<>(capacity);
    private final Object element = new Object();

    @Override
    public ArrayStack<Object> target() {
        return stack;
    }

    public void verifyPushValid() {
        if (!stack.isFull()) {
            stack.push(element);
        }
    }

    public void verifyPushOverflowFail() {
        try {
            if (stack.isFull()) {
                stack.push(element);
            }
        } catch (AssertionError e) {}
    }

    public void verifyPopValid() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public void verifyPopUnderflowFail() {
        try {
            if (stack.isEmpty()) {
                stack.pop();
            }
        } catch (AssertionError e) {}
    }

    public void verifyPeekValid() {
        if (!stack.isEmpty()) {
            stack.peek();
        }
    }

    public void verifyPeekEmptyFail() {
        try {
            if (stack.isEmpty()) {
                stack.peek();
            }
        } catch (AssertionError e) {}
    }
}