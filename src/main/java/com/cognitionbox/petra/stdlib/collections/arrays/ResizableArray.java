package com.cognitionbox.petra.stdlib.collections.arrays;

import com.cognitionbox.petra.ast.terms.Base;

@Base
public final class ResizableArray<T> {
    private final Array<Array<T>> container; // Final reference box for array swapping
    private int size;
    private int capacity;

    public ResizableArray(final int initialCapacity) {
        assert initialCapacity > 0 : "Initial capacity must be greater than zero";
        this.container = new Array<>(1);
        this.container.set(0, new Array<T>(initialCapacity));
        this.size = 0;
        this.capacity = initialCapacity;
    }

    // --- Capacity & Resize Inspection ---

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

    public int remainingCapacity() {
        return this.capacity - this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    public boolean needsResize() {
        return this.size >= this.capacity;
    }

    public boolean needsResizeFor(final int additionalElements) {
        assert additionalElements >= 0 : "Additional elements cannot be negative";
        return additionalElements > (this.capacity - this.size);
    }

    // --- Manual & Automatic Resizing ---

    public void resize(final int newCapacity) {
        assert newCapacity > this.capacity : "New capacity must exceed current capacity";
        assert newCapacity >= this.size : "New capacity cannot truncate existing elements";

        final Array<T> oldData = this.container.get(0);
        final Array<T> newData = new Array<>(newCapacity);

        for (int i = 0; i < this.size; i++) {
            newData.set(i, oldData.get(i));
        }

        this.container.set(0, newData);
        this.capacity = newCapacity;
    }

    public void ensureCapacity(final int minCapacity) {
        assert minCapacity > 0 : "Minimum capacity must be greater than zero";
        if (minCapacity > this.capacity) {
            int target = this.capacity * 2;
            if (target < minCapacity) {
                target = minCapacity;
            }
            resize(target);
        }
    }

    // --- Index-Stable Operations (No Element Shifting) ---

    public int allocate(final T item) {
        if (needsResize()) {
            resize(this.capacity * 2);
        }

        assert this.size < this.capacity : "Pre-write capacity invariant failed";
        final int index = this.size;
        final Array<T> data = this.container.get(0);
        data.set(index, item);
        this.container.set(0, data);
        this.size++;
        return index;
    }

//    public T get(final int index) {
//        assert index >= 0 : "Index cannot be negative";
//        assert index < this.size : "Index exceeds allocated size";
//        return this.container.get(0).get(index);
//    }
//
//    public void set(final int index, final T item) {
//        assert index >= 0 : "Index cannot be negative";
//        assert index < this.size : "Index exceeds allocated size";
//        final Array<T> data = this.container.get(0);
//        data.set(index, item);
//        this.container.set(0, data);
//    }

    public T get(final int index) {
        assert index >= 0 : "Pre-condition failure: index cannot be negative";
        assert index < this.size : "Pre-condition failure: index exceeds size bound";

        final Array<T> data = this.container.get(0);
        return data.get(index);
    }

    public void set(final int index, final T item) {
        assert index >= 0 : "Pre-condition failure: index cannot be negative";
        assert index < this.size : "Pre-condition failure: index exceeds size bound";

        final Array<T> data = this.container.get(0);
        data.set(index, item);
        this.container.set(0, data);
    }
}