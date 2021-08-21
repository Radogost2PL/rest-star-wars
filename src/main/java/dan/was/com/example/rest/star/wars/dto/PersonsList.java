package dan.was.com.example.rest.star.wars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonPOJOBuilder(buildMethodName = "createBean", withPrefix = "construct")
public class PersonsList {


    private int count;
    private List<Person> results;

}
