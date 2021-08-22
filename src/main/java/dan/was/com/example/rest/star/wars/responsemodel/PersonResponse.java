package dan.was.com.example.rest.star.wars.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

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
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;
    private HomeworldResponse homeworld;
    private List<StarshipResponse> starships;

    public PersonResponse(int id, String name,
                          String birth_year,
                          String eye_color,
                          String gender,
                          String hair_color,
                          String height,
                          String mass,
                          String skin_color,
                          HomeworldResponse homeworld,
                          List<StarshipResponse> starships) {
        this.id = id;
        this.name = name;
        this.birthYear = birth_year;
        this.eyeColor = eye_color;
        this.gender = gender;
        this.hairColor = hair_color;
        this.height = height;
        this.mass = mass;
        this.skinColor = skin_color;
        this.homeworld = homeworld;
        this.starships = starships;
    }
}

