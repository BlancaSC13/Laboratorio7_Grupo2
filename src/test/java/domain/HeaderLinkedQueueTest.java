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
        System.out.println("\n Este metodo separa los climas de foggy y sunny de la lista 1 y los agrega a la lista 2: ");
        deleteByWeather(hlq1,hlq2,new Weather("foggy"),new Weather("sunny"));
        System.out.println("Lista 1 : " + hlq1);
        System.out.println("Lista 2 : " + hlq2);
        System.out.println("\nEste metodo separa Paraiso y Liberia de la lista 1 y los agrega a la lista 3: ");
        deleteByPlace(hlq1,hlq3,new Place("Paraíso"),new Place("Liberia"));
        System.out.println("Lista 1 : " + hlq1);
        System.out.println("Lista 3 : " + hlq3);
        System.out.println("\nEste metodo separa thunderstorm y cloudy de la lista 1 y los agrega a las listas 2 y 3: ");
        deleteByWeatherWithTwo(hlq1,hlq2,hlq3,new Weather("thunderstorm"),new Weather("cloudy"));
        System.out.println("Lista 1 : " + hlq1);
        System.out.println("Lista 2 : " + hlq2);
        System.out.println("Lista 3 : " + hlq3);
    }

    public void deleteByWeather (HeaderLinkedQueue queue1, HeaderLinkedQueue queue2, Weather weather1, Weather weather2){
            try {
                HeaderLinkedQueue aux = new HeaderLinkedQueue();
                while (!queue1.isEmpty()) {
                    if (queue1.front() instanceof Climate){
                        if (((Climate) queue1.front()).getWeather().getName() == weather1.getName() || ((Climate) queue1.front()).getWeather().getName() == weather2.getName()){
                            queue2.enQueue(queue1.deQueue());
                        }
                        else {
                            aux.enQueue(queue1.deQueue());
                        }
                    }
                }
                while (!aux.isEmpty()){
                    queue1.enQueue(aux.deQueue());
                }
            }catch (QueueException e) {
                throw new RuntimeException(e);
            }
    }
    public void deleteByWeatherWithTwo (HeaderLinkedQueue queue1, HeaderLinkedQueue queue2, HeaderLinkedQueue queue3, Weather weather1, Weather weather2){
        try {
            HeaderLinkedQueue aux = new HeaderLinkedQueue();
            while (!queue1.isEmpty()) {
                Object obj = queue1.front();
                if (queue1.front() instanceof Climate){
                    if (((Climate) queue1.front()).getWeather().getName() == weather1.getName() || ((Climate) queue1.front()).getWeather().getName() == weather2.getName()){
                        queue2.enQueue(obj);
                        queue3.enQueue(obj);
                        queue1.deQueue();
                    }
                    else {
                        aux.enQueue(queue1.deQueue());
                    }
                }
            }
            while (!aux.isEmpty()){
                queue1.enQueue(aux.deQueue());
            }
        }catch (QueueException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteByPlace (HeaderLinkedQueue queue1, HeaderLinkedQueue queue2, Place place1, Place place2){
        try {
            HeaderLinkedQueue aux = new HeaderLinkedQueue();
            while (!queue1.isEmpty()) {
                if (queue1.front() instanceof Climate){
                    if (((Climate) queue1.front()).getPlace().getNombre() == place1.getNombre() || ((Climate) queue1.front()).getPlace().getNombre() == place2.getNombre()){
                        queue2.enQueue(queue1.deQueue());
                    }
                    else {
                        aux.enQueue(queue1.deQueue());
                    }
                }
            }
            while (!aux.isEmpty()){
                queue1.enQueue(aux.deQueue());
            }
        }catch (QueueException e) {
            throw new RuntimeException(e);
        }
    }
}
