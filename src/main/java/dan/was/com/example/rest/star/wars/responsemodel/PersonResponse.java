package dan.was.com.example.rest.star.wars.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dan.was.com.example.rest.star.wars.dto.Planet;
import dan.was.com.example.rest.star.wars.dto.Starship;
import lombok.*;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor

@ToString
@Getter
@Setter
public class PersonResponse extends RestResponse {
    @JsonIgnore
    private String message;

    private int id;
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private Planet homeworld;
    private List<Starship> starships;

    public PersonResponse(int id, String name, String birth_year, String eye_color, String gender, String hair_color, String height, String mass, String skin_color, Planet homeworld, List<Starship> starships) {
        this.id = id;
        this.name = name;
        this.birth_year = birth_year;
        this.eye_color = eye_color;
        this.gender = gender;
        this.hair_color = hair_color;
        this.height = height;
        this.mass = mass;
        this.skin_color = skin_color;
        this.homeworld = homeworld;
        this.starships = starships;
    }
}

