package dan.was.com.example.rest.star.wars.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@ToString
@Getter
@Setter

public class Person {
    private int id;
    private String name;
    private String birth_year;
    private String eye_color;
    private String gender;
    private String hair_color;
    private String height;
    private String mass;
    private String skin_color;
    private String homeworld;
    @JsonIgnore
    private String[] films;
    @JsonIgnore
    private String[] species;
    private String[] starships;
    private String[] vehicles;
    @JsonIgnore
    private String url;
    @JsonIgnore
    private String created;
    @JsonIgnore
    private String edited;
}
