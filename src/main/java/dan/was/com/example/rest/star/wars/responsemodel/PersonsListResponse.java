package dan.was.com.example.rest.star.wars.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonsListResponse extends RestResponse{
    @JsonIgnore
    private String message;

    private int pages;
    private int count;
    private List<PersonResponse> elements;

    public PersonsListResponse(int pages, int count, List<PersonResponse> elements) {
        this.pages = pages;
        this.count = count;
        this.elements = elements;
    }
}
