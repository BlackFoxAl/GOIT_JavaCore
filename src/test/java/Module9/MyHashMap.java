package Module9;

class MyHashMap < K, V > {
/*
Написати свій клас MyHashMap як аналог класу HashMap.

Потрібно робити за допомогою однозв'язної Node.

Не може зберігати дві ноди з однаковими ключами.

Методи

put(Object key, Object value) додає пару ключ + значення
remove(Object key) видаляє пару за ключем
clear() очищає колекцію
size() повертає розмір колекції
get(Object key) повертає значення (Object value) за ключем
 */
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private NodeHashMap < K, V >  table[];
    private int size;
    private int capacity;
    private float loadFactor;
    public MyHashMap() {
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
    }
    public MyHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new NodeHashMap[capacity];
    }
    private static class NodeHashMap <K,V> {
        final K key;
        V value;
        NodeHashMap <K,V> next;
        public NodeHashMap(K key, V value, NodeHashMap next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        NodeHashMap <K,V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                System.out.println("an element with the same key already exists");
                return;
            }
            node = node.next;
        }
        NodeHashMap <K,V> newNode = newNode(key, value, table[index]);
        table[index] = newNode;
        size++;
        if (size > capacity * loadFactor) {
            resize();
        }
    }

    private void resize() {
        int newCapacity = capacity * 2;
        NodeHashMap <K,V> [] newTable = new NodeHashMap[newCapacity];
        for (int i = 0; i < capacity; i++) {
            NodeHashMap <K,V> node = table[i];
            while (node != null) {
                NodeHashMap <K,V> next = node.next;
                int index = hash(node.key);
                node.next = newTable[index];
                newTable[index] = node;
                node = next;
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    public void remove(K key) {
        int index = hash(key);
        NodeHashMap <K, V> node = table[index];
        NodeHashMap <K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return;
            }
            prev = node;
            node = node.next;
        }
        System.out.println("element with such key does not exist");
    }
    void clear() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        loadFactor = DEFAULT_LOAD_FACTOR;
        table = new NodeHashMap[capacity];
    }
    int size() {
        return size;
    }
    public V get(K key) {
        int index = hash(key);
        NodeHashMap <K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        System.out.println("element with such key does not exist");
        return null;
    }

    NodeHashMap newNode(K key, V value, NodeHashMap next) {
        return new NodeHashMap(key, value, next);
    }
}



class TestMyHashMap {
    public static void main(String[] args) {

        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1,1010);
        myHashMap.put(2,2020);
        myHashMap.put(3,3030);
        myHashMap.put(4,4040);
        myHashMap.put(5,5050);
        System.out.println("***Full hashMap***");
        System.out.println("Size HashMap 5 -> " + myHashMap.size());
        for (int i = 1; i <= myHashMap.size(); i++) {
           System.out.println(myHashMap.get(i));
        }
        myHashMap.remove(3);
        System.out.println("***Delete element with key 3***");
        System.out.println("Size HashMap 4 -> " + myHashMap.size());
        for (int i = 1; i <= myHashMap.size(); i++) {
            System.out.println(myHashMap.get(i));
        }
        System.out.println("***Delete element with key 3 again***");
        myHashMap.remove(3);
        myHashMap.put(3,3030);
        for (int i = 1; i <= myHashMap.size(); i++) {
            System.out.println(myHashMap.get(i));
        }
        myHashMap.put(3,6060);
        myHashMap.clear();
        System.out.println(myHashMap.size());
        for (int i = 1; i <= myHashMap.size(); i++) {
            System.out.println(myHashMap.get(i));
        }
    }
}
