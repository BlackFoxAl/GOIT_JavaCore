package Module9;

public class MyHashMap {
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

    void put(Object key, Object value) {
        if (count == 0) {
            NodeHashMap node = new NodeHashMap(key, value, null);
            head = node;
            count++;
            return;
        }
        NodeHashMap node = new NodeHashMap(key, value, head);
        head = node;
        count++;
    }

    void remove(Object key) {

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
        while (current.next != null) {

            return current.value;
        }
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
