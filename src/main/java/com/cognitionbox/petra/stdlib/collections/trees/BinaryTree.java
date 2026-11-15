package com.cognitionbox.petra.stdlib.collections.trees;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.stdlib.collections.arrays.ArrayListV2;

@Base
public final class BinaryTree<T> {
    public static final int NONE = -1;

    private final ArrayListV2<T> values;
    private final ArrayListV2<Integer> left;
    private final ArrayListV2<Integer> right;
    private final ArrayListV2<Integer> parent;
    private int root;

//    public BinaryTree() {
//        this(10);
//    }

    public BinaryTree(final int initialCapacity) {
        assert initialCapacity > 0 : "Initial capacity must be greater than zero";
        this.values = new ArrayListV2<>(initialCapacity);
        this.left = new ArrayListV2<>(initialCapacity);
        this.right = new ArrayListV2<>(initialCapacity);
        this.parent = new ArrayListV2<>(initialCapacity);
        this.root = NONE;
    }

    // --- Size & Node Validation ---

    public int size() {
        assert this.values.size() == this.left.size() : "Lockstep invariant failed: values vs left";
        assert this.values.size() == this.right.size() : "Lockstep invariant failed: values vs right";
        assert this.values.size() == this.parent.size() : "Lockstep invariant failed: values vs parent";
        return this.values.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isValidNode(final int nodeIndex) {
        return nodeIndex >= 0 && nodeIndex < size();
    }

    public int root() {
        return this.root;
    }

    // --- Node Accessors & Mutators ---

    public T getValue(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.values.getAt(nodeIndex);
    }

    public void setValue(final int nodeIndex, final T value) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        this.values.setAt(nodeIndex, value);
    }

    public int getLeft(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.left.getAt(nodeIndex);
    }

    public int getRight(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.right.getAt(nodeIndex);
    }

    public int getParent(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.parent.getAt(nodeIndex);
    }

    public boolean hasLeft(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.left.getAt(nodeIndex) != NONE;
    }

    public boolean hasRight(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.right.getAt(nodeIndex) != NONE;
    }

    public boolean hasParent(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.parent.getAt(nodeIndex) != NONE;
    }

    public boolean isLeaf(final int nodeIndex) {
        assert isValidNode(nodeIndex) : "Invalid node index";
        return this.left.getAt(nodeIndex) == NONE && this.right.getAt(nodeIndex) == NONE;
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
        this.left.setAt(parentIndex, childIndex);
        return childIndex;
    }

    public int addRight(final int parentIndex, final T value) {
        assert isValidNode(parentIndex) : "Invalid parent node index";
        assert !hasRight(parentIndex) : "Parent already has a right child";

        final int childIndex = allocateNode(value, parentIndex);
        this.right.setAt(parentIndex, childIndex);
        return childIndex;
    }

    // --- Internal Lockstep Allocation ---

    private int allocateNode(final T value, final int parentIndex) {
        final int index = size();
        this.values.add(value);
        this.left.add(NONE);
        this.right.add(NONE);
        this.parent.add(parentIndex);

        assert size() == index + 1 : "Lockstep allocation invariant failed";
        return index;
    }
}