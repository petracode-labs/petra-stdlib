package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.arrays.Array;

@Base
public final class ArrayList<T> {
    private final Array<Array<T>> container; // Reference box for array swapping
    private int size;
    private int capacity;

    public ArrayList() {
        this(10);
    }

    public ArrayList(final int initialCapacity) {
        assert initialCapacity > 0 : "Initial capacity must be greater than zero";
        this.container = new Array<>(1);
        this.container.set(0, new Array<T>(initialCapacity));
        this.size = 0;
        this.capacity = initialCapacity;
    }

    public void add(final T item) {
        if (this.size == this.capacity) {
            resize(this.capacity * 2);
        }

        assert this.size < this.capacity : "Pre-write capacity invariant failed";
        this.container.get(0).set(this.size, item);
        this.size++;
    }

    public T get(final int index) {
        assert index >= 0 : "Pre-condition failure: index cannot be negative";
        assert index < this.size : "Pre-condition failure: index exceeds size bound";

        return this.container.get(0).get(index);
    }

    public void set(final int index, final T item) {
        assert index >= 0 : "Pre-condition failure: index cannot be negative";
        assert index < this.size : "Pre-condition failure: index exceeds size bound";

        this.container.get(0).set(index, item);
    }

    public T remove(final int index) {
        assert index >= 0 : "Pre-condition failure: index cannot be negative";
        assert index < this.size : "Pre-condition failure: index exceeds size bound";

        final Array<T> data = this.container.get(0);
        final T removedItem = data.get(index);

        for (int i = index; i < this.size - 1; i++) {
            data.set(i, data.get(i + 1));
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

        final Array<T> oldData = this.container.get(0);
        final Array<T> newData = new Array<>(newCapacity);

        for (int i = 0; i < this.size; i++) {
            newData.set(i, oldData.get(i));
        }

        this.container.set(0, newData);
        this.capacity = newCapacity;
    }
}