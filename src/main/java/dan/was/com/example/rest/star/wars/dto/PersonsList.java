package dan.was.com.example.rest.star.wars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.NoArgsConstructor;

//@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
//@JsonPOJOBuilder(buildMethodName = "createBean", withPrefix = "construct")
public class PersonsList {

    @JsonProperty("count")
    private int count;
//    @JsonProperty("elements")
    private Person[] results;

    public PersonsList(int count, Person[] results) {
        this.count = count;
        this.results = results;
    }


    public int getCount() {
        return count;
    }

    public Person[] getResults() {
        return results;
    }

    public void setResults(Person[] results) {
        this.results = results;
    }
}
