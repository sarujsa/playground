package sarujsa.docker.csm.dto;

import java.util.Objects;

public class AttractionDto {

    private String name;
    private LocationDto locationDto;
    private String description;

    public AttractionDto() {}
    
    public AttractionDto(String name, LocationDto locationDto, String description) {
        this.name = name;
        this.locationDto = locationDto;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationDto getLocation() {
        return locationDto;
    }

    public void setLocation(LocationDto locationDto) {
        this.locationDto = locationDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttractionDto that = (AttractionDto) o;
        return Objects.equals(name, that.name) && Objects.equals(locationDto, that.locationDto) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, locationDto, description);
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                ", location=" + locationDto +
                ", description='" + description + '\'' +
                '}';
    }
}
