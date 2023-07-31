package Module9;

class MyStack <E> {
/*
    Написати свій клас MyStack як аналог класу Stack, який працює за принципом LIFO (last-in-first-out).

Можна робити за допомогою Node або використати масив.

Методи

push(Object value) додає елемент в кінець
remove(int index) видаляє елемент за індексом
clear() очищає колекцію
size() повертає розмір колекції
peek() повертає перший елемент стеку
pop() повертає перший елемент стеку та видаляє його з колекції
 */
//LIFO
    NodeStack head;
    int count;
    MyStack() {
        count = 0;
        head = null;
    }
    void push(Object value) {
        if (count == 0) {
            NodeStack node = new NodeStack((E) value, null);
            head = node;
            count++;
            return;
        }
        NodeStack node = new NodeStack((E) value, head);
        head = node;
        count++;
    }

    boolean remove(int index) {
        NodeStack current, previous;
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

        int counter = 1;
        previous = head;
        current = head.next;
        while (counter <= index) {
            if (index == counter) {
                previous.next = current.next;
                count--;
                return true;
            }
            counter++;
            current = current.next;
            previous = previous.next;
        }
        return false;
    }
    void clear() {
        count = 0;
        head = null;
    }
    int size() {
        return count;
    }
    E peek() {
        if (count == 0) {
            return null;
        }
        return (E) head.data; //only return
    }
    E pop() {
        E ejectedElement;
        if (count == 0) {
            return null;
        }
        ejectedElement = (E)head.data;
        head = head.next;
        count--;
        return ejectedElement;// return and kill
    }

}

class NodeStack <E> {
    E data;
    NodeStack next;
    NodeStack(E data, NodeStack next) {
        this.data = data;
        this.next = next;
    }
}

class TestMyStack {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1000);
        myStack.push(2000);
        myStack.push(3000);
        System.out.println("add 1000, 2000, 3000");
        System.out.println("output 3000 -> " + myStack.peek());
        System.out.println("output size 3 -> " + myStack.size());
        myStack.remove(1);
        System.out.println("remove 2000");
        System.out.println("output size 2 -> " + myStack.size());
        System.out.println("output and kill 3000 -> " + myStack.pop());
        System.out.println("output size 1 -> " + myStack.size());
        myStack.push(4000);// add to stack 4000
        System.out.println("add 4000");
        System.out.println("output 4000 -> " + myStack.peek());
        System.out.println("output size 2 -> " + myStack.size());
        myStack.clear();// clear stack
        System.out.println("clear stack");
        System.out.println(myStack.peek());
        System.out.println("output size 0 -> " + myStack.size());
        myStack.push(1000);
        myStack.push(2000);
        myStack.push(3000);
        myStack.push(4000);
        myStack.push(5000);
        System.out.println("add 1000, 2000, 3000, 4000, 5000");
        int i = 0;
        while (myStack.size() > 0) {
            System.out.println("output [ " + i + " ] -> " + myStack.pop());
            i++;
        }
        myStack.push(1000);
        myStack.push(2000);
        myStack.push(3000);
        myStack.push(4000);
        myStack.push(5000);
        System.out.println("add 1000, 2000, 3000, 4000, 5000");
        myStack.remove(1);
        System.out.println("remove 2nd element - 4000");
        i = 0;
        while (myStack.size() > 0) {
            System.out.println("output [ " + i + " ] -> " + myStack.pop());
            i++;
        }
        myStack.push(1000);
        myStack.push(2000);
        myStack.push(3000);
        myStack.push(4000);
        myStack.push(5000);
        System.out.println("add 1000, 2000, 3000, 4000, 5000");
        myStack.remove(3);
        System.out.println("remove 4th element - 2000");
        i = 0;
        while (myStack.size() > 0) {
            System.out.println("output [ " + i + " ] -> " + myStack.pop());
            i++;
        }
    }
}
