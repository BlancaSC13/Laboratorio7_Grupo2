package domain.ColasNListas;

import domain.Exceptions.QueueException;
import domain.Objetos.Node;
import domain.Objetos.Person;
import domain.Objetos.Testeoo;
import domain.interfaces.Queue;

public class PriorityLinkedQueue implements Queue {

    private Node front;//apunta al anterior

    private Node rear;//apunta al siguiente
    private int count; //control de elementos encolados

    public LinkedQueue getPriorityC() {
        return priorityC;
    }

    LinkedQueue priorityC = new LinkedQueue();

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

    public void enQueue2(Person person, String priori, LinkedQueue pri) throws QueueException {
        int priority = util.Utility.priority(priori);
        System.out.println("Adding element " + person.getName() + " with priority " + priority);
        Node newNode = new Node(person,priority);
        priorityC.enQueue(priority);

        if (isEmpty()) {
            rear = newNode;
            //garantizamos que anterior queda apuntando al primer nodo
            front = newNode;

           System.out.println("Queue was empty. Added element " + front.getData() + " with priority " + front.getPriority());
        }else {
            enQueue(newNode.data);
            LinkedQueue lq1 = new LinkedQueue();
            LinkedQueue lq11 = new LinkedQueue();
            LinkedQueue lq2 = new LinkedQueue();
            LinkedQueue lq22 = new LinkedQueue();
            LinkedQueue lq3 = new LinkedQueue();
            LinkedQueue lq33 = new LinkedQueue();
            while (!isEmpty()){
                    if ((Integer) priorityC.front() == 1){
                        lq1.enQueue(deQueue());
                        lq11.enQueue(priorityC.deQueue());
                    } else if ((Integer) priorityC.front() == 2) {
                        lq2.enQueue(deQueue());
                        lq22.enQueue(priorityC.deQueue());
                    }else {
                        lq3.enQueue(deQueue());
                        lq33.enQueue(priorityC.deQueue());
                    }
                }
            while (!lq3.isEmpty()){
                enQueue(lq3.deQueue());
                priorityC.enQueue(lq33.deQueue());
            }
            while (!lq2.isEmpty()){
                enQueue(lq2.deQueue());
                priorityC.enQueue(lq22.deQueue());
            }
            while (!lq1.isEmpty()){
                enQueue(lq1.deQueue());
                priorityC.enQueue(lq11.deQueue());
            }
        } System.out.println("Added element " + person.getName() + " with priority " + priority);
        System.out.println(toString());
        pri = priorityC;
        System.out.println(pri.toString());
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
                result += front.getData() + ", ";
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
