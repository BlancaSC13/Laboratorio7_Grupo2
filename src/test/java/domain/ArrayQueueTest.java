package domain;

import domain.ColasNListas.ArrayQueue;
import domain.Exceptions.QueueException;
import domain.Objetos.Person;
import org.junit.jupiter.api.Test;

class ArrayQueueTest {

    @Test
    void test() {
        try {
            ArrayQueue queue = new ArrayQueue(20);
            for (int i = 0; i < 20; i++) {
                queue.enQueue(new Person(util.Utility.getPerson(i), util.Utility.mood(i)));
            }
            System.out.println(queue.size());
        } catch (QueueException e) {
            throw new RuntimeException(e);
        }
    }
}