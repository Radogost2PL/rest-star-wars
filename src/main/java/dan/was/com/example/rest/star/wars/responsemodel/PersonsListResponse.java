package dan.was.com.example.rest.star.wars.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonsListResponse {
    private int pages;
    private int count;
    private List<PersonResponse> elements;
}
