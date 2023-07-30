package Module9;

class MyArrayList <E> {
    private E[] elements;
    private int size, capacity;
    private Object[] tempElements;
/*    MyArrayList(Class<E> clazz, int capacity) {
        elements = (E[]) Array.newInstance(clazz, capacity);

    }*/
    MyArrayList(E[] elements) {
        this.elements = elements;
        size = elements.length;
        capacity = size;
    }
    void add(Object value) {
        size++;
        if (capacity < size) {
            capacity += 10;
        }
        tempElements = new Object[capacity];
        if (size > 1) {
            System.arraycopy(elements, 0, tempElements,0, size-1);
        }
        tempElements[size - 1] = value;
        elements = (E[]) tempElements;
    }
    void remove(int index) {
        if (size > 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
            size--;
        }
    }
    void clear() {
        size = 0;
        capacity = 0;
        elements = (E[]) new Object[capacity];
    }
    int size() {
        return this.size;
    }
    E get(int index) {
        return elements[index];
    }
}

class TestMyArrayList {
    public static void main(String[] args) {
        Integer[] numberType = {1000, 2000};
        MyArrayList arrList = new MyArrayList(numberType);
        arrList.add(3000);
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }
        arrList.remove(1);
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }
        arrList.clear();
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }
        arrList.add(3000);
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }
        String[] stringType = {"Alex", "Kolesnyk"};
        MyArrayList arrListString = new MyArrayList(stringType);
        arrListString.add("Oleksandr");
        for (int i = 0; i < arrListString.size(); i++) {
            System.out.println(arrListString.get(i));
        }
    }
}