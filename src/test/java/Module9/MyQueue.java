package Module9;
class MyQueue <E> {
    //FIFO
    NodeQueue head;
    NodeQueue tail;
    int count;
    MyQueue() {
        count = 0;
        tail = head = null;
    }
    void add(Object value) {
        if (count == 0) {
            NodeQueue node = new NodeQueue((E) value, null);
            tail = head = node;
            count++;
            return;
        }
        NodeQueue node = new NodeQueue((E) value, null);
        tail.next = node;
        tail = node;

        count++;
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
    E pool() {
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

class NodeQueue <E> {
    E data;
    NodeQueue next;
    NodeQueue(E data, NodeQueue next) {
        this.data = data;
        this.next = next;
    }
}

class TestMyQueue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.add(1000);
        myQueue.add(2000);
        myQueue.add(3000);
        System.out.println("output 1000 -> " + myQueue.peek());
        System.out.println("output size 3 -> " + myQueue.size());
        System.out.println("output and kill 1000 -> " + myQueue.pool());
        System.out.println("output size 2 -> " + myQueue.size());
        myQueue.add(4000);// add to queue 4000
        System.out.println("output size 3 -> " + myQueue.size());
        System.out.println("output and kill 2000 -> " + myQueue.pool());
        System.out.println("output size 2 -> " + myQueue.size());
        myQueue.clear();// clear queue
        System.out.println(myQueue.pool());
        System.out.println("output size 0 -> " + myQueue.size());
        myQueue.add(5000);// add to queue 5000
        myQueue.add(6000);// add to queue 6000
        System.out.println("output and kill 5000 -> " + myQueue.pool());
        System.out.println("output size 1 -> " + myQueue.size());
        System.out.println("output and kill 6000 -> " + myQueue.pool());
        System.out.println("output size 0 -> " + myQueue.size());
        myQueue.add(1000);
        myQueue.add(2000);
        myQueue.add(3000);
        myQueue.add(4000);
        myQueue.add(5000);
        System.out.println("add 1000, 2000, 3000, 4000, 5000");
        int i = 0;
        while (myQueue.size() > 0) {
            System.out.println("output [ " + i + " ] -> " + myQueue.pool());
            i++;
        }
    }
}
