package domain;

import domain.ColasNListas.HeaderLinkedQueue;
import domain.Exceptions.QueueException;
import domain.Objetos.Climate;
import domain.Objetos.Place;
import domain.Objetos.Weather;
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


    @Test
    void test2() {
        HeaderLinkedQueue hlq1 = new HeaderLinkedQueue();
        HeaderLinkedQueue hlq2 = new HeaderLinkedQueue();
        HeaderLinkedQueue hlq3 = new HeaderLinkedQueue();
        try {
            for (int i = 0; i < 20; i++) {
                hlq1.enQueue(new Climate(new Place(util.Utility.getPlace()), new Weather(util.Utility.getWeather())));
            }
        } catch (QueueException e) {
            throw new RuntimeException(e);
        }
        System.out.println(hlq1);
    }
}
