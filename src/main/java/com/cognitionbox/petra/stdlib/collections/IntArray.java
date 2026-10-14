package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.terms.Base;

@Base
public final class IntArray {
      public final int[] data;
      public final int length;

      public IntArray(final int capacity) {
          assert capacity > 0 : "Capacity must be greater than zero";
          this.data = new int[capacity];
          this.length = capacity;
      }

      public int get(final int index) {
          assert index >= 0 : "Index cannot be negative";
          assert index < this.length : "Index out of bounds: index exceeds length";
          return this.data[index];
      }

      public void set(final int index, final int value) {
          assert index >= 0 : "Index cannot be negative";
          assert index < this.length : "Index out of bounds: index exceeds length";
          this.data[index] = value;
      }
  }
