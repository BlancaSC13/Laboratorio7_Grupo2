package domain;

public class PriorityLinkedQueue implements Queue {

    private Node front;//apunta al anterior
    private Node rear;//apunta al siguiente
    private int count; //control de elementos encolados

    public PriorityLinkedQueue() {
        this.front = this.rear = null;
        this.count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        this.front = this.rear = null;
        this.count = 0;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
        //return count==0;
    }

    @Override
    public int indexOf(Object element) throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Priority Linked Queue is empty");
        }
        PriorityLinkedQueue aux = new PriorityLinkedQueue();
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
        if (isEmpty()) {//cola no existe
            rear = newNode;
            //garantizamos que anterior queda apuntando al primer nodo
            front = newNode;
        } else {//la cola existe
            rear.next = newNode;
            rear = newNode;
        }
        count++;
    }

    public void enQueue(Object element, Integer priority) throws QueueException {
        Node newNode = new Node(element, priority);
        if (isEmpty()) {//cola no existe
            rear = newNode;
            //garantizamos que anterior queda apuntando al primer nodo
            front = newNode;
        } else {//la cola existe
            Node aux = front;
            Node prev = front;
            while (aux != null && aux.priority > priority) {
                prev = aux; //dejamos un rastro en el nodo anterior
                aux = aux.next;//movemos al siguiente nodo
            }//Se sale del while cuando alcanza nulo o la prioridad de nuevo elemento es más alta
            if (aux == front) {//El nuevo elemento tiene prioridad más alta
                newNode.next = front;
                front = newNode;
            } else if (aux == null) { //prev está en el último nodo
                prev.next = newNode;
                rear = newNode;
            } else {
                prev.next = newNode;
                newNode.next = aux;
            }
        }
        count++;
    }

    @Override
    public Object deQueue() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Priority Linked Queue is empty");
        }
        Object element = front.data;
        //case 1 - Cuando solo hay 1 elemento
        if (front == rear) {
            clear();//elimino la cola
        } else {//case 2 - Cuando hay varios elementos
            front = front.next;
            count--;//actualizo el contador de elementos
        }

        return element;
    }

    @Override
    public boolean contains(Object element) throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Priority Linked Queue is empty");
        }
        PriorityLinkedQueue aux = new PriorityLinkedQueue();
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
        if (isEmpty()) throw new QueueException("Priority Linked Queue is empty");
        return front.data;
    }

    @Override
    public Object front() throws QueueException {
        if (isEmpty()) throw new QueueException("Priority Linked Queue is empty");
        return front.data;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Priority Linked Queue is empty";
        String result = "Priority Linked Queue Content\n";
        try {
            PriorityLinkedQueue aux = new PriorityLinkedQueue();
            while (!isEmpty()) {
                result += front() + ", ";
                Integer priority = front.priority;
                aux.enQueue(deQueue(), priority);
            }
            while (!aux.isEmpty()) {
                Integer priority = aux.front.priority;
                enQueue(aux.deQueue(), priority);
            }
        } catch (QueueException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

}
