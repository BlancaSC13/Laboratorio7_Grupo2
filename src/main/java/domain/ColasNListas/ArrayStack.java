package domain.ColasNListas;

import domain.Exceptions.StackException;
import domain.interfaces.Stack;

public class ArrayStack implements Stack {
    private int n; //tam max de la pila
    private int top; //indica el tope de la pila
    private Object dataStack[];

    public ArrayStack(int n) {
        this.n = n;
        this.top = -1;
        this.dataStack = new Object[n];
    }

    @Override
    public int size() {
        return top+1;
    }

    @Override
    public void clear() {
        this.top = -1;
        //this.dataStack = new Object[n];
    }

    @Override
    public boolean isEmpty() {
        return top==-1;
    }

    @Override
    public Object peek() throws StackException {
        if(isEmpty())
            throw new StackException("Array Stack is empty");
        return this.dataStack[top];
    }

    @Override
    public Object top() throws StackException {
        if(isEmpty())
            throw new StackException("Array Stack is empty");
        return this.dataStack[top];
    }

    @Override
    public void push(Object element) throws StackException {
        if(top>=n-1){
            throw new StackException("Array Stack is full");
        }
        this.dataStack[++top] = element;
    }

    @Override
    public Object pop() throws StackException {
        if(isEmpty())
            throw new StackException("Array Stack is empty");
        return this.dataStack[top--];
    }

    @Override
    public String toString() {
        String result = "Array Stack content\n";
        ArrayStack aux = new ArrayStack(size());
        try {
            while (!isEmpty()) {
                result += peek() + ", ";
                aux.push(pop());
            }

            //requiero volver a llenar la pila original
            while (!aux.isEmpty()) {
                push(aux.pop());
            }
        }catch(StackException ex){
            throw  new RuntimeException(ex);
        }
        return result;
    }
}
