package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.terms.Base;

@Base
public final class Array<T> {
      public final T[] data;
      public final int length;

      public Array(final int capacity) {
          assert capacity > 0 : "Capacity must be greater than zero";
          this.data = (T[]) new Object[capacity];
          this.length = capacity;
      }

      public T get(final int index) {
          assert index >= 0 : "Index cannot be negative";
          assert index < this.length : "Index out of bounds: index exceeds length";
          return this.data[index];
      }

      public void set(final int index, final T value) {
          assert index >= 0 : "Index cannot be negative";
          assert index < this.length : "Index out of bounds: index exceeds length";
          this.data[index] = value;
      }
  }
