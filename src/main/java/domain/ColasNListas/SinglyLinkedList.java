package domain.ColasNListas;


import domain.Exceptions.ListException;
import domain.Objetos.Node;
import domain.interfaces.List;

public class SinglyLinkedList implements List {
    private Node first;//apunta al inicio de la lista/Existe siempre que este en la clase
    private Node last;

    public SinglyLinkedList() {
        this.first = this.last = null;
    }

    @Override
    public int size() throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        }
        Node aux = first;
        int count = 0;
        while (aux != null) {
            count++;
            aux = aux.next;//Mueve el aux al siguiente nodo
        }
        return count;
    }

    @Override
    public void clear() {
        this.first = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        }
        Node aux = first;
        while (aux != null) {
            if (util.Utility.compare(aux.data, element) == 0) {
                return true;
            }
            aux = aux.next;//Mueve el aux al siguiente nodo
        }
        return false;//Si llegó aquí el elemento no existe
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);//Crea un nuevo nodo y le agrega element/solo existe en el método agregar
        if (isEmpty()) {
            this.first = newNode;//first apunta al nuevo nodo
            this.last = newNode;
        } else {
            Node aux = first;//apunta al primer nodo de la lista
            //necesito moverme por la lista hasta el final
            while (aux.next != null) {
                aux = aux.next;
            }
            //Se sale del while cuando aux.next==null
            aux.next = this.last = newNode;
        }
    }

    @Override
    public void addFirst(Object element) {
        if (isEmpty()) {
            this.add(element);
        } else {
            try {
                if (!contains(element)) {
                    Node newest = new Node(element);
                    newest.next = first;
                    first = newest;
                }
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void addLast(Object element) {
        add(element);

    }

//    @Override
//    public void addInSortedList(Object element) {
//        //Agregar de forma ordenada
//        try {
//            add(element);
//            sort();
//        } catch (ListException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Override
    public void remove(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        }
        //Caso 1: El elemento a suprimir es el primero de la lista
        if (util.Utility.compare(first.data, element) == 0) {
            first = first.next;
        } else {

            //Caso 2: El elemento puede estar en cualquier posición
            Node aPrev = first;
            Node aux = first.next;

            while (aux != null && !(util.Utility.compare(aux.data, element) == 0)) {
                aPrev = aux;
                aux = aux.next;
            }
            //Se sale cuando alcanza nulo o cuando encuentra el elemento
            if (aux != null && util.Utility.compare(aux.data, element) == 0) {
                //Desenlaza el nodo con le elemento a eliminar
                aPrev.next = aux.next;//Se lo brinca
            }

        }
    }


    @Override
    public Object removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        }
        Node aux = first;
        first = first.next;
        return aux;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        }
        Node aux = first;
        Object eliminado = this.last.data;//Almacena el dato que se elimina para luego retornarlo
        while (aux.next != this.last) {
            aux = aux.next;
        }
        aux.next = null;
        this.last = aux;
        return eliminado;
    }

//    @Override
//    public void sort() throws ListException {
//        if (isEmpty()) {
//            throw new ListException("Doubly Linked List is empty");
//        }
//        if (size() > 1) {
//            boolean cambio;
//            do {
//                Node actual = first;
//                Node anterior = null;
//                Node siguiente = first.next;
//                cambio = false;
//                int i = 0;
//                while (siguiente != null) {
//                    i++;
//                    Student actualStudentName = (Student) getNode(i).data;
//                    Student nextStudentName = (Student) getNode(i + 1).data;
//                    if (util.Utility.compare(actualStudentName.getName(), nextStudentName.getName()) > 0) {
//                        cambio = true;
//                        if (anterior != null) {
//                            Node sig = siguiente.next;
//                            anterior.next = siguiente;
//                            siguiente.next = actual;
//                            actual.next = sig;
//                        } else {
//                            Node sig = siguiente.next;
//                            first = siguiente;
//                            siguiente.next = actual;
//                            actual.next = sig;
//                        }
//                        anterior = siguiente;
//                        siguiente = actual.next;
//                    } else {
//                        anterior = actual;
//                        actual = siguiente;
//                        siguiente = siguiente.next;
//                    }
//                }
//                this.last = actual;
//            } while (cambio);
//        }
//    }

    @Override
    public int indexOf(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        }
        Node aux = first;
        int index = 1;
        while (aux != null) {
            if (util.Utility.compare(aux.data, element) == 0) {
                return index;
            } else {
                index++;
                aux = aux.next;//lo movemos al siguiente nodo
            }
        }
        return -1;//significa que el elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        } else {
            return this.last.data;
        }
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        } else {
            int prev = indexOf(element);
            if (prev == -1) {
                return "El elemento " + element + " no esta en la lista";
            } else if (getNode(prev - 1) == null) {
                return "El elemento previo a " + element + " no existe";
            } else {
                return "El elemento previo a " + element + " es: " + getNode(prev - 1).data;
            }
        }
    }

    @Override
    public Object getNext(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        } else {
            int next = indexOf(element);
            if (next == -1) {
                return "El elemento " + element + " no esta en la lista";
            } else if (getNode(next + 1) == null) {
                return "El siguiente elemento después de " + element + " no existe";
            } else {
                return "El siguiente elemento siguiente a : " + element + " es: " + getNode(next + 1).data;
            }
        }
    }

    @Override
    public Node getNode(int index) throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        }
        Node aux = first;
        int i = 1;
        while (aux != null) {
            if (util.Utility.compare(i, index) == 0) {
                return aux;
            }
            i++;
            aux = aux.next;//muevo auxiliar al siguiente nodo
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "Singly Linked List Content\n";
        Node aux = first;
        while (aux != null) {
            result += aux.data + "\n";
            aux = aux.next;//Mueve el aux al siguiente nodo
        }
        return result;
    }
}
