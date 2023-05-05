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

            System.out.println(arrayQueue.contains(new Person("Carlos", "Gloomy"))
                    ? "Carlos - Gloomy fue encolado"
                    : "Carlos - Glommy no fue encolado");
            System.out.println(arrayQueue.contains(new Person("Carlos", "Calm"))
                    ? "Carlos - Calm fue encolado"
                    : "Carlos - Calm no fue encolado");
            System.out.println("El indice es: " + arrayQueue.indexOf(new Person("Rubén", "Fearful")));
            System.out.println("El indice es: " +arrayQueue.indexOf(new Person("Rubén", "Calm")));

            System.out.println("Mood a eliminar: Cheerful");
            arrayQueue = deleteByMood(arrayQueue);
            System.out.println("New arrayQueue...\n" +arrayQueue);
            System.out.println("New size...\n" + arrayQueue.size());

        } catch (QueueException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayQueue deleteByMood(ArrayQueue s) throws QueueException {
        ArrayQueue aux = new ArrayQueue(s.size());
        while (!s.isEmpty()) {
            Person person = (Person) s.front();
            if (person.getMood() == "Cheerful") {
                s.deQueue();
            }
            aux.enQueue(s.deQueue());
        }
        while (!aux.isEmpty()) {
            s.enQueue(aux.deQueue());
        }
        return s;
    }
}