package domain;

import domain.ColasNListas.LinkedQueue;
import domain.ColasNListas.PriorityLinkedQueue;
import domain.Exceptions.QueueException;
import domain.Objetos.Person;
import domain.Objetos.Testeoo;
import org.junit.jupiter.api.Test;

class PriorityLinkedQueueTest {
    @Test
    void test1() {
        try {
            PriorityLinkedQueue q = new PriorityLinkedQueue();
            LinkedQueue priori = new LinkedQueue();
            q.enQueue2(new Person("Carlos","feliz"), "High",priori);
            q.enQueue2(new Person("Ana","triste"), "Low",priori);
            q.enQueue2(new Person("David","contento"), "Medium",priori);
            q.enQueue2(new Person("Elena","preocupada"), "High",priori);
            q.enQueue2(new Person("Fernando","alegre"), "Medium",priori);
            q.enQueue2(new Person("Gustavo","nervioso"), "Low",priori);
            System.out.println(q);
        } catch (QueueException ex) {
            throw new RuntimeException(ex);
        }
    }
}