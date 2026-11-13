package com.cognitionbox.petra.stdlib.collections.arrays;

import com.cognitionbox.petra.ast.terms.Base;

@Base
public final class ArrayListV2<T> {
    private Array<T> data;
    private int size;
    private int capacity;

    public ArrayListV2(final int initialCapacity) {
        assert initialCapacity > 0 : "Initial capacity must be greater than zero";
        this.data = new Array<T>(initialCapacity);
        this.size = 0;
        this.capacity = initialCapacity;
    }

    public static <T> ArrayListV2<T> create() {
        return new ArrayListV2<>(10);
    }

    public void add(final T item) {
        if (this.size == this.capacity) {
            resize(this.capacity * 2);
        }

        assert this.size < this.capacity : "Pre-write capacity invariant failed";
        this.data.set(this.size, item);
        this.size++;
    }

    public T getAt(final int index) {
        assert index >= 0 : "Pre-condition failure: index cannot be negative";
        assert index < this.size : "Pre-condition failure: index exceeds size bound";

        return this.data.get(index);
    }

    public void setAt(final int index, final T item) {
        assert index >= 0 : "Pre-condition failure: index cannot be negative";
        assert index < this.size : "Pre-condition failure: index exceeds size bound";

        this.data.set(index, item);
    }

    public T remove(final int index) {
        assert index >= 0 : "Pre-condition failure: index cannot be negative";
        assert index < this.size : "Pre-condition failure: index exceeds size bound";

        final T removedItem = this.data.get(index);

        for (int i = index; i < this.size - 1; i++) {
            this.data.set(i, this.data.get(i + 1));
        }
        this.size--;

        return removedItem;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void resize(final int newCapacity) {
        assert newCapacity > this.capacity : "Resize failure: new capacity must exceed current";

        final Array<T> newData = new Array<>(newCapacity);

        for (int i = 0; i < this.size; i++) {
            newData.set(i, this.data.get(i));
        }

        this.data = newData;
        this.capacity = newCapacity;
    }
}