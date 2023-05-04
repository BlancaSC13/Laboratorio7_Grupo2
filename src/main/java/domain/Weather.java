package domain;

public class Weather {
    private int id;
    private String name;
    private static int counter;

    public Weather(String name) {
        this.id = ++counter;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
