package Module9;
class MyLinkedList <E> {
    Node head;
    Node tail;
    int count;
    MyLinkedList() {
        count = 0;
        head = tail = null;

    }

    void add(Object value) {
        if (count == 0) {
            Node node = new Node((E) value, null, null);
            head = node;
            tail = head;
            count++;
            return;
        }
        Node node = new Node((E) value, null, tail);
        tail.next = node;
        tail = node;
        count++;
    }
    boolean remove(int index) {
        Node current;
        if (index > count-1) {
            return false;
        }
        if (count == 1 && index == 0) {
            clear();
            return true;
        }
        if (index == 0) {
            head =  head.next;
            count--;
            return true;
        }
        if (index == count - 1) {
            tail = tail.previous;
            count--;
            return true;
        }
        int counter = 1;
        current = head.next;
        while (counter <= index) {
            if (index == counter) {
                current.previous.next = current.next;
                current.next.previous = current.previous;
                count--;
                return true;
            }
            counter++;
            current = current.next;
        }
        return false;
    }
    void clear() {
        count = 0;
        tail = head = null;
    }
    int size() {
        return count;
    }
    E get(int index) {
        Node current;
        if (index > count-1) {
            return null;
        }
        int counter = 0;
        current = head;
        while (counter <= index) {
            if (index == counter) {
                return (E) current.data;
            }
            counter++;
            current = current.next;
        }
        return null;
    }
}
class Node <E> {
    E data;
    Node next;
    Node previous;
    Node(E data, Node next, Node previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
}

class TestMyLinkedList {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.add(1000);
        linkedList.add(2000);
        linkedList.add(3000);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
        if (linkedList.remove(1)) {
            System.out.println("remove 2000");
            for (int i = 0; i < linkedList.size(); i++) {
                System.out.println(linkedList.get(i));
            }
        }
        linkedList.add(4000);
        System.out.println("add 4000 in end of list");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
        if (linkedList.remove(1)) {
            System.out.println("remove 3000");
            for (int i = 0; i < linkedList.size(); i++) {
                System.out.println(linkedList.get(i));
            }
        }
        System.out.println("clean");
        linkedList.clear();
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
        System.out.println("remove null");
        linkedList.remove(1);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
        linkedList.add(5000);
        System.out.println("add 5000 in end of list");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
        if (linkedList.remove(0)) {
            System.out.println("remove 5000");
            for (int i = 0; i < linkedList.size(); i++) {
                System.out.println(linkedList.get(i));
            }
        }
    }
}
