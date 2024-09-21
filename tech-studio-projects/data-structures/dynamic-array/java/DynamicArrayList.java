public class DynamicArrayList {
    private int[] array;
    private int capacity;
    private int length;

    public DynamicArrayList() {
        capacity = 10;
        array = new int[capacity];
        length = 0;
    }

    private void resize(int newCapacity) {
        int[] newArray = new int[newCapacity];
        System.arraycopy(array, 0, newArray, 0, length);
        array = newArray;
        capacity = newCapacity;
    }

    public void append(int value) {
        if (length == capacity) {
            resize(capacity * 2);
        }
        array[length++] = value;
    }

    public void insert(int index, int value) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (length == capacity) {
            resize(capacity * 2);
        }
        System.arraycopy(array, index, array, index + 1, length - index);
        array[index] = value;
        length++;
    }

    public void remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        System.arraycopy(array, index + 1, array, index, length - index - 1);
        length--;
        if (length > 0 && length == capacity / 4) {
            resize(capacity / 2);
        }
    }

    public int get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return array[index];
    }

    public int size() {
        return length;
    }
}
