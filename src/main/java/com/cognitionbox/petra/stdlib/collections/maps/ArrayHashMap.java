package com.cognitionbox.petra.stdlib.collections.maps;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.arrays.Array;

@Base
public final class ArrayHashMap<K, V> {
    private final Array<MapNode<K, V>> buckets;
    private final MapNode<K, V> sentinel;
    private int size;
    private final int capacity;

    public ArrayHashMap(final int capacity) {
        assert capacity > 0 : "Capacity must be greater than zero";
        this.sentinel = new MapNode<>();
        this.buckets = new Array<>(capacity);
        this.size = 0;
        this.capacity = capacity;

        for (int i = 0; i < capacity; i++) {
            this.buckets.set(i, this.sentinel);
        }
    }

    private int hash(final K key) {
        final int h = key.hashCode();
        final int index = (h % this.capacity + this.capacity) % this.capacity;
        assert index >= 0 && index < this.capacity : "Hash boundary invariant failed";
        return index;
    }

    public void put(final K key, final V value) {
        final int index = hash(key);
        MapNode<K, V> current = this.buckets.get(index);

        while (!current.isSentinel()) {
            if (current.getKey().equals(key)) {
                current.setValue(value);
                return;
            }
            current = current.getNext();
        }

        final MapNode<K, V> newHead = new MapNode<>(key, value, this.buckets.get(index));
        this.buckets.set(index, newHead);
        this.size++;
    }

    public V get(final K key) {
        final int index = hash(key);
        MapNode<K, V> current = this.buckets.get(index);

        while (!current.isSentinel()) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }

        assert false : "Key not found in map";
        return current.getValue();
    }

    public void remove(final K key) {
        final int index = hash(key);
        MapNode<K, V> current = this.buckets.get(index);
        MapNode<K, V> prev = this.sentinel;

        while (!current.isSentinel()) {
            if (current.getKey().equals(key)) {
                if (prev.isSentinel()) {
                    this.buckets.set(index, current.getNext());
                } else {
                    prev.setNext(current.getNext());
                }
                this.size--;
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }

    public boolean containsKey(final K key) {
        final int index = hash(key);
        MapNode<K, V> current = this.buckets.get(index);

        while (!current.isSentinel()) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}