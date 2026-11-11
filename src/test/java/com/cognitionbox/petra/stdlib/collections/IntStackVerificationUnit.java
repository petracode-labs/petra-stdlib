package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;

@Base
public class IntStackVerificationUnit implements VerificationUnit<IntStack> {
    private final int capacity = 3;
    private final IntStack stack = new IntStack(capacity);
    private int item;

    @Override
    public IntStack target() {
        return stack;
    }

    public void verifyPushValid() {
        stack.push(item);
    }

    public void verifyPushOverflowFail() {
        try {
            if (stack.isFull()) {
                stack.push(item);
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