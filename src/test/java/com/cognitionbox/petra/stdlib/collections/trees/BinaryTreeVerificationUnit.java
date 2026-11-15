package com.cognitionbox.petra.stdlib.collections.trees;

import com.cognitionbox.petra.ast.interp.VerificationUnit;
import com.cognitionbox.petra.ast.terms.Base;

@Base
public class BinaryTreeVerificationUnit implements VerificationUnit<BinaryTree<Object>> {
    private final int initialCapacity = 2;
    private final BinaryTree<Object> tree = new BinaryTree<>(initialCapacity);
    private final Object element = new Object();
    private int index;

//    public BinaryTreeVerificationUnit(){
//        assert index > 0 && index < 100;
//    }
    @Override
    public BinaryTree<Object> target() {
        return tree;
    }

    // --- Root Construction ---

    public void verifySetRootValid() {
        if (index > 0 && index < 100){
            if (tree.isEmpty()) {
                tree.setRoot(element);
            }
        }
    }

    public void verifySetRootFail() {
        if (index > 0 && index < 100) {
            try {
                if (!tree.isEmpty()) {
                    tree.setRoot(element);
                }
            } catch (AssertionError e) {
            }
        }
    }

    // --- Child Insertion (Triggers Resize Past Capacity 2) ---

    public void verifyAddLeftValid() {
        if (index >= 0 && index < 100) {
            if (tree.isValidNode(index) && !tree.hasLeft(index)) {
                tree.addLeft(index, element);
            }
        }
    }

    public void verifyAddLeftFail() {
        if (index >= 0 && index < 100) {
            try {
                if (!tree.isValidNode(index) || tree.hasLeft(index)) {
                    tree.addLeft(index, element);
                }
            } catch (AssertionError e) {
            }
        }
    }

    public void verifyAddRightValid() {
        if (index >= 0 && index < 100) {
            if (tree.isValidNode(index) && !tree.hasRight(index)) {
                tree.addRight(index, element);
            }
        }
    }

    public void verifyAddRightFail() {
        if (index >= 0 && index < 100) {
            try {
                if (!tree.isValidNode(index) || tree.hasRight(index)) {
                    tree.addRight(index, element);
                }
            } catch (AssertionError e) {
            }
        }
    }

    // --- Value Access & Mutation ---

    public void verifyGetValueBoundsValid() {
        if (index >= 0 && index < 100) {
            if (tree.isValidNode(index)) {
                tree.getValue(index);
            }
        }
    }


    public void verifyGetValueBoundsFail() {
        if (index >= 0 && index < 100) {
            try {
                if (!tree.isValidNode(index)) {
                    tree.getValue(index);
                }
            } catch (AssertionError e) {
            }
        }
    }

    public void verifySetValueBoundsValid() {
        if (index >= 0 && index < 100){
            if (tree.isValidNode(index)) {
                tree.setValue(index, element);
            }
        }
    }

    public void verifySetValueBoundsFail() {
        if (index >= 0 && index < 100) {
            try {
                if (!tree.isValidNode(index)) {
                    tree.setValue(index, element);
                }
            } catch (AssertionError e) {
            }
        }
    }

    // --- Structural Link Accessors & Predicates ---

    public void verifyNavigationBoundsValid() {
        if (index >= 0 && index < 100){
            if (tree.isValidNode(index)) {
                tree.getLeft(index);
                tree.getRight(index);
                tree.getParent(index);
                tree.hasLeft(index);
                tree.hasRight(index);
                tree.hasParent(index);
                tree.isLeaf(index);
            }
        }
    }

    public void verifyGetLeftBoundsFail() {
        if (index >= 0 && index < 100){
            try {
                if (!tree.isValidNode(index)) {
                    tree.getLeft(index);
                }
            } catch (AssertionError e) {}
        }
    }

    public void verifyGetRightBoundsFail() {
        if (index >= 0 && index < 100){
            try {
                if (!tree.isValidNode(index)) {
                    tree.getRight(index);
                }
            } catch (AssertionError e) {}
        }
    }

    public void verifyGetParentBoundsFail() {
        if (index >= 0 && index < 100){
            try {
                if (!tree.isValidNode(index)) {
                    tree.getParent(index);
                }
            } catch (AssertionError e) {}
        }
    }

    public void verifyPredicatesBoundsFail() {
        if (index >= 0 && index < 100){
            try {
                if (!tree.isValidNode(index)) {
                    tree.hasLeft(index);
                }
            } catch (AssertionError e) {}

            try {
                if (!tree.isValidNode(index)) {
                    tree.hasRight(index);
                }
            } catch (AssertionError e) {}

            try {
                if (!tree.isValidNode(index)) {
                    tree.hasParent(index);
                }
            } catch (AssertionError e) {}

            try {
                if (!tree.isValidNode(index)) {
                    tree.isLeaf(index);
                }
            } catch (AssertionError e) {}
        }
    }

    // --- Global & Bidirectional Tree Invariants ---

    public void verifyStructuralInvariants() {
        if (index >= 0 && index < 100){
            if (tree.isEmpty()) {
                assert tree.root() == BinaryTree.NONE : "Empty tree must have NONE root";
            } else {
                assert tree.root() == 0 : "Non-empty tree root must be at index 0";
                assert !tree.hasParent(tree.root()) : "Root node cannot have a parent";
            }

            if (tree.isValidNode(index)) {
                if (tree.hasLeft(index)) {
                    final int leftChild = tree.getLeft(index);
                    assert tree.isValidNode(leftChild) : "Left child index out of bounds";
                    assert tree.getParent(leftChild) == index : "Left child parent symmetry broken";
                }
                if (tree.hasRight(index)) {
                    final int rightChild = tree.getRight(index);
                    assert tree.isValidNode(rightChild) : "Right child index out of bounds";
                    assert tree.getParent(rightChild) == index : "Right child parent symmetry broken";
                }
                if (tree.hasParent(index)) {
                    final int parentNode = tree.getParent(index);
                    assert tree.isValidNode(parentNode) : "Parent index out of bounds";
                    assert tree.getLeft(parentNode) == index || tree.getRight(parentNode) == index
                            : "Parent does not reference child";
                }
            }
        }
    }
}