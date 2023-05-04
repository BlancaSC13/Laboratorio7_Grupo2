package domain;

public class HeaderLinkedQueue implements Queue {

    private Node front;//apunta al anterior
    private Node rear;//apunta al siguiente
    private int count; //control de elementos encolados

    public HeaderLinkedQueue() {
        this.front = this.rear = new Node();
        this.count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        this.front = this.rear = new Node();
        this.count = 0;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public int indexOf(Object element) throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Header Linked Queue is empty");
        }
        HeaderLinkedQueue aux = new HeaderLinkedQueue();
        int i = 1;
        int j = -1; // si es -1 no existe el elemento en la cola
        while (!isEmpty()) {
            if (util.Utility.compare(front(), element) == 0) {
                j = i;
            }
            aux.enQueue(deQueue());
            i++;
        }
        //dejamos la cola en su estado original
        while (!aux.isEmpty()) {
            enQueue(aux.deQueue());
        }
        return j;
    }

    @Override
    public void enQueue(Object element) throws QueueException {
        Node newNode = new Node(element);
        rear.next = newNode;
        rear = newNode;
    count++;
}

    @Override
    public Object deQueue() throws QueueException {
        if (isEmpty()) {throw new QueueException("Header Linked Queue is empty");}
        Object element = front.next.data;
        //case 1 - Cuando solo hay 1 elemento
        if (front.next == rear) {
            clear();//elimino la cola
        } else {//case 2 - Cuando hay varios elementos
            front.next = front.next.next;
            count--;//actualizo el contador de elementos
        }

        return element;
    }

    @Override
    public boolean contains(Object element) throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Header Linked Queue is empty");
        }
        HeaderLinkedQueue aux = new HeaderLinkedQueue();
        boolean encontrado = false;
        while (!isEmpty()) {
            if (util.Utility.compare(front(), element) == 0) {
                encontrado = true;
            }
            aux.enQueue(deQueue());
        }
        while (!aux.isEmpty()) {
            enQueue(aux.deQueue());
        }
        return encontrado;

    }

    @Override
    public Object peek() throws QueueException {
        if (isEmpty()) throw new QueueException("Header Linked Queue is empty");
        return front.next.data;
    }

    @Override
    public Object front() throws QueueException {
        if (isEmpty()) throw new QueueException("Header Linked Queue is empty");
        return front.next.data;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Header Linked Queue is empty";
        String result = "Header Linked Queue Content\n";
        try {
            HeaderLinkedQueue aux = new HeaderLinkedQueue();
            while (!isEmpty()) {
                result += front() + ", ";
                aux.enQueue(deQueue());
            }
            while (!aux.isEmpty()) {
                enQueue(aux.deQueue());
            }
        } catch (QueueException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

}
