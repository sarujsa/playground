package sarujsa.docker.csm.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("attraction")
public class Attraction {

  @Id private String id;
  private String name;
  private String location;
  private String description;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Attraction{"
        + "id='"
        + id
        + '\''
        + ", name='"
        + name
        + '\''
        + ", location='"
        + location
        + '\''
        + ", description='"
        + description
        + '\''
        + '}';
  }
}
