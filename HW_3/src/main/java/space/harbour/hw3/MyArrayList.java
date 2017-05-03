package space.harbour.hw3;

import java.util.Collection;
import java.util.Iterator;

public class MyArrayList<T> implements Collection<T> {
    private T[] arr;
    private int size;
    public static final int GROW_FACTOR = 2;

    public MyArrayList(T[] arr) {
        this.arr = arr;
        size = arr.length;
    }

    public MyArrayList(int capacity) {
        arr = (T[]) new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] newArr = (T[]) new Object[size * GROW_FACTOR];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    public boolean add(T t) {
        if (size == arr.length) {
            grow();
        }
        arr[size++] = t;
        return true;
    }

    public boolean addAll(Collection<? extends T> collection) {
        for (T t : collection) {
            add(t);
        }
        return true;
    }

    public void clear() {
        size = 0;
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException("contains is not implemented");
    }

    public Iterator<T> iterator() {
        return new MyIterator(arr);
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException("toArray is not implemented");
    }

    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException("toArray is not implemented");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("remove is not implemented");
    }

    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException("containsAll is not implemented");
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("retainAll is not implemented");
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("retainAll is not implemented");
    }

    public class MyIterator implements Iterator<T> {
        T[] elems;
        int pos;

        public MyIterator(T[] elems) {
            this.elems = elems;
            pos = 0;
        }

        public boolean hasNext() {
            if (pos < size) {
                return true;
            } else {
                return false;
            }
        }

        public T next() {
            if (hasNext()) {
                return elems[pos++];
            } else {
                return null;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("no changes allowed");
        }
    }
}
