package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityLinkedQueueTest {
    @Test
    void test1() {
        try {
            PriorityLinkedQueue q = new PriorityLinkedQueue();
            q.enQueue("Carlos", 1);
            q.enQueue("María", 3);
            q.enQueue("Raúl", 2);
            q.enQueue("Valeria", 3);
            q.enQueue("Pedro", 2);
            System.out.println(q);
        } catch (QueueException ex) {
            throw new RuntimeException(ex);
        }
    }
}