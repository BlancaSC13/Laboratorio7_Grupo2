package domain;

import org.junit.jupiter.api.Test;

class HeaderLinkedQueueTest {

    @Test
    void test1() {
        HeaderLinkedQueue header = new HeaderLinkedQueue();
        try {
            header.enQueue(10);
            header.enQueue(20);
            header.enQueue(5);
            header.enQueue(7);
            header.enQueue(2);
            header.enQueue(1);
            System.out.println(header.toString());
            System.out.println("index of 5: " + header.indexOf(5));
            System.out.println("index of 2: " + header.indexOf(2));
            System.out.println("index of 7: " + header.indexOf(7));
            System.out.println((header.contains(11)
                    ? "El 11 fue encolado"
                    : "El 11 no fue encolado"));
            System.out.println((header.contains(2)
                    ? "El 2 fue encolado"
                    : "El 2 no fue encolado"));
        } catch (QueueException e) {
            throw new RuntimeException(e);
        }
    }
}