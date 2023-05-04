package domain.Objetos;

public class Person {
    private String name;
    private String mood;

    public Person(String name, String mood) {
        this.name = name;
        this.mood = mood;//estado de ánimo
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }
}
