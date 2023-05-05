package domain;


import domain.ColasNListas.*;
import domain.Exceptions.QueueException;
import domain.Exceptions.StackException;
import domain.Objetos.Person;
import domain.Objetos.Place;
import domain.Objetos.Weather;
import org.junit.jupiter.api.Test;

class LinkedQueueTest {
    @Test
    void test1() {
        LinkedQueue queue = new LinkedQueue();
        try {
            queue.enQueue(10);
            queue.enQueue(20);
            queue.enQueue(5);
            queue.enQueue(7);
            queue.enQueue(2);
            queue.enQueue(1);
            System.out.println(queue.toString());
            System.out.println("index of 5: " + queue.indexOf(5));
            System.out.println("index of 2: " + queue.indexOf(2));
            System.out.println("index of 7: " + queue.indexOf(7));
            System.out.println((queue.contains(11)
                    ? "El 11 fue encolado"
                    : "El 11 no fue encolado"));
            System.out.println((queue.contains(2)
                    ? "El 2 fue encolado"
                    : "El 2 no fue encolado"));

        } catch (QueueException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void test2() {

        //Singly Linked List
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        for (int i = 0; i < 30; i++) {
            singlyLinkedList.add(util.Utility.getCountry(i));
        }

        //Array Queue
        ArrayQueue arrayQueue = new ArrayQueue(20);
        try {
            for (int i = 0; i < 20; i++) {
                arrayQueue.enQueue(new Person(util.Utility.getPerson(i), util.Utility.mood(i)));
            }
        } catch (QueueException e) {
            throw new RuntimeException(e);
        }

        //ArrayStack
        ArrayStack arrayStack = new ArrayStack(30);
        try {
            for (int i = 0; i < 5; i++) {
                arrayStack.push(util.Utility.getPerson(i));
                arrayStack.push(new Weather(util.Utility.getWeather()));
                arrayStack.push(new Place(util.Utility.getPlace()));
                arrayStack.push(util.Utility.random());
            }
        } catch (StackException e) {
            throw new RuntimeException(e);
        }

        //Linked Stack
        LinkedStack linkedStack = new LinkedStack();
        try {
            for (int i = 0; i < 100; i++) {
                linkedStack.push(util.Utility.random(1, 100));
            }
        } catch (StackException e) {
            throw new RuntimeException(e);
        }

        //Linked Queue
        LinkedQueue queue = new LinkedQueue();
        try {
            queue.enQueue(singlyLinkedList);
            queue.enQueue(arrayQueue);
            queue.enQueue(arrayStack);
            queue.enQueue(linkedStack);
            System.out.println(queue);
            System.out.println("Size: " + queue.size());

           queue = deQueueArray(queue);
           System.out.println(queue);
           System.out.println("Size: " + queue.size());


        } catch (QueueException e) {
            throw new RuntimeException(e);
        }

    }

    public LinkedQueue deQueueArray(LinkedQueue queue) {
        //ArrayStack stack = new ArrayStack(30);
        LinkedQueue aux = new LinkedQueue();
        try {
            while (!queue.isEmpty()) {
                if(util.Utility.instanceOf(queue.front())=="arrayStack"){
                    queue.deQueue();
                }else{
                    aux.enQueue(queue.deQueue());
                }

            }
            while (!aux.isEmpty()){
                queue.enQueue(aux.deQueue());
            }
        } catch (QueueException e) {
            throw new RuntimeException(e);
        }
        return queue;
    }
}
