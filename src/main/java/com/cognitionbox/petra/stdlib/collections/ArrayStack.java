package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.arrays.Array;

@Base
public final class ArrayStack<T> {
    private final Array<T> data;
    private int size;
    private final int capacity;

    public ArrayStack(final int capacity) {
        assert capacity > 0 : "Capacity must be greater than zero";
        this.data = new Array<>(capacity);
        this.size = 0;
        this.capacity = capacity;
    }

    public void push(final T item) {
        assert !isFull() : "Protocol failure: stack is full";
        assert this.size >= 0 && this.size < this.capacity : "Boundary invariant failed";

        this.data.set(this.size, item);
        this.size++;
    }

    public T pop() {
        assert !isEmpty() : "Protocol failure: stack is empty";
        this.size--;
        assert this.size >= 0 : "Boundary invariant failed";

        return this.data.get(this.size);
    }

    public T peek() {
        assert !isEmpty() : "Protocol failure: stack is empty";
        final int targetIndex = this.size - 1;
        assert targetIndex >= 0 : "Boundary invariant failed";

        return this.data.get(targetIndex);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }
}