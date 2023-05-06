package domain.Objetos;

public class Testeoo {

    private Person person;
    private Integer priority;

    public Testeoo(Person person, Integer priority) {
        this.person = person;
        this.priority = priority;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Testeoo{" +
                "person=" + person +
                ", priority='" + priority + '\'' +
                '}';
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
