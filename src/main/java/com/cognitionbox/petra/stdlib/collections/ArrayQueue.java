package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.arrays.Array;

@Base
public final class ArrayQueue<T> {
    private final Array<T> data;
    private int head;
    private int tail;
    private int size;
    private final int capacity;

    public ArrayQueue(final int capacity) {
        assert capacity > 0 : "Capacity must be greater than zero";
        this.data = new Array<>(capacity);
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.capacity = capacity;
    }

    public void enqueue(final T item) {
        assert !isFull() : "Protocol failure: queue is full";
        assert this.tail >= 0 && this.tail < this.capacity : "Tail boundary invariant failed";

        this.data.set(this.tail, item);
        this.tail = (this.tail + 1) % this.capacity;
        this.size++;
    }

    public T dequeue() {
        assert !isEmpty() : "Protocol failure: queue is empty";
        assert this.head >= 0 && this.head < this.capacity : "Head boundary invariant failed";

        final T item = this.data.get(this.head);
        this.head = (this.head + 1) % this.capacity;
        this.size--;
        return item;
    }

    public T peek() {
        assert !isEmpty() : "Protocol failure: queue is empty";
        assert this.head >= 0 && this.head < this.capacity : "Head boundary invariant failed";

        return this.data.get(this.head);
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