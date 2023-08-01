package Module9;

class MyHashMap {
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
    NodeHashMap head;
    int count;
    MyHashMap() {
        count = 0;
        head = null;
    }

    boolean put(Object key, Object value) {
        NodeHashMap current;
        if (count == 0) {
            NodeHashMap node = new NodeHashMap(key, value, null);
            head = node;
            count++;
            return true;
        }
        current = head;
        do {
            if (current.key.equals(key)) {
                System.out.println("An element with key " + key + " exists");
                return false;
            }
            current = current.next;
        } while (current != null);

        NodeHashMap node = new NodeHashMap(key, value, head);
        head = node;
        count++;
        return true;
    }

    boolean remove(Object key) {
        NodeHashMap current, previous;
        if (head.key.equals(key) && head.next == null) {
            clear();
            return true;
        }
        if (head.key.equals(key)) {
            head =  head.next;
            count--;
            return true;
        }

        previous = head;
        current = head.next;
        while (current != null) {
            if (current.key.equals(key)) {
                previous.next = current.next;
                count--;
                return true;
            }

            current = current.next;
            previous = previous.next;
        }
        System.out.println("An element with key " + key + "  not exists");
        return false;
    }
    void clear() {
        count = 0;
        head = null;
    }
    int size() {
        return count;
    }
    Object get(Object key) {

        NodeHashMap current = head;
        do {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;

        } while (current != null);
        System.out.println("An element with key " + key + " not exists");
        return null;
    }

}

class NodeHashMap {
    Object value;
    Object key;
    NodeHashMap next;
    NodeHashMap(Object key, Object value, NodeHashMap next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

class TestMyHashMap {
    public static void main(String[] args) {
        int counter = 0;

        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(0,"First element");
        myHashMap.put(1,"Second element");
        myHashMap.put(2,"Third element");
        myHashMap.put(3,"Forth element");
        myHashMap.put(4,"Fifth element");
        System.out.println("***Full array***");
        System.out.println("Size HashMap 5 -> " + myHashMap.size());
        for (int i = 0; i < myHashMap.size(); i++) {
           System.out.println(myHashMap.get(i));
        }
        myHashMap.remove(3);
        System.out.println("***Delete forth element***");
        System.out.println("Size HashMap 4 -> " + myHashMap.size());
        for (int i = 0; i <= myHashMap.size(); i++) {
            System.out.println(myHashMap.get(i));
        }
        myHashMap.remove(3);
        myHashMap.put(3,"Forth element");
        for (int i = 0; i < myHashMap.size(); i++) {
            System.out.println(myHashMap.get(i));
        }
        myHashMap.put(3,"Sixth element");
    }
}
