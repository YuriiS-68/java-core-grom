package dz_lesson35_36.model;

import java.util.Objects;

public class Hotel {
    private long id;
    private String country;
    private String city;
    private String street;
    private String name;

    public Hotel() {
    }

    public Hotel(long id, String country, String city, String street, String name) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                Objects.equals(country, hotel.country) &&
                Objects.equals(city, hotel.city) &&
                Objects.equals(street, hotel.street) &&
                Objects.equals(name, hotel.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, country, city, street, name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
