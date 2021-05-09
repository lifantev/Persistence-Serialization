package com.netcracker.edu.arraylinkedlistvector;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Modified ArrayVector and LinkedListVector classes
 * (based on an array and on a linked list) in such a way that they
 * are serializable.
 * NOTE: LinkedList is serializable by default and it doesn't need modification.
 */
public class CollectionsSerialization {
    /**
     * Doesn't need any modifications.
     */
    public static class LinkedListVector {
    }

    /**
     * ArrayVector is a serializable class, based on array.
     */
    public static class ArrayVector implements Serializable {
        private int[] array;

        /**
         * Constructor specifying stored array.
         *
         * @param array
         */
        public ArrayVector(int[] array) {
            setArray(array);
        }

        /**
         * Default array.
         */
        public ArrayVector() {
        }

        /**
         * Setter for array.
         *
         * @param array
         */
        public void setArray(int[] array) {
            this.array = array;
        }

        /**
         * Getter for array.
         *
         * @return
         */
        public int[] getArray() {
            return array;
        }

        /**
         * Standard override of {@link Object#equals(Object)} method.
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayVector that = (ArrayVector) o;
            return Arrays.equals(array, that.array);
        }

        /**
         * Standard override of {@link Object#hashCode()} method.
         *
         * @return
         */
        @Override
        public int hashCode() {
            return Arrays.hashCode(array);
        }
    }
}
