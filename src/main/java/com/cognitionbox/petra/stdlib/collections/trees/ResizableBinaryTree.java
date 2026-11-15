package com.cognitionbox.petra.stdlib.collections.trees;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.arrays.ResizableArray;
import com.cognitionbox.petra.stdlib.collections.arrays.ResizableIntArray;

@Base
public final class ResizableBinaryTree<T> {
    public static final int NONE = -1;

    private final ResizableArray<T> values;
    private final ResizableIntArray left;
    private final ResizableIntArray right;
    private final ResizableIntArray parent;
    private int root;

    public ResizableBinaryTree() {
        this(10);
    }

    public ResizableBinaryTree(final int initialCapacity) {
        assert initialCapacity > 0 : "Initial capacity must be greater than zero";
        this.values = new ResizableArray<>(initialCapacity);
        this.left = new ResizableIntArray(initialCapacity);
        this.right = new ResizableIntArray(initialCapacity);
        this.parent = new ResizableIntArray(initialCapacity);
        this.root = NONE;
    }

    // --- Capacity & Resizing Controls ---

    public int size() {
        assert this.values.size() == this.left.size() : "Lockstep invariant failed: values vs left";
        assert this.values.size() == this.right.size() : "Lockstep invariant failed: values vs right";
        assert this.values.size() == this.parent.size() : "Lockstep invariant failed: values vs parent";
        return this.values.size();
    }

    public int capacity() {
        assert this.values.capacity() == this.left.capacity() : "Capacity invariant failed: values vs left";
        assert this.values.capacity() == this.right.capacity() : "Capacity invariant failed: values vs right";
        assert this.values.capacity() == this.parent.capacity() : "Capacity invariant failed: values vs parent";
        return this.values.capacity();
    }

    public int remainingCapacity() {
        return capacity() - size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == capacity();
    }

    public boolean needsResize() {
        return size() >= capacity();
    }

    public boolean needsResizeFor(final int additionalNodes) {
        assert additionalNodes >= 0 : "Additional nodes cannot be negative";
        return additionalNodes > remainingCapacity();
    }

    public void resize(final int newCapacity) {
        assert newCapacity > capacity() : "New capacity must exceed current capacity";
        this.values.resize(newCapacity);
        this.left.resize(newCapacity);
        this.right.resize(newCapacity);
        this.parent.resize(newCapacity);
    }

    public void ensureCapacity(final int minCapacity) {
        assert minCapacity > 0 : "Minimum capacity must be greater than zero";
        if (minCapacity > capacity()) {
            int target = capacity() * 2;
            if (target < minCapacity) {
                target = minCapacity;
            }
            resize(target);
        }
    }

    // --- Node Validation & Inspection ---

    public boolean isValidNode(final int nodeIndex) {
        return nodeIndex >= 0 && nodeIndex < size();
    }

    public int root() {
        return this.root;
    }

    public T getValue(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.values.get(nodeIndex);
    }

    public void setValue(final int nodeIndex, final T value) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        this.values.set(nodeIndex, value);
    }

    public int getLeft(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.left.get(nodeIndex);
    }

    public int getRight(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.right.get(nodeIndex);
    }

    public int getParent(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.parent.get(nodeIndex);
    }

    public boolean hasLeft(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.left.get(nodeIndex) != NONE;
    }

    public boolean hasRight(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.right.get(nodeIndex) != NONE;
    }

    public boolean hasParent(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.parent.get(nodeIndex) != NONE;
    }

    public boolean isLeaf(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.left.get(nodeIndex) == NONE && this.right.get(nodeIndex) == NONE;
    }

    // --- Tree Construction ---

    public int setRoot(final T value) {
        assert isEmpty() : "Tree already has a root";
        assert this.root == NONE : "Root sentinel invariant failed";

        final int rootIndex = allocateNode(value, NONE);
        assert rootIndex == 0 : "Root must be allocated at index 0";
        this.root = rootIndex;
        return rootIndex;
    }

    public int addLeft(final int parentIndex, final T value) {
        assert isValidNode(parentIndex) : "Invalid parent node index";
        assert !hasLeft(parentIndex) : "Parent already has a left child";

        final int childIndex = allocateNode(value, parentIndex);
        this.left.set(parentIndex, childIndex);
        return childIndex;
    }

    public int addRight(final int parentIndex, final T value) {
        assert isValidNode(parentIndex) : "Invalid parent node index";
        assert !hasRight(parentIndex) : "Parent already has a right child";

        final int childIndex = allocateNode(value, parentIndex);
        this.right.set(parentIndex, childIndex);
        return childIndex;
    }

    // --- Internal Lockstep Allocation ---

    private int allocateNode(final T value, final int parentIndex) {
        if (needsResize()) {
            resize(capacity() * 2);
        }

        assert size() < capacity() : "Pre-allocation capacity invariant failed";
        final int idxVal = this.values.allocate(value);
        final int idxLeft = this.left.allocate(NONE);
        final int idxRight = this.right.allocate(NONE);
        final int idxParent = this.parent.allocate(parentIndex);

        assert idxVal == idxLeft && idxVal == idxRight && idxVal == idxParent : "Lockstep index mismatch";
        return idxVal;
    }
}