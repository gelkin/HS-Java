package space.harbour.hw5.classes;

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

    public T get(int ind) {
        return arr[ind];
    }

    public Iterator<T> iterator() {
        return new MyIterator(arr, size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            sb.append(arr[i]);
            if (i < size - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException("contains is not implemented");
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
        int size;
        int pos;

        public MyIterator(T[] elems, int size) {
            this.elems = elems;
            this.size = size;
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
