package domain;

public class Place {
    private int id;
    private String nombre;
    private static int counter;

    public Place(String nombre) {
        this.id = ++counter;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
