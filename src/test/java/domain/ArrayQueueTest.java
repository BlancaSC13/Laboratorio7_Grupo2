package domain;

import domain.ColasNListas.ArrayQueue;
import domain.Exceptions.QueueException;
import domain.Objetos.Person;
import org.junit.jupiter.api.Test;

class ArrayQueueTest {

    @Test
    void test() {
    ArrayQueue arrayQueue = new ArrayQueue(20);
        try {
            arrayQueue.enQueue(new Person("Andrea", "Happiness"));
            arrayQueue.enQueue(new Person("Pedro", "Sadness"));
            arrayQueue.enQueue(new Person("Ana", "Anger"));
            arrayQueue.enQueue(new Person("María", "Sickness"));
            arrayQueue.enQueue(new Person("Juan", "Cheerful"));
            arrayQueue.enQueue(new Person("Marcela", "Reflective"));
            arrayQueue.enQueue(new Person("Carlos", "Gloomy"));
            arrayQueue.enQueue(new Person("Mariano", "Romantic"));
            arrayQueue.enQueue(new Person("Jason", "Calm"));
            arrayQueue.enQueue(new Person("Sergio", "Hopeful"));
            arrayQueue.enQueue(new Person("Erick", "Fearful"));
            arrayQueue.enQueue(new Person("Mariana", "Tense"));
            arrayQueue.enQueue(new Person("Alejandra", "Lonely"));
            arrayQueue.enQueue(new Person("Tomás", "Anger"));
            arrayQueue.enQueue(new Person("Rocio", "Calm"));
            arrayQueue.enQueue(new Person("Jose", "Reflective"));
            arrayQueue.enQueue(new Person("Armando", "Romantic"));
            arrayQueue.enQueue(new Person("Nohelia", "Happiness"));
            arrayQueue.enQueue(new Person("Camila", "Cheerful"));
            arrayQueue.enQueue(new Person("Rubén", "Fearful"));

            //testeos
            System.out.println(arrayQueue);
            System.out.println(arrayQueue.size());
/*            System.out.println(arrayQueue.contains(new Person("Erick","Fearful")));
            System.out.println(arrayQueue.contains(new Person("Mariana","Anger")));
            System.out.println(arrayQueue.indexOf(new Person("Jason", "Calm")));*/

        } catch (QueueException e) {
            throw new RuntimeException(e);
        }

    }
}