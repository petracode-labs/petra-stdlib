package com.cognitionbox.petra.stdlib.collections.arrays;

import com.cognitionbox.petra.ast.terms.Base;

@Base
public final class ResizableIntArray {
    private final Array<IntArray> container; // Final reference box for IntArray swapping
    private int size;
    private int capacity;

    public ResizableIntArray(final int initialCapacity) {
        assert initialCapacity > 0 : "Initial capacity must be greater than zero";
        this.container = new Array<>(1);
        this.container.set(0, new IntArray(initialCapacity));
        this.size = 0;
        this.capacity = initialCapacity;
    }

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

    public void resize(final int newCapacity) {
        assert newCapacity > this.capacity : "New capacity must exceed current capacity";
        assert newCapacity >= this.size : "New capacity cannot truncate existing elements";

        final IntArray oldData = this.container.get(0);
        final IntArray newData = new IntArray(newCapacity);

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

    public int allocate(final int value) {
        if (needsResize()) {
            resize(this.capacity * 2);
        }

        assert this.size < this.capacity : "Pre-write capacity invariant failed";
        final int index = this.size;
        final IntArray data = this.container.get(0);
        data.set(index, value);
        this.container.set(0, data);
        this.size++;
        return index;
    }

    public int get(final int index) {
        assert index >= 0 : "Index cannot be negative";
        assert index < this.size : "Index exceeds allocated size";
        return this.container.get(0).get(index);
    }

    public void set(final int index, final int value) {
        assert index >= 0 : "Index cannot be negative";
        assert index < this.size : "Index exceeds allocated size";
        final IntArray data = this.container.get(0);
        data.set(index, value);
        this.container.set(0, data);
    }
}