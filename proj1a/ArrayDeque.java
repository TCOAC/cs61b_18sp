public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;
    private static final int INIT_CAPACITY = 8;

    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int current = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[current];
            current = plusOne(current);
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = plusOne(nextFirst);
        T firstItem = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / RFACTOR);
        }
        return firstItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = minusOne(nextLast);
        T lastItem = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;
        if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / RFACTOR);
        }
        return lastItem;
    }

}
