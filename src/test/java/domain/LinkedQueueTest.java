package domain;


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

    void test2() {

    }
}