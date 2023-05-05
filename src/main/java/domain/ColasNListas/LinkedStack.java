package domain.ColasNListas;

import domain.Exceptions.StackException;
import domain.Objetos.Node;
import domain.interfaces.Stack;

public class LinkedStack implements Stack {
    private Node top; //es un apuntador al tope de la pila enlazada
    private int counter; //contador de elementos apilados

    public LinkedStack() {
        this.top = null;
        this.counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        this.top = null;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Object peek() throws StackException {
        if (isEmpty())
            throw new StackException("Linked Stack is empty");
        return this.top.data;
    }

    @Override
    public Object top() throws StackException {
        if (isEmpty())
            throw new StackException("Linked Stack is empty");
        return this.top.data;
    }

    @Override
    public void push(Object element) throws StackException {
        Node newNode = new Node(element);
        if (isEmpty()) {
            //Creamos un nuevo nodo
            this.top = newNode;
        } else {
            newNode.next = top;//Hacemos el enlace entre nodos
            top = newNode;
        }
        this.counter++;//Incremento el contador de elementos
    }

    @Override
    public Object pop() throws StackException {
        if (isEmpty())
            throw new StackException("Linked Stack is empty");
        Object element = top.data;
        top = top.next;//Movemos top al siguiente nodo
        counter--;
        return element;

    }

    @Override
    public String toString() {
        String result = "\nLinked Stack content\n";
        LinkedStack aux = new LinkedStack();
        try {
            while (!isEmpty()) {
                result += peek() + " ";
                aux.push(pop());
            }

            //requiero volver a llenar la pila original
            while (!aux.isEmpty()) {
                push(aux.pop());
            }
        } catch (StackException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

}
