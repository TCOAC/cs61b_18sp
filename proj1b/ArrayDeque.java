public class ArrayDeque<item> implements Deque<item> {

    private int nextFirst;
    private int nextLast;
    private item[] items;
    private int size;
    public ArrayDeque() {
        items = (item[]) new Object[8];
        nextFirst = items.length - 1;
        nextLast = 0;
        size = 0;
    }

    private void resize(int capacity) {
        item[] a = (item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, nextLast);
        int length = items.length - nextFirst - 1;
        System.arraycopy(items, nextFirst + 1, a, capacity - length, length);
        nextFirst = capacity - length - 1;
        items = a;
    }

    public void addFirst(item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst --;
        size++;

    }

    public void addLast(item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast ++;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = nextFirst + 1; i < nextLast; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println(items[nextLast-1]);
    }

    public item removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst ++;
        item temp = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (size < items.length / 4 && items.length > 8) {
            resize(items.length / 2);
        }
        return temp;
    }

    public item removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast --;
        item temp = items[nextLast];
        items[nextLast] = null;
        size--;
        if (size < items.length / 4 && items.length > 8) {
            resize(items.length / 2);
        }
        return temp;
    }

    public item get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    @Override
    public item getFirst() {
        return null;
    }

    @Override
    public item getLast() {
        return null;
    }
}
