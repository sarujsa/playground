package sarujsa.docker.csm.dto;

import java.util.Objects;

public class Location {

    private String name;
    private String country;
    private LocationType type;

    public Location() {}

    public Location(String name, String country, LocationType type) {
        this.name = name;
        this.country = country;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name) && Objects.equals(country, location.country) && type == location.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, type);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", type=" + type +
                '}';
    }
}
