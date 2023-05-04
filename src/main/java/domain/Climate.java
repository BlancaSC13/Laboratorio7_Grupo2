package domain;

public class Climate {
    private Place place;
    private Weather weather;

    public Climate(Place place, Weather weather) {
        this.place = place;
        this.weather = weather;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Climate{" +
                "place=" + place +
                ", weather=" + weather +
                '}';
    }
}
