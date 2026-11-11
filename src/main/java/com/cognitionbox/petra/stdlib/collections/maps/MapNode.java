package com.cognitionbox.petra.stdlib.collections.maps;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.arrays.Array;

@Base
public final class MapNode<K, V> {
    private final Array<K> keyBox;
    private final Array<V> valBox;
    private final Array<MapNode<K, V>> nextBox;
    private int nodeType; // 0 = Sentinel, 1 = Data Node

    // Sentinel Node Constructor
    public MapNode() {
        this.keyBox = new Array<>(1);
        this.valBox = new Array<>(1);
        this.nextBox = new Array<>(1);
        this.nodeType = 0;
    }

    // Data Node Constructor
    public MapNode(final K key, final V value, final MapNode<K, V> next) {
        this.keyBox = new Array<>(1);
        this.keyBox.set(0, key);
        this.valBox = new Array<>(1);
        this.valBox.set(0, value);
        this.nextBox = new Array<>(1);
        this.nextBox.set(0, next);
        this.nodeType = 1;
    }

    public boolean isSentinel() {
        return this.nodeType == 0;
    }

    public K getKey() {
        assert !isSentinel() : "Cannot extract key from sentinel node";
        return this.keyBox.get(0);
    }

    public V getValue() {
        assert !isSentinel() : "Cannot extract value from sentinel node";
        return this.valBox.get(0);
    }

    public void setValue(final V value) {
        assert !isSentinel() : "Cannot set value on sentinel node";
        this.valBox.set(0, value);
    }

    public MapNode<K, V> getNext() {
        return this.nextBox.get(0);
    }

    public void setNext(final MapNode<K, V> next) {
        this.nextBox.set(0, next);
    }
}