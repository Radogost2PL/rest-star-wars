package dan.was.com.example.rest.star.wars.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Starship {

    private String name;
    private String model;
    private String starship_class;
    private String manufacturer;
    private String cost_in_credits;
    private String length;
    private String crew;
    private String passengers;
    private String max_atmosphering_speed;
    private String hyperdrive_rating;
    @JsonProperty("MGLT")
    private String MGLT;
    private String cargo_capacity;
    private String consumables;
}